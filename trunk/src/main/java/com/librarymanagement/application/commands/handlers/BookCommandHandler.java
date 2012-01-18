package com.librarymanagement.application.commands.handlers;

import com.librarymanagement.application.commands.PurchaseBookCommand;
import com.librarymanagement.application.commands.UpdateBookCommand;
import com.librarymanagement.domain.Book;
import com.librarymanagement.domain.BookBuilder;
import com.librarymanagement.domain.IBookRepository;
import org.nthdimenzion.cqrs.command.AbstractCommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
@CommandHandler
public class BookCommandHandler extends AbstractCommandHandler{

    @Autowired
    private BookBuilder bookBuilder;

    @Autowired
    private IBookRepository bookRepository;

    public Long purchaseBook(PurchaseBookCommand purchaseBookCommand){
        Book book = bookBuilder.createBook(purchaseBookCommand.name,purchaseBookCommand.isbn,purchaseBookCommand.cost).withAuthors(purchaseBookCommand.authors).withCopies(purchaseBookCommand.copies).build();
        Long bookId = bookRepository.purchaseBook(book);
        return bookId;
    }

    public Book updateBook(UpdateBookCommand updateBookCommand){
        Book book = bookRepository.getBookFromId(updateBookCommand.id);
        modelMapper.map(updateBookCommand,book);
        book = bookRepository.updateBook(book);
        return book;
    }

}
