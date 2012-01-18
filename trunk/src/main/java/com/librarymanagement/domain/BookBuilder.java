package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.joda.money.Money;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@DomainFactory
public class BookBuilder {

    @Autowired
    private IIdGenerator idGenerator;

    private Book book;

    @Autowired
    @Qualifier("domainEventBus")
    private IEventBus domainEventBus;

    public BookBuilder createBook(String name, String isbn, Money cost) {
        BookId bookId = new BookId(idGenerator.nextId());
        book = new Book(name, isbn, bookId, cost);
        return this;
    }

    public BookBuilder withCopies(Integer noOfCopiesPurchased) {
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
