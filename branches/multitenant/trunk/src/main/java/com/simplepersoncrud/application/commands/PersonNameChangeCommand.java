package com.simplepersoncrud.application.commands;

import com.google.common.base.Preconditions;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class PersonNameChangeCommand implements ICommand{

    private String name;
    private Long id;

    public PersonNameChangeCommand() {
    }

    public PersonNameChangeCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void validate() {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(id);
    }
}
