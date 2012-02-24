package org.nthdimenzion.ddd.application;

import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.ICommandInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandLoggingInterceptor implements ICommandInterceptor {

    private final Logger logger = LoggerFactory.getLogger(CommandLoggingInterceptor.class);


    @Override
    public boolean pre(ICommand command) {
        logger.debug("Pre command handling " + command.toString());
        return true;
    }

    @Override
    public boolean post(ICommand command, Object result) {
        logger.debug("Post command handling Command  " + command.toString() + " result " + result.toString());
        return true;
    }

}
