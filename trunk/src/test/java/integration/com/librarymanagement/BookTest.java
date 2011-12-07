package integration.com.librarymanagement;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookFactory;
import com.librarymanagement.domain.IBookRepository;
import com.librarymanagement.presentation.IBookFinder;
import com.librarymanagement.presentation.dto.BookDetailsDto;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.testinfrastructure.testdata.TestUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/testContext.xml", "classpath:/queryContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BookFactory bookFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private SystemUser systemUser;

    @Autowired
    private IBookFinder bookFinder;

    @Test
    public void testPurchaseBook() {
        systemUser.uses(new TestUserDetails());
        System.out.println(systemUser);
        Book book = bookFactory.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD,1000));

        Long id = bookRepository.purchaseBook(book);
        hibernateTemplate.flush();
        hibernateTemplate.evict(book);

        book = bookRepository.getBookFromId(id);
        BookDetailsDto bookDetailsDto = bookFinder.findAllBooks().get(0);

        assertNotNull(book);
        assertNotNull(book.getId());
        assertNotNull(bookDetailsDto.getAmount());
        assertTrue(bookDetailsDto.getAmount().compareTo(BigDecimal.valueOf(1000))==0);
    }

}
