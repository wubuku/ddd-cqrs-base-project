package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IBookRepository {

    Long purchaseBook(Book book);

    Book getBookFromId(Long id);

    Book updateBook(Book book);

    Book geBookWithUid(BookId bookId);

    Book issueBook(Book book);

    Book returnBook(Book book);
}
