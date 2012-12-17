package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.Interval;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.MI;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@MI
@AggregateRoot
@Entity
public class BookLending extends BaseAggregateRoot {
    private Book book;
    private Member member;
    private Interval lendingInterval;
    private BookLendingId bookLendingId;

    BookLending() {
        lendingInterval = Interval.start();
    }

    public BookLending(BookLendingId bookLendingId,Book book, Member member) {
        this();
        this.bookLendingId = bookLendingId;
        this.book = book;
        this.member = member;

    }

    @Embedded
    public BookLendingId getBookLendingId() {
        return bookLendingId;
    }

    void setBookLendingId(BookLendingId bookLendingId) {
        this.bookLendingId = bookLendingId;
    }

    @ManyToOne
    Book getBook() {
        return book;
    }

    void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    Member getMember() {
        return member;
    }

    void setMember(Member member) {
        this.member = member;
    }

    @Embedded
    Interval getLendingInterval() {
        return lendingInterval;
    }

    void setLendingInterval(Interval lendingInterval) {
        this.lendingInterval = lendingInterval;
    }

    @Transient
    public boolean isBookLendingCompleted() {
        return lendingInterval.isCompleted();
    }
    @Transient
    public void extendDueDateBy(int days) {
        lendingInterval = lendingInterval.extendThruDateByDays(days);
    }

    @Transient
    public void bookReturned(){
        lendingInterval = lendingInterval.complete();
    }
    
    public boolean isForSameMemberAndBook(Long memberId, BookId bookId){
        return book.getBookId().equals(bookId) && member.getId().equals(memberId);
    }
}
