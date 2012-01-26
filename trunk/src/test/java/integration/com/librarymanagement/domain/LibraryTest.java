package com.librarymanagement.domain;

import com.google.common.collect.Sets;
import com.librarymanagement.application.commands.IssueBooksCommand;
import com.librarymanagement.application.commands.ReturnBooksCommand;
import com.librarymanagement.application.commands.PurchaseBookCommand;
import com.librarymanagement.application.commands.UpdateBookCommand;
import com.librarymanagement.presentation.dto.LibrarySummaryDto;
import com.librarymanagement.presentation.queries.BookQueries;
import com.librarymanagement.presentation.queries.ILibraryFinder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Test;
import org.nthdimenzion.testdata.TestUserDetails;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LibraryTest extends AbstractTestFacilitator {

    @Autowired
    private BookBuilder bookBuilder;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private BookQueries bookQueries;

    @Autowired
    private ILibraryFinder bookFinder;

    @Autowired
    private MemberBuilder memberBuilder;

    @Autowired
    private IBookLendingRepository bookLendingRepository;


    @Test
    public void testPurchaseBook() {
        systemUser.uses(new TestUserDetails());
        Book javaPersistence = bookBuilder.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD, 1000)).withAuthors("Sudarshan").purchaseCopies(10).build();
//        Book eventSourcing = bookBuilder.createBook("Event Sourcing", "008", Money.of(CurrencyUnit.USD, 2000)).withAuthors("Greg Young").withCopies(2).build();

        Long id = bookRepository.purchaseBook(javaPersistence);
//        bookRepository.registerBook(eventSourcing);
        hibernateTemplate.flush();
        hibernateTemplate.evict(javaPersistence);
//        hibernateTemplate.evict(eventSourcing);

        List<Map<String, ?>> books = bookFinder.findAllBooks();

        Map<String, ?> bookResult = books.get(0);

        assertEquals(1, books.size());
        assertNotNull(bookResult);
        assertNotNull(bookResult.get("id"));
        assertNotNull(bookResult.get("amount"));
        assertTrue(((BigDecimal) bookResult.get("amount")).compareTo(BigDecimal.valueOf(1000)) == 0);
    }

    @Test
    public void testFindBookCount() {
        Book book = bookBuilder.createBook("Java Persistence", "007", Money.of(CurrencyUnit.USD, 1000)).purchaseCopies(1).build();
        Long id = bookRepository.purchaseBook(book);
        hibernateTemplate.flush();

        LibrarySummaryDto librarySummary = bookQueries.getLibrarySummary();

        assertTrue(new Integer("1").equals(librarySummary.getNumberOfBooks()));
    }


    @Test
    public void findMembers() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();
        Member member1 = memberBuilder.createMember("NthDimen", "S", DateTime.now()).withMiddleName("Hello").build();

        crudDao.add(member);
        crudDao.add(member1);
        hibernateTemplate.flush();
        hibernateTemplate.evict(member);
        hibernateTemplate.evict(member1);

        assertNotNull(crudDao.getAll(Member.class));
    }

    @Test
    public void createMember() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();

        Long memberId = crudDao.add(member);

        assertNotNull(memberId);
    }

    @Test
    public void testBookPurchase() {
        PurchaseBookCommand purchaseBookCommand = createPurchaseBookCommand();

        Long bookId = (Long) commandBus.send(purchaseBookCommand);

        Assert.notNull(bookId);
    }

    @Test
    public void testBookUpdate() {
        PurchaseBookCommand purchaseBookCommand = createPurchaseBookCommand();
        Long bookId = (Long) commandBus.send(purchaseBookCommand);

        UpdateBookCommand updateBookCommand = new UpdateBookCommand();
        updateBookCommand.id = bookId;
        updateBookCommand.isbn = "008";
        updateBookCommand.name = "Bum";

        Book book = (Book) commandBus.send(updateBookCommand);
        Assert.notNull(book);
        Assert.isTrue(bookId.equals(book.getId()));
        Assert.isTrue("Bum".equals(book.toString()));

    }

    private PurchaseBookCommand createPurchaseBookCommand() {
        PurchaseBookCommand purchaseBookCommand = new PurchaseBookCommand();
        purchaseBookCommand.name = "SCJP";
        purchaseBookCommand.authors = "Sudarshan";
        purchaseBookCommand.isbn = "007";
        purchaseBookCommand.copies = 1;
        purchaseBookCommand.cost = Money.parse("INR 2000");
        return purchaseBookCommand;
    }

    @Test
    public void testIssueBook() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();
        Long memberId = crudDao.add(member);
        PurchaseBookCommand purchaseBookCommand = createPurchaseBookCommand();
        Long bookId = (Long) commandBus.send(purchaseBookCommand);
        Book book = bookRepository.getBookFromId(bookId);

        IssueBooksCommand bookIssueCommand = new IssueBooksCommand(Sets.newHashSet(book.getBookId()), memberId);
        commandBus.send(bookIssueCommand);
        hibernateTemplate.flush();

        BookLending bookLending = bookLendingRepository.findOpenBookLending(book,member);
        Assert.notNull(bookLending);
        Assert.isTrue(0 == book.getAvailableCopies());
        Assert.isTrue(1 == book.getTotalCopies());
    }

    @Test
    public void testIssueUnavailableBook() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();
        Long memberId = crudDao.add(member);
        PurchaseBookCommand purchaseBookCommand = createPurchaseBookCommand();
        purchaseBookCommand.copies = 0;
        Long bookId = (Long) commandBus.send(purchaseBookCommand);
        Book book = bookRepository.getBookFromId(bookId);

        IssueBooksCommand bookIssueCommand = new IssueBooksCommand(Sets.newHashSet(book.getBookId()), memberId);
        commandBus.send(bookIssueCommand);
        hibernateTemplate.flush();

        BookLending bookLending = bookLendingRepository.findOpenBookLending(book,member);

        Assert.isTrue(presentationDecoratedExceptionHandler.isExceptionHandled());
        Assert.isNull(bookLending);
        Assert.isTrue(0 == book.getAvailableCopies());
        Assert.isTrue(0 == book.getTotalCopies());
    }

    @Test
    public void testReturnBook() {
        Member member = memberBuilder.createMember("Sudarshan", "S", DateTime.now()).withMiddleName("Hello").build();
        Long memberId = crudDao.add(member);
        PurchaseBookCommand purchaseBookCommand = createPurchaseBookCommand();
        Long bookId = (Long) commandBus.send(purchaseBookCommand);
        Book book = bookRepository.getBookFromId(bookId);
        IssueBooksCommand bookIssueCommand = new IssueBooksCommand(Sets.newHashSet(book.getBookId()), memberId);
        commandBus.send(bookIssueCommand);

        ReturnBooksCommand bookReturnCommand = new ReturnBooksCommand(Sets.newHashSet(book.getBookId()), memberId);
        commandBus.send(bookReturnCommand);

        hibernateTemplate.flush();
        List bookLendings = bookFinder.findBookLendings(memberId,bookId);
        Map<String,?> firstBookLending = (Map<String,?>)bookLendings.get(0);
        BookLending bookLending = bookLendingRepository.getBookLendingFromId((Long)firstBookLending.get("id"));
        List<Map<String,?>> booksWithMember = bookFinder.findAllBooksWithMember(memberId);

        Assert.isTrue(1 == book.getAvailableCopies());
        Assert.isTrue(1 == book.getTotalCopies());
        Assert.isTrue(booksWithMember.size() == 0);
        Assert.notNull(bookLendings);
        Assert.notNull(bookLending);
        Assert.isTrue(true == bookLending.isBookLendingCompleted());

    }
}

