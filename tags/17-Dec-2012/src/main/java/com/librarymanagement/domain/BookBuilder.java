package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.joda.money.Money;
import org.nthdimenzion.ddd.domain.AbstractDomainFactory;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;

@DomainFactory
public class BookBuilder extends AbstractDomainFactory{

    private Book book;

    public BookBuilder createBook(String name, String isbn, Money cost) {
        BookId bookId = new BookId(idGenerator.nextId());
        book = new Book(name, isbn, bookId, cost);
        return this;
    }

    public BookBuilder purchaseCopies(Integer noOfCopiesPurchased) {
        book.purchaseCopies(noOfCopiesPurchased);
        return this;
    }


    public BookBuilder withAuthors(String authors) {
        book.setAuthors(authors);
        return this;
    }

    public Book build() {
        validate();
        injectDependencies();
        return book;
    }

    private void validate() {
        Preconditions.checkNotNull(book.getName());
        Preconditions.checkNotNull(book.getIsbn());
    }

    private void injectDependencies() {
        book.setDomainEventBus(domainEventBus);
    }

}
