package org.nthdimenzion.ddd;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.simplepersoncrud.testdata.PersonMaker.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InjectionTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IPersonService personService;

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

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonFactory personFactory;

    @Test
    public void testInjections() throws PersonCreationException {
        Assert.notNull(personService);
        Assert.notNull(exceptionEventBus);
        Assert.notNull(domainEventBus);
        Assert.notNull(applicationEventBus);
        Assert.notNull(personRepository);
        Assert.notNull(personFactory);

    }

     @Test
    public void testIdGenerator() throws PersonCreationException {
        Assert.notNull(idGenerator);
        Assert.notNull(idGenerator.nextId());
    }

}
