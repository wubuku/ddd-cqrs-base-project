package com.simplepersoncrud;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.librarymanagement.domain.Member;
import com.librarymanagement.domain.MemberBuilder;
import com.librarymanagement.presentation.dto.MemberDto;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import com.simplepersoncrud.application.commands.PersonRegistrationCommand;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.SimplePerson;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import com.simplepersoncrud.testdata.DummyDisplayMessages;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.infrastructure.exception.DisplayableException;
import org.nthdimenzion.presentation.exception.PresentationDecoratedExceptionHandler;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.nthdimenzion.security.application.services.UserService;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.testdata.SecurityDetailsMaker;
import org.nthdimenzion.testdata.TestUserDetails;
import org.nthdimenzion.testinfrastructure.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

public class PersonTest extends AbstractTest {

    @Autowired
    private IPersonFinder iPersonFinder;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ILibraryFinder libraryFinder;

    @Autowired
    private MemberBuilder memberBuilder;


    @AfterClass
    public static void onTimeTearDown(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void testPersonRegistration() throws PersonCreationException {
        Assert.notNull(commandBus);

        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand("Sudarshan"));
        Assert.notNull(actualId);

        SimplePerson person = personRepository.getPersonWithId(actualId);

        Assert.isTrue(actualId == person.getId());
    }


    @Test(expected = DisplayableException.class)
    public void testCreatePersonWithLongLengthName() {
        IDisplayMessages displayMessages = new DummyDisplayMessages();
        presentationDecoratedExceptionHandler.setDisplayMessages(displayMessages);
        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("SudarshanSreenivasan")));

        Assert.isTrue(presentationDecoratedExceptionHandler.isExceptionHandled() ==  false);
    }

    @Test
    public void testCreatePersonHavingNameWithSpaces() {
        IDisplayMessages displayMessages = new DummyDisplayMessages();
        presentationDecoratedExceptionHandler.setDisplayMessages(displayMessages);

        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("Sud Sr")));
        Assert.isNull(actualId);
        Assert.isTrue("Sud Sr is a invalid Person name".equals(displayMessages.toString()));
    }


    @Test
    public void testDeletePersonDetails() throws PersonCreationException {
        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("Sudarshan")));

        commandBus.send(new UnRegisterCommand(Sets.newHashSet(actualId)));

        Assert.isNull(personRepository.getPersonWithId(actualId));
    }


    @Test
    public void testFindPeopleDetails() throws PersonCreationException {
        List<PersonDetailsDto> expectedPeople = Lists.newArrayList(new PersonDetailsDto("Sudarshan2", 2L),new PersonDetailsDto("Sudarshan1", 1L));
        commandBus.send(new PersonRegistrationCommand(("Sudarshan1")));
        commandBus.send(new PersonRegistrationCommand(("Sudarshan2")));

        List<PersonDetailsDto> actualPeopleDetails = iPersonFinder.findAllPeople();

        Assert.notEmpty(actualPeopleDetails);
//        Assert.isTrue(expectedPeople.equals(actualPeopleDetails));
    }

    @Test
    public void testUpcomingBirthDays(){
        Member member = memberBuilder.createMember("Su", "Sree", DateTime.now()).build();

        Long memberId = crudDao.add(member);
        List<MemberDto> memberDtos = libraryFinder.upcomingBirthDays();
        Assert.notEmpty(memberDtos);
        Assert.notNull(memberDtos.get(0).dateOfBirth);
        Assert.isTrue(memberDtos.get(0).dateOfBirth.getMonthOfYear()== DateTime.now().getMonthOfYear());
    }
}
