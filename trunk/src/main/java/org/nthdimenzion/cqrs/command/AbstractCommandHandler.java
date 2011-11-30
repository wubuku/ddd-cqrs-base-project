package org.nthdimenzion.cqrs.command;

import com.google.common.eventbus.EventBus;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractCommandHandler {

    protected ModelMapper modelMapper = new ModelMapper();

    @Autowired
    @Qualifier("applicationEventBus")
    protected EventBus applicationEventBus;

    protected AbstractCommandHandler(){
        modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
    }
}
