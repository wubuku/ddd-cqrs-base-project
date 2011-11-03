package org.nthdimenzion.ddd;

import com.google.common.eventbus.EventBus;
import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class InjectionTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IIdGenerator idGenerator;

    @Autowired
    @Qualifier("exceptionEventBus")
    private EventBus exceptionEventBus;

    @Autowired
    @Qualifier("domainEventBus")
    private EventBus domainEventBus;

    @Autowired
    @Qualifier("applicationEventBus")
    private EventBus applicationEventBus;



    @Test
    public void testInjections() throws PersonCreationException {
        Assert.notNull(exceptionEventBus);
        Assert.notNull(domainEventBus);
        Assert.notNull(applicationEventBus);
    }

     @Test
    public void testIdGenerator() throws PersonCreationException {
        Assert.notNull(idGenerator);
        Assert.notNull(idGenerator.nextId());
    }

}
