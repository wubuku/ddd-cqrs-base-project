package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

import java.util.List;

@DomainRepository
public interface IBookLendingRepository {

    Long startBookLending(BookLending bookLending);

    Long completeBookLending(BookLending bookLending);

    BookLending findOpenBookLending(Book book, Member member);

    BookLending getBookLendingFromId(Long id);

    List<BookLending> findAllBooksWithMember(Member member);
}
