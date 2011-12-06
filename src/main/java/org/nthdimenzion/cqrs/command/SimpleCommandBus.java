package org.nthdimenzion.cqrs.command;

import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.DisplayableException;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;
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

    @Override
    public Object send(ICommand command) {
        Handler handler = commandHandlerRegistry.findCommandHandlerFor(command.getClass());
        try {
            return handler.invokeMethod(command);
        } catch (Throwable throwable) {
            boolean isExceptionHandled = handleException(throwable);
            logger.debug("Is exception handled " + isExceptionHandled);
            if (!isExceptionHandled) {
                logger.error("Unhandled exception ",throwable);
                throw new DisplayableException().havingCause(throwable);
            }
        }
        return null;
    }

    private boolean handleException(Throwable throwable) {
        logger.debug("Error bubbled up till CommandBus ",throwable);
        if(throwable instanceof InvocationTargetException){
            throwable = ((InvocationTargetException)throwable).getTargetException();
        }
        if (throwable instanceof IBaseException) {
            ErrorDetails errorDetails = ((IBaseException) throwable).getErrorDetails();
            if (errorDetails.getShowErrorInView()) {
                exceptionEventBus.raise(new OperationFailed(errorDetails));
                return true;
            }
        }
        return false;
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
