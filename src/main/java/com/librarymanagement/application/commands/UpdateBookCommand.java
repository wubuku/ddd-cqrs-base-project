package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import org.joda.money.Money;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class UpdateBookCommand implements ICommand{

    public String name;
    public String isbn;
    public Integer copies;
    public Money cost;
    public String authors;
    public Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void validate() {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(isbn);
        Preconditions.checkNotNull(copies);
        Preconditions.checkNotNull(cost);
        Preconditions.checkNotNull(authors);
        Preconditions.checkNotNull(name);
    }
}
