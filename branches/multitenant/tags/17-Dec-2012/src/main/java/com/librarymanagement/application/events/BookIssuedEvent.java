package com.librarymanagement.application.events;

import com.librarymanagement.domain.BookId;
import org.nthdimenzion.ddd.domain.IDomainEvent;

public class BookIssuedEvent implements IDomainEvent{
    public final BookId bookId;
    public final Long memberId;

    public BookIssuedEvent(BookId bookId, Long memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }
}
