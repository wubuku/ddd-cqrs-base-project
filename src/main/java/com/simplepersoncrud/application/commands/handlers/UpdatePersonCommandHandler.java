package com.simplepersoncrud.application.commands.handlers;

import com.simplepersoncrud.application.commands.UpdatePersonCommand;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CommandHandler
public class UpdatePersonCommandHandler implements ICommandHandler<UpdatePersonCommand,Person>{

    private final Logger logger = LoggerFactory.getLogger(UpdatePersonCommandHandler.class);

    @Autowired
    private PersonFactory personFactory;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Person handle(UpdatePersonCommand updatePersonCommand) {
        logger.debug("handle(UpdatePersonCommand) " );
        Person person = personRepository.getPersonWithUid(updatePersonCommand.personId);
        return person;
    }
}
