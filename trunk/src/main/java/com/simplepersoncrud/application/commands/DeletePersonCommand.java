package com.simplepersoncrud.application.commands;

import com.google.common.collect.Sets;
import com.simplepersoncrud.domain.PersonId;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.annotations.Command;

import java.util.Set;

@Command
public class DeletePersonCommand implements ICommand{
    private Set<Long> peopleToBeDeleted = Sets.newHashSet();

    public DeletePersonCommand(Set<Long> peopleToBeDeleted) {
        this.peopleToBeDeleted= peopleToBeDeleted;
    }

    public Set<Long> getPeopleToBeDeleted() {
        return peopleToBeDeleted;
    }
}
