package integration.com.simplepersoncrud;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.librarymanagement.domain.Member;
import com.librarymanagement.domain.MemberBuilder;
import com.librarymanagement.presentation.dto.MemberDto;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.application.commands.PersonRegistrationCommand;
import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.SimplePerson;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import integration.com.simplepersoncrud.testdata.DummyDisplayMessages;
import integration.org.nthdimenzion.testdata.SecurityDetailsMaker;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.infrastructure.exception.DisplayableException;
import org.nthdimenzion.presentation.exception.PresentationDecoratedExceptionHandler;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

/** @noinspection ALL*/
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
    private PresentationDecoratedExceptionHandler presentationDecoratedExceptionHandler;

    @Autowired
    private ILibraryFinder libraryFinder;

    @Autowired
    private MemberBuilder memberBuilder;

    @Autowired
    private ICrud crudDao;

    @BeforeClass
    public static void onTimeSetUp(){
    TestingAuthenticationToken token = SecurityDetailsMaker.makeTestingAuthenticationToken(new GrantedAuthorityImpl[]{new GrantedAuthorityImpl("ROLE_SUPERADMIN")});
    SecurityContextHolder.getContext().setAuthentication(token);
    }

    @AfterClass
    public static void onTimeTearDown(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    @Rollback
    public void testPersonRegistration() throws PersonCreationException {
        Assert.notNull(commandBus);

        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand("Sudarshan"));
        SimplePerson person = personRepository.getPersonWithId(actualId);

        Assert.isTrue(1L == actualId);
    }


    @Test(expected = DisplayableException.class)
    @Rollback
    public void testCreatePersonWithLongLengthName() {
        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("SudarshanSreenivasan")));

        Assert.isTrue(presentationDecoratedExceptionHandler.isExceptionHandled() == false);
    }

    @Test
    @Rollback
    public void testCreatePersonHavingNameWithSpaces() {
        IDisplayMessages displayMessages = new DummyDisplayMessages();
        presentationDecoratedExceptionHandler.setDisplayMessages(displayMessages);

        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("Sud Sr")));
        Assert.isNull(actualId);
        Assert.isTrue("Sud Sr is a invalid Person name".equals(displayMessages.toString()));
    }


    @Test
    @Rollback
    public void testDeletePersonDetails() throws PersonCreationException {
        Long actualId = (Long) commandBus.send(new PersonRegistrationCommand(("Sudarshan")));

        commandBus.send(new UnRegisterCommand(Sets.newHashSet(actualId)));

        Assert.isNull(personRepository.getPersonWithId(actualId));
    }


    @Test
    @Rollback
    public void testFindPeopleDetails() throws PersonCreationException {
        List<PersonDetailsDto> expectedPeople = Lists.newArrayList(new PersonDetailsDto("Sudarshan2", 2L),new PersonDetailsDto("Sudarshan1", 1L));
        commandBus.send(new PersonRegistrationCommand(("Sudarshan1")));
        commandBus.send(new PersonRegistrationCommand(("Sudarshan2")));

        List<PersonDetailsDto> actualPeopleDetails = iPersonFinder.findAllPeople();
        System.out.println(actualPeopleDetails);

        Assert.notEmpty(actualPeopleDetails);
        Assert.isTrue(expectedPeople.equals(actualPeopleDetails));
    }

    @Test
    @Rollback
    public void testUpcomingBirthDays(){
        Member member = memberBuilder.createMember("Su", "Sree", DateTime.now()).build();

        Long memberId = crudDao.add(member);
        System.out.println("########## " + crudDao.find(Member.class,memberId));
        List<MemberDto> memberDtos = libraryFinder.upcomingBirthDays();
        Assert.notEmpty(memberDtos);
        Assert.notNull(memberDtos.get(0).dateOfBirth);
        System.out.println("memberDtos.get(0).dateOfBirth " + memberDtos.get(0).dateOfBirth);
    }
}
