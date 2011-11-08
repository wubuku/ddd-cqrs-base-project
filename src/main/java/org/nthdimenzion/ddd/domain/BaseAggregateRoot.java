package org.nthdimenzion.ddd.domain;

import com.google.common.eventbus.EventBus;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;



@MappedSuperclass
public abstract class BaseAggregateRoot extends IdGeneratingBaseEntity {

    private EventBus domainEventBus;

    public void setDomainEventBus(EventBus domainEventBus) {
        if (this.domainEventBus != null)
            throw new IllegalStateException("DomainEventBus is already set! Probably You have logical error in code");
        this.domainEventBus = domainEventBus;
    }

    @Transient
    protected EventBus getDomainEventBus() {
        return domainEventBus;
    }
}
