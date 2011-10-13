package com.simplepersoncrud;

import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import static com.simplepersoncrud.testdata.PersonMaker.*;
import static com.natpryce.makeiteasy.MakeItEasy.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IPersonService personService;

    @Test
    public void testSavePersonDetails(){
        Person person = make(a(Person));
        Long personId = personService.savePerson(person);
        person = personService.getPersonWithId(personId);
        Assert.notNull(personService);
        Assert.notNull(personId);
        Assert.notNull(person.getVersion());
    }
}
