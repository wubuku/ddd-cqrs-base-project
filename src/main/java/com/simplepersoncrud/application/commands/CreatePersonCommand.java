package com.simplepersoncrud.application.commands;

import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class CreatePersonCommand implements ICommand{
    private String name;

    public CreatePersonCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
