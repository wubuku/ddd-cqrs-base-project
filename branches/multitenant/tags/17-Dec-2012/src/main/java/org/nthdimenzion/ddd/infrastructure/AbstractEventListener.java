package org.nthdimenzion.ddd.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventListener {

    protected IEventBus eventBus;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractEventListener(IEventBus eventBus){
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    @Override
    protected void finalize(){
        this.eventBus.unRegister(this);
    }
}
