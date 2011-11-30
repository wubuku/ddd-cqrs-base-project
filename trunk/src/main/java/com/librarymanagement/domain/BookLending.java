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

    public BookLending() {
        lendingInterval = Interval.start();
    }

    public BookLending(Book book, Member member) {
        this();
        this.book = book;
        this.member = member;

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
    public boolean isBookOverDue() {
        return lendingInterval.isCompleted();
    }

    public void extendDueDateBy(int days){
        lendingInterval = lendingInterval.extendThruDateByDays(days);
    }
}
