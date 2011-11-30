package integration.com.librarymanagement;

import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookFactory;
import com.librarymanagement.domain.IBookRepository;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BookFactory bookFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private IBookRepository bookRepository;

    @Test
    public void testPurchaseBook() {
        Book book = bookFactory.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD,1000));

        Long id = bookRepository.purchaseBook(book);
        hibernateTemplate.flush();
        hibernateTemplate.evict(book);

        book = bookRepository.getBookFromId(id);
        assertNotNull(book);
        assertNotNull(book.getId());
        assertNotNull(book.getCost());
        assertTrue(book.getCost().getCurrencyUnit().equals(CurrencyUnit.USD));
        assertTrue(book.getCost().getAmount().compareTo(BigDecimal.valueOf(1000))==0);
    }

}
