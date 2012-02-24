package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IBookRepository {

    Long registerBook(Book book);

    Book getBookFromId(Long id);

    Book updateBook(Book book);

    Book geBookWithUid(BookId bookId);

    Book lend(Book book);

    Book rentalExpiry(Book book);

    Book purchaseCopies(Book book);

    Book sellCopies(Book book);
}
