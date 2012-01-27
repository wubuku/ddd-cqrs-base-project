package com.librarymanagement.application.commands.handlers;

import com.librarymanagement.application.commands.IssueBooksCommand;
import com.librarymanagement.application.commands.PurchaseBookCommand;
import com.librarymanagement.application.commands.ReturnBooksCommand;
import com.librarymanagement.application.commands.UpdateBookCommand;
import com.librarymanagement.domain.*;
import com.librarymanagement.domain.error.NotEnoughCopies;
import org.nthdimenzion.cqrs.command.AbstractCommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.nthdimenzion.crud.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;

@CommandHandler
public class BookCommandHandler extends AbstractCommandHandler {

    @Autowired
    private BookBuilder bookBuilder;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ICrud crudDao;

    public Long purchaseBook(PurchaseBookCommand purchaseBookCommand) {
        Book book = bookBuilder.createBook(purchaseBookCommand.name, purchaseBookCommand.isbn, purchaseBookCommand.cost).withAuthors(purchaseBookCommand.authors).purchaseCopies(purchaseBookCommand.copies).build();
        Long bookId = bookRepository.purchaseBook(book);
        return bookId;
    }

    public Book updateBook(UpdateBookCommand updateBookCommand) {
        Book book = bookRepository.getBookFromId(updateBookCommand.id);
        book = populate(updateBookCommand,book);
        book = bookRepository.updateBook(book);
        return book;
    }

    public boolean issueBook(IssueBooksCommand bookIssueCommand) throws NotEnoughCopies {
        Set<BookId> bookIds = bookIssueCommand.bookIds;
        Member member = crudDao.find(Member.class, bookIssueCommand.memberId);
        for (BookId bookId : bookIds) {
            Book book = bookRepository.geBookWithUid(bookId);
            book.lend(member.getId());
            bookRepository.lend(book);
        }
        return success;
    }

    public boolean returnBook(ReturnBooksCommand bookReturnCommand) {
        Set<BookId> bookIds = bookReturnCommand.bookIds;
        Member member = crudDao.find(Member.class, bookReturnCommand.memberId);
        for (BookId bookId : bookIds) {
            Book book = bookRepository.geBookWithUid(bookId);
            book.rentalExpiry(member.getId());
            bookRepository.rentalExpiry(book);
        }
        return success;
    }

}
