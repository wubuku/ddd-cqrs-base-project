package org.nthdimenzion.cqrs.command;

import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.*;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@Qualifier("simpleCommandBus")
public class SimpleCommandBus implements ICommandBus {

    private final Logger logger = LoggerFactory.getLogger(SimpleCommandBus.class);

    @Autowired
    private IMultiCommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("exceptionEventBus")
    private IEventBus exceptionEventBus;

    private CommandValidationFailed commandValidationFailed = new CommandValidationFailed();

    @Override
    public Object send(ICommand command) {
        Handler handler = commandHandlerRegistry.findCommandHandlerFor(command.getClass());
        try {
            if (!validate(command))
                return null;
            return handler.invokeMethod(command);
        } catch (Throwable throwable) {
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
        return null;
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
        try {
            command.validate();
        } catch (Exception exception) {
            String exceptionDetails = exception.getMessage();
            if (UtilValidator.isNotEmpty(exceptionDetails)) {
                commandValidationFailed.details = exceptionDetails;
            }
            exceptionEventBus.raise(commandValidationFailed);
            isValid = false;
        }
        return isValid;
    }


    @Override
    public <C> void subscribe(Class<C> commandType, ICommandHandler handler) {
        throw new UnsupportedOperationException("Will be available in future realeses, Method Signature may change");
    }

    @Override
    public <C> void unsubscribe(Class<C> commandType, ICommandHandler handler) {
        throw new UnsupportedOperationException("Will be available in future realeses, Method Signature may change");
    }
}
