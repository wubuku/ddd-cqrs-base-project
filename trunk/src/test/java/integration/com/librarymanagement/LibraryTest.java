package com.librarymanagement;

import com.librarymanagement.domain.*;
import com.librarymanagement.presentation.dto.LibrarySummaryDto;
import com.librarymanagement.presentation.queries.BookQueries;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.testinfrastructure.testdata.TestUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml", "classpath:/testContext.xml", "classpath:/queryContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class LibraryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BookBuilder bookBuilder;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private SystemUser systemUser;

    @Autowired
    private BookQueries bookQueries;

    @Autowired
    private ILibraryFinder bookFinder;

    @Autowired
    private ICrud crudDao;

    @Autowired
    private MemberBuilder memberBuilder;

    @Test
    public void testPurchaseBook() {
        systemUser.uses(new TestUserDetails());
        System.out.println(systemUser);
        Book javaPersistence = bookBuilder.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD, 1000)).withAuthors("Sudarshan").withCopies(10).build();
//        Book eventSourcing = bookBuilder.createBook("Event Sourcing", "008", Money.of(CurrencyUnit.USD, 2000)).withAuthors("Greg Young").withCopies(2).build();

        Long id = bookRepository.purchaseBook(javaPersistence);
//        bookRepository.registerBook(eventSourcing);
        hibernateTemplate.flush();
        hibernateTemplate.evict(javaPersistence);
//        hibernateTemplate.evict(eventSourcing);

        List<Map<String,?>> books = bookFinder.findAllBooks();

        Map<String,?> bookResult = books.get(0);

        assertEquals(1,books.size());
        assertNotNull(bookResult);
        assertNotNull(bookResult.get("id"));
        assertNotNull(bookResult.get("amount"));
        assertTrue(((BigDecimal)bookResult.get("amount")).compareTo(BigDecimal.valueOf(1000)) == 0);
    }

    @Test
    public void testFindBookCount() {
        Book book = bookBuilder.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD, 1000)).withCopies(1).build();
        Long id = bookRepository.purchaseBook(book);
        hibernateTemplate.flush();

        LibrarySummaryDto librarySummary = bookQueries.getLibrarySummary();

        assertTrue(new Integer("1").equals(librarySummary.getNumberOfBooks()));
    }


    @Test
    public void findMembers() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();
        Member member1 = memberBuilder.createMember("NthDimen", "S", DateTime.now()).withMiddleName("Hello").build();

        member = crudDao.update(member);
        member1 = crudDao.update(member1);
        hibernateTemplate.flush();
        hibernateTemplate.evict(member);
        hibernateTemplate.evict(member1);

        assertNotNull(crudDao.getAll(Member.class));
    }

    @Test
    public void createMember() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();

        member = crudDao.update(member);

        assertNotNull(member.getId());
    }

    @Test
    public void temp(){
        System.out.println(bookFinder.findAllBooksWithMember(Long.valueOf("1")));
    }
}

