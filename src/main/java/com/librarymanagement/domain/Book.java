package com.librarymanagement.domain;

import com.google.common.collect.Lists;
import com.librarymanagement.domain.error.NotEnoughCopiesInLibrary;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.PPT;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AggregateRoot
@PPT
@Entity
public class Book extends BaseAggregateRoot{
    private String name;
    private String isbn;
    private Integer copies;
    private Money cost;
    private String authors;
    private BookId bookId;

    Book() {
        copies = 0;
    }

    public Book(String name, String isbn,BookId bookId,Money cost) {
        this.name = name;
        this.isbn = isbn;
        this.cost = cost;
        this.bookId = bookId;
        copies = 0;
    }

    String getName() {
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

    Integer getCopies() {
        return copies;
    }

    void setCopies(Integer copies) {
        this.copies = copies;
    }

    String getAuthors() {
        return authors;
    }

    public void purchaseCopies(Integer noOfCopiesPurchased){
        copies = copies + noOfCopiesPurchased;
    }

    public void sellCopies(Integer noOfCopiesSold) throws NotEnoughCopiesInLibrary {
        Integer copies = new Integer(this.copies.intValue());
        copies = copies - noOfCopiesSold;
        if(copies < 0){
            throw new NotEnoughCopiesInLibrary(new ErrorDetails.Builder("100").args(Lists.<String>newArrayList(copies.toString(),name)).build());
        }
        this.copies = copies;
    }

    void setAuthors(String authors) {
        this.authors = authors;
    }

    public void borrowBook() throws NotEnoughCopiesInLibrary {
    if(copies <=0){
        throw new NotEnoughCopiesInLibrary(new ErrorDetails.Builder("100").args(Lists.<String>newArrayList(copies.toString(),name)).build());
    }
    copies--;
    }

    public void returnBook(){
        copies++;
    }

    @Embedded
    public BookId getBookId() {
        return bookId;
    }

    void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    @Columns(columns = { @Column(name = "AMOUNT",precision = 64,scale = 2),@Column(name = "CURRENCY_CODE")})
    @Type(type= "org.nthdimenzion.ddd.domain.sharedkernel.MoneyType")
    @NotNull
    Money getCost() {
        return cost;
    }

    void setCost(Money cost) {
        this.cost = cost;
    }
}
