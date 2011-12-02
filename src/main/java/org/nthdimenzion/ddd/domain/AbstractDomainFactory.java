package org.nthdimenzion.ddd.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@DomainFactory
public class AbstractDomainFactory {

    @Autowired
    protected IIdGenerator idGenerator;

    @Autowired
    @Qualifier("domainEventBus")
    protected IEventBus domainEventBus;


}
