package com.simplepersoncrud.application.commands.handlers;

import com.simplepersoncrud.application.commands.DeletePersonCommand;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandlerAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CommandHandlerAnnotation
public class DeletePeopleCommandHandler implements ICommandHandler<DeletePersonCommand, Void> {

    private final Logger logger = LoggerFactory.getLogger(DeletePeopleCommandHandler.class);

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Void handle(DeletePersonCommand deletePersonCommand) {
        logger.debug("handle(DeletePersonCommand) ");
        for (Long personToBeDeletedId : deletePersonCommand.getPeopleToBeDeleted()) {
            personRepository.deletePerson(personToBeDeletedId);
        }
        return null;
    }
}
