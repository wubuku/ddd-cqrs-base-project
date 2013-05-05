package org.nthdimenzion.cqrs.command;

import com.google.common.base.Preconditions;
import org.hibernate.exception.ConstraintViolationException;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.*;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@Component
@Qualifier("simpleCommandBus")
public class SimpleCommandBus implements ICommandBus {

    private final Logger logger = LoggerFactory.getLogger(SimpleCommandBus.class);

    @Autowired
    private IMultiCommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("exceptionEventBus")
    private IEventBus exceptionEventBus;

    private CommandValidationFailed commandValidationFailed = null;

    @Autowired
    private List<ICommandInterceptor> commandInterceptors;

    @Autowired
    private Validator validator;

    SimpleCommandBus(){
    }

    @Override
    public Object send(ICommand command) {
        Handler handler = commandHandlerRegistry.findCommandHandlerFor(command.getClass());
        Object result = null;
        try {
            if (validate(command)) {
                preInterceptors(commandInterceptors, command);
                result = handler.invokeMethod(command);
                postInterceptors(commandInterceptors,command,result);
            }
        } catch (Throwable throwable) {
            raiseEventForThrowable(throwable);
            result = null;
        }
        return result;
    }

    private void raiseEventForThrowable(Throwable throwable) {
        throwable = extractTargetException(throwable);
        boolean isExceptionEventRaised = raiseEventForException(throwable);
        logger.debug("Is exception handled " + isExceptionEventRaised);
        if (isNonDisplayBusinessException(isExceptionEventRaised, throwable)) {
            logger.error("Unhandled exception ", throwable);
            throwException(throwable);
        } else if (!isExceptionEventRaised) {
            raiseEventForUnexpectedException(throwable);
        }
    }

    private void postInterceptors(List<ICommandInterceptor> interceptors, ICommand command, Object result) {
        if (UtilValidator.isNotEmpty(interceptors)) {
            for (ICommandInterceptor interceptor : interceptors) {
                interceptor.post(command,result);
            }
        }
    }

    private void preInterceptors(List<ICommandInterceptor> interceptors, ICommand command) {
        if (UtilValidator.isNotEmpty(interceptors)) {
            for (ICommandInterceptor interceptor : interceptors) {
                interceptor.pre(command);
            }
        }
    }

    private void raiseEventForUnexpectedException(Throwable throwable) {
        exceptionEventBus.raise(OperationFailed.createDefaultDisplayableException(throwable));
    }

    private void throwException(Throwable throwable) {
        throw new DisplayableException().havingCause(throwable.getCause());
    }

    private boolean isNonDisplayBusinessException(boolean exceptionEventRaised, Throwable causeException) {
        return !exceptionEventRaised && causeException instanceof IBaseException;
    }

    private boolean raiseEventForException(Throwable throwable) {
        logger.debug("Error bubbled up till CommandBus ", throwable);
        if (throwable instanceof IBaseException) {
            ErrorDetails errorDetails = ((IBaseException) throwable).getErrorDetails();
            if (errorDetails.getShowErrorInView()) {
                exceptionEventBus.raise(new OperationFailed(errorDetails));
                return true;
            }
        }else if(throwable instanceof ConstraintViolationException){
            String message = throwable.getMessage().replace("key"," field ");
            ErrorDetails errorDetails = new ErrorDetails.Builder("000",message).exception(throwable)
                    .isShowErrorInView(true).build();
            exceptionEventBus.raise(new OperationFailed(errorDetails));
            return true;

        }
        return false;
    }

    private Throwable extractTargetException(Throwable throwable) {
        if (throwable instanceof InvocationTargetException) {
            throwable = ((InvocationTargetException) throwable).getTargetException();
        }
        return throwable;
    }

    private boolean validate(ICommand command) {
        boolean isValid = true;
        Set<ConstraintViolation<ICommand>> constraintViolations = validator.validate(command);
        if(UtilValidator.isNotEmpty(constraintViolations)){
            commandValidationFailed = new CommandValidationFailed(constraintViolations);
            exceptionEventBus.raise(commandValidationFailed);
            isValid = false;
            return isValid;
        }
        try {
            command.validate();
        } catch (Exception exception) {
            String exceptionDetails = exception.getMessage();
            commandValidationFailed = new CommandValidationFailed(exception);
            if (UtilValidator.isNotEmpty(exceptionDetails)) {
                commandValidationFailed.details = exceptionDetails;
            }
            exceptionEventBus.raise(commandValidationFailed);
            isValid = false;
        }
        return isValid;
    }

}
