package com.librarymanagement.domain;

import com.google.common.collect.Lists;
import com.librarymanagement.application.events.BookIssuedEvent;
import com.librarymanagement.application.events.BookReturnedEvent;
import com.librarymanagement.domain.error.MemberAlreadyBorrowedBookException;
import com.librarymanagement.domain.error.NotEnoughCopiesException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.INamed;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.PPT;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.List;

@AggregateRoot
@PPT
@Entity
public class Book extends BaseAggregateRoot implements INamed{
    private String name;
    private String isbn;
    private Integer availableCopies;
    private Integer totalCopies;
    private Money cost;
    private String authors;
    private BookId bookId;

    Book() {
        totalCopies = 0;
        availableCopies = totalCopies;

    }

    public Book(String name, String isbn, BookId bookId, Money cost) {
        this();
        this.name = name;
        this.isbn = isbn;
        this.cost = cost;
        this.bookId = bookId;
    }

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getIsbn() {
        return isbn;
    }

    void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    Integer getAvailableCopies() {
        return availableCopies;
    }

    void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    String getAuthors() {
        return authors;
    }

    void setAuthors(String authors) {
        this.authors = authors;
    }

    @Embedded
    public BookId getBookId() {
        return bookId;
    }

    void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    @Columns(columns = {@Column(name = "AMOUNT", precision = 64, scale = 2), @Column(name = "CURRENCY_CODE")})
    @Type(type = "org.nthdimenzion.ddd.domain.sharedkernel.MoneyType")
//    @NotNull
    Money getCost() {
        return cost;
    }

    void setCost(Money cost) {
        this.cost = cost;
    }

    Integer getTotalCopies() {
        return totalCopies;
    }

    void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void lend(Long memberId, List<BookLending> booksAlreadyWithMember) throws NotEnoughCopiesException, MemberAlreadyBorrowedBookException {
        checkIfMemberAlreadyHasBook(memberId,booksAlreadyWithMember) ;
        if (availableCopies <= 0) {
            throw new NotEnoughCopiesException(new ErrorDetails.Builder("100").args(Lists.<String>newArrayList(availableCopies.toString(), name)).build());
        }
        availableCopies--;
        domainEventBus.raise(new BookIssuedEvent(bookId, memberId));
    }

    private void checkIfMemberAlreadyHasBook(Long memberId, List<BookLending> booksAlreadyWithMember) throws MemberAlreadyBorrowedBookException {
        for(BookLending bookLending : booksAlreadyWithMember){
            if(memberHasBook(memberId, bookLending)) {
                throw new MemberAlreadyBorrowedBookException(new ErrorDetails.Builder("101").args(Lists.<String>newArrayList(memberId.toString(), bookLending.getBook().getName())).build());
            }
        }
    }

    private boolean memberHasBook(Long memberId, BookLending bookLending) {
        return bookLending.isForSameMemberAndBook(memberId, bookId);
    }

    public void rentalExpiry(Long memberId) {
        availableCopies++;
        domainEventBus.raise(new BookReturnedEvent(bookId, memberId));
    }

    public void purchaseCopies(Integer noOfCopiesPurchased) {
        if (noOfCopiesPurchased == null)
            noOfCopiesPurchased = 1;
        availableCopies = availableCopies + noOfCopiesPurchased;
        totalCopies = totalCopies + noOfCopiesPurchased;
    }

    public void sellCopies(Integer noOfCopiesSold) throws NotEnoughCopiesException {
        if (noOfCopiesSold > availableCopies)
            throw new NotEnoughCopiesException(new ErrorDetails.Builder("100").args(Lists.<String>newArrayList(availableCopies.toString(), name)).build());
        availableCopies = availableCopies - noOfCopiesSold;
        totalCopies = totalCopies - noOfCopiesSold;
    }


    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            Book obj = (Book) o;
            return new EqualsBuilder().append(bookId, obj.bookId).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(bookId);
    }
}
