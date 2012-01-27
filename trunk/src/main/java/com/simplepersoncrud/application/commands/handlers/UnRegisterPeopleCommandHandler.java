package com.simplepersoncrud.application.commands.handlers;

import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CommandHandler
public class UnRegisterPeopleCommandHandler implements ICommandHandler<UnRegisterCommand, Boolean> {

    private final Logger logger = LoggerFactory.getLogger(UnRegisterPeopleCommandHandler.class);

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Boolean handle(UnRegisterCommand deletePersonCommand) {
        logger.debug("handle(DeletePersonCommand) ");
        for (Long personToBeDeletedId : deletePersonCommand.getPeopleToBeDeleted()) {
            personRepository.unRegisterPerson(personToBeDeletedId);
        }
        return Boolean.TRUE;
    }
}
