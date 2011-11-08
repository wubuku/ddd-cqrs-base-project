package com.simplepersoncrud.application.commands.handlers;

import com.simplepersoncrud.application.commands.CreatePersonCommand;
import com.simplepersoncrud.application.services.IPersonService;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.error.PersonCreationException;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
public class CreatePersonCommandHandler implements ICommandHandler<CreatePersonCommand,Long>{

    private final Logger logger = LoggerFactory.getLogger(CreatePersonCommandHandler.class);

    @Autowired
    private PersonFactory personFactory;

    @Autowired
    private IPersonService personService;

    @Override
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Long handle(CreatePersonCommand createPersonCommand) throws PersonCreationException {
        logger.debug("handle(CreatePersonCommand) createPersonCommand.getName() " + createPersonCommand.getName());
        Person person = personFactory.createPerson(createPersonCommand.getName());
        return personService.createPerson(person);
    }
}
