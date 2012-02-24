package org.nthdimenzion.ddd.infrastructure;

import org.nthdimenzion.ddd.domain.IEvent;

public class EventBus implements IEventBus{

    private final com.google.common.eventbus.EventBus eventBus;

    public EventBus(com.google.common.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object listener) {
        this.eventBus.register(listener);
    }

    @Override
    public void unRegister(Object listener) {
        this.eventBus.unregister(listener);
    }

    @Override
    public void raise(IEvent event) {
        eventBus.post(event);
    }
}
