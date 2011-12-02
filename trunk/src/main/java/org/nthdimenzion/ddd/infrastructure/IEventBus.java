package org.nthdimenzion.ddd.infrastructure;

import org.nthdimenzion.ddd.domain.IEvent;

public interface IEventBus {

    void register(Object listener);

    void unRegister(Object listener);

    void raise(IEvent event);
}
