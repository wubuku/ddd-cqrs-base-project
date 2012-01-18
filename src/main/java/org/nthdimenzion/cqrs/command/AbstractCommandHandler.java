package org.nthdimenzion.cqrs.command;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractCommandHandler {

    protected ModelMapper modelMapper = new ModelMapper();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("applicationEventBus")
    protected IEventBus applicationEventBus;

    protected AbstractCommandHandler(){
        modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
    }
}
