package org.nthdimenzion.ddd.domain;

import org.nthdimenzion.ddd.infrastructure.IEventBus;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;



@MappedSuperclass
public abstract class BaseAggregateRoot extends IdGeneratingBaseEntity {

    private IEventBus domainEventBus;

    public void setDomainEventBus(IEventBus domainEventBus) {
        if (this.domainEventBus != null)
            throw new IllegalStateException("DomainEventBus is already set! Probably You have logical error in code");
        this.domainEventBus = domainEventBus;
    }

    @Transient
    protected IEventBus getDomainEventBus() {
        return domainEventBus;
    }
}
