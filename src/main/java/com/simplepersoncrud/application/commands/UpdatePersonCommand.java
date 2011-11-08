package com.simplepersoncrud.application.commands;

import com.simplepersoncrud.domain.PersonId;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

@Command
public class UpdatePersonCommand implements ICommand{
    public String name;
    public PersonId personId;

}
