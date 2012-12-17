package org.nthdimenzion.ddd;

import com.simplepersoncrud.domain.error.PersonCreationException;
import org.junit.Test;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

public class InjectionTest extends AbstractTestFacilitator {

    @Autowired
    private IIdGenerator idGenerator;

    @Autowired
    @Qualifier("exceptionEventBus")
    private IEventBus exceptionEventBus;

    @Autowired
    @Qualifier("domainEventBus")
    private IEventBus domainEventBus;

    @Autowired
    @Qualifier("applicationEventBus")
    private IEventBus applicationEventBus;



    @Test
    public void testInjections() throws PersonCreationException {
        Assert.notNull(exceptionEventBus);
        Assert.notNull(domainEventBus);
        Assert.notNull(applicationEventBus);
    }

     @Test
    public void testIdGenerator()  {
        Assert.notNull(idGenerator);
        Assert.notNull(idGenerator.nextId());
    }

}
