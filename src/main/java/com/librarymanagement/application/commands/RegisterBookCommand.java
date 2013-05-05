package com.librarymanagement.application.commands;

import com.google.common.base.Preconditions;
import com.librarymanagement.domain.CustomEmail;
import org.joda.money.Money;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

import javax.validation.constraints.NotNull;

@Command
public class RegisterBookCommand implements ICommand{

    @CustomEmail
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

    @Override
    public void validate() {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(isbn);
//        Preconditions.checkNotNull(authors);
    }

    @Override
    public String toString() {
        return "RegisterBookCommand{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies=" + copies +
                ", cost=" + cost +
                ", authors='" + authors + '\'' +
                '}';
    }
}
