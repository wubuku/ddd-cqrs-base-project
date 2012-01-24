package org.nthdimenzion.ddd.infrastructure;

public abstract class AbstractEventListener {

    protected IEventBus eventBus;

    protected AbstractEventListener(IEventBus eventBus){
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    @Override
    protected void finalize(){
        this.eventBus.unRegister(this);
    }
}
