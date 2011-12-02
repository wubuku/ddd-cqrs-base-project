package org.nthdimenzion.cqrs.command;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractCommandHandler {

    protected ModelMapper modelMapper = new ModelMapper();

    @Autowired
    @Qualifier("applicationEventBus")
    protected IEventBus applicationEventBus;

    protected AbstractCommandHandler(){
        modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
    }
}
