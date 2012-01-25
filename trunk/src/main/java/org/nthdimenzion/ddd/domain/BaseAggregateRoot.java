package org.nthdimenzion.ddd.domain;

import org.nthdimenzion.ddd.infrastructure.IEventBus;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;



@MappedSuperclass
public abstract class BaseAggregateRoot extends IdGeneratingArcheType {

    @Transient
    protected IEventBus domainEventBus;

    public void setDomainEventBus(IEventBus domainEventBus) {
        if (this.domainEventBus != null){
        //throw new IllegalStateException("DomainEventBus is already set! Probably You have logical error in code");
        logger.warn("DomainEventBus is already set! Probably You have logical error in code or the entity was picked from the cache");
        }
        this.domainEventBus = domainEventBus;
    }

}
