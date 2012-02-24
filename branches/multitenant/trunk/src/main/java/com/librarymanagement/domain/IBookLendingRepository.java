package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IBookLendingRepository {

    Long startBookLending(BookLending bookLending);

    Long completeBookLending(BookLending bookLending);

    BookLending findOpenBookLending(Book book, Member member);

    BookLending getBookLendingFromId(Long id);

}
