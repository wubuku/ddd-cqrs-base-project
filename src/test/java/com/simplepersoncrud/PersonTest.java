package com.simplepersoncrud;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.simplepersoncrud.application.commands.CreatePersonCommand;
import com.simplepersoncrud.application.commands.DeletePersonCommand;
import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.ddd.infrastructure.exception.DisplayableException;
import org.nthdimenzion.presentation.exception.IExceptionHandler;
import org.nthdimenzion.presentation.exception.PresentationDecoratedExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/queryContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier("simpleCommandBus")
    private ICommandBus commandBus;

    @Autowired
    private IPersonFinder iPersonFinder;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private PersonFactory personFactory;

    @Autowired
    @Qualifier("presentationDecoratedExceptionHandler")
    private IExceptionHandler presentationDecoratedExceptionHandler;

    @Test
    public void testSavePersonDetails() throws PersonCreationException {
        Assert.notNull(commandBus);

        Long actualId = (Long) commandBus.send(new CreatePersonCommand("Sudarshan"));
        Person person = personRepository.getPersonWithId(actualId);

        Assert.isTrue(1L == actualId);
        Assert.isTrue("Sudarshan".equals(person.getName()));
        Assert.notNull(person.getVersion());
        Assert.notNull(person.getDomainEventBus());
    }


    @Test(expected = DisplayableException.class)
    public void testCreatePersonWithLongLengthName() {

        Long actualId = (Long) commandBus.send(new CreatePersonCommand("SudarshanSreenivasan"));
        Assert.isTrue(presentationDecoratedExceptionHandler.isExceptionHandled() == false);
    }

    @Test
    public void testCreatePersonHavingNameWithSpaces() {
        Long actualId = (Long) commandBus.send(new CreatePersonCommand("Sud Sr"));

        Assert.isNull(actualId);
        Assert.isTrue(presentationDecoratedExceptionHandler.isExceptionHandled());
    }


    @Test
    public void testDeletePersonDetails() throws PersonCreationException {
        Long actualId = (Long) commandBus.send(new CreatePersonCommand("Sudarshan"));

        commandBus.send(new DeletePersonCommand(Sets.newHashSet(actualId)));

        Assert.isNull(personRepository.getPersonWithId(actualId));
    }


    @Test
    public void testFindPeopleDetails() throws PersonCreationException {
        List<PersonDetailsDto> expectedPeople = Lists.newArrayList(new PersonDetailsDto("Sudarshan1", 1L), new PersonDetailsDto("Sudarshan2", 2L));
        commandBus.send(new CreatePersonCommand("Sudarshan1"));
        commandBus.send(new CreatePersonCommand("Sudarshan2"));

        List<PersonDetailsDto> actualPeopleDetails = iPersonFinder.findAllPeople();

        Assert.notEmpty(actualPeopleDetails);
        Assert.isTrue(expectedPeople.equals(actualPeopleDetails));
    }
}
