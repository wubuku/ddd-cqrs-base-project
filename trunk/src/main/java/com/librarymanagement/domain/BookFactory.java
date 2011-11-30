package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.joda.money.Money;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@DomainFactory
public class BookFactory {
    @Autowired
    private IIdGenerator idGenerator;

    public Book createBook(String name,String isbn,Money cost) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(isbn);
        BookId bookId = new BookId(idGenerator.nextId());
        return new Book(name,isbn,bookId,cost);
    }

}
