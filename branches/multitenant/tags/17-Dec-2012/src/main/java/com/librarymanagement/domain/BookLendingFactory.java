package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.nthdimenzion.ddd.domain.AbstractDomainFactory;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;

@DomainFactory
public class BookLendingFactory extends AbstractDomainFactory{

    public BookLending create(Book book,Member member){
        Preconditions.checkNotNull(book);
        Preconditions.checkNotNull(member);
        BookLendingId bookLendingId = new BookLendingId(idGenerator.nextId());
        BookLending bookLending = new BookLending(bookLendingId,book,member);
        return injectDependencies(bookLending);
    }

    private BookLending injectDependencies(BookLending bookLending) {
        bookLending.setDomainEventBus(domainEventBus);
        return bookLending;
    }
}
