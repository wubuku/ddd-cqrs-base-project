package com.simplepersoncrud;

import com.google.common.collect.Lists;
import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

import static com.simplepersoncrud.testdata.PersonMaker.*;
import static com.natpryce.makeiteasy.MakeItEasy.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IPersonFinder iPersonFinder;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Rollback()
    public void testSavePersonDetails() throws PersonCreationException {
        Person person = make(a(Person));

        Long personId = personService.createPerson(person);
        person = personService.getPersonWithId(personId);

        Assert.notNull(personService);
        Assert.notNull(personId);
        Assert.notNull(person.getVersion());
    }

    @Test
    public void testDeletePersonDetails() throws PersonCreationException {
        Person person = make(a(Person));
        Long personId = personService.createPerson(person);

        personService.deletePerson(personId);

        Assert.isNull(personService.getPersonWithId(personId));
    }

    @Test
    public void testFindPeopleDetails() throws PersonCreationException {
        List<PersonDetailsDto> expectedPeople = Lists.newArrayList(new PersonDetailsDto("Sudarshan",1L),new PersonDetailsDto("Sudarshan",2L));
        Long personId = personService.createPerson(make(a(Person)));
        personId = personService.createPerson(make(a(Person)));

        List<PersonDetailsDto> actualPeopleDetails = iPersonFinder.findAllPeople();

        Assert.notEmpty(actualPeopleDetails);
        Assert.isTrue(expectedPeople.equals(actualPeopleDetails));
    }


}
