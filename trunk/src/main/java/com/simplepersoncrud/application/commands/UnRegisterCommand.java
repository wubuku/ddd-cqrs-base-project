package com.simplepersoncrud.application.commands;

import com.google.common.collect.Sets;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

import java.util.Set;

@Command
public class UnRegisterCommand implements ICommand{
    private Set<Long> peopleToBeDeleted = Sets.newHashSet();

    public UnRegisterCommand(Set<Long> peopleToBeDeleted) {
        this.peopleToBeDeleted= peopleToBeDeleted;
    }

    public Set<Long> getPeopleToBeDeleted() {
        return peopleToBeDeleted;
    }
}
