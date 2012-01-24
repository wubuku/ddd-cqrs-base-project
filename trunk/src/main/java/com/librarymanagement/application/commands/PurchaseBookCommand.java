package com.librarymanagement.application.commands;

import org.joda.money.Money;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class PurchaseBookCommand implements ICommand{

    public String name;
    public String isbn;
    public Integer copies;
    public Money cost;
    public String authors;

    public void setName(String name) {
        this.name = name;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
