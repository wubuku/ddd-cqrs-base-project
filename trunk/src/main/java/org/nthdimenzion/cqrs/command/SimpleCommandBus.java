package org.nthdimenzion.cqrs.command;

import com.google.common.eventbus.EventBus;
import org.nthdimenzion.ddd.infrastructure.exception.DisplayableException;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("simpleCommandBus")
public class SimpleCommandBus implements ICommandBus {

    private final Logger logger = LoggerFactory.getLogger(SimpleCommandBus.class);

    @Autowired
    private ICommandHandlerRegistry commandHandlerRegistry;

    @Autowired
    @Qualifier("exceptionEventBus")
    private EventBus exceptionEventBus;

    @Override
    public Object send(ICommand command) {
        ICommandHandler commandHandler = commandHandlerRegistry.findCommandHanlerFor(command.getClass());
        try {
            return commandHandler.handle(command);
        } catch (Throwable throwable) {
            boolean isExceptionHandled = handleException(throwable);
            logger.debug("Is the exception handler " + isExceptionHandled);
            if (!isExceptionHandled) {
                throw new DisplayableException().havingCause(throwable);
            }
        }
        return null;
    }

    private boolean handleException(Throwable throwable) {
        logger.error("Error bubbled up till CommandBus ",throwable);
        if (throwable instanceof IBaseException) {
            ErrorDetails errorDetails = ((IBaseException) throwable).getErrorDetails();
            if (errorDetails.isSuppresException) {
                exceptionEventBus.post(errorDetails);
                return true;
            }
        }
        return false;
    }

    @Override
    public <C> void subscribe(Class<C> commandType, ICommandHandler handler) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <C> void unsubscribe(Class<C> commandType, ICommandHandler handler) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}