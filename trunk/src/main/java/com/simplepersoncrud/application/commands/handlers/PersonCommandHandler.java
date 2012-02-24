package com.simplepersoncrud.application.commands.handlers;

import com.simplepersoncrud.application.commands.PersonNameChangeCommand;
import com.simplepersoncrud.application.commands.PersonRegistrationCommand;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.domain.PersonFactory;
import com.simplepersoncrud.domain.SimplePerson;
import com.simplepersoncrud.domain.error.PersonCreationException;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.nthdimenzion.cqrs.command.AbstractCommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
public class PersonCommandHandler extends AbstractCommandHandler{

    @Autowired
    private PersonFactory personFactory;

    @Autowired
    private PersonRepository personRepository;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public Long handlePersonRegistration(PersonRegistrationCommand personRegistrationCommand) throws PersonCreationException {
        logger.debug("handlePersonRegistration(CreatePersonCommand) createPersonCommand.getName() " + personRegistrationCommand.getName());
        SimplePerson person = personFactory.createPerson(personRegistrationCommand.getName());
        return personRepository.registerPerson(person);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public SimplePerson handlePersonNameChange(PersonNameChangeCommand personRegistrationCommand) {
        logger.debug("personRegistrationCommand.getId() " + personRegistrationCommand.getId());
        SimplePerson person = personRepository.getPersonWithId(personRegistrationCommand.getId());
        person = populate(personRegistrationCommand,person);
//        modelMapper.map(personRegistrationCommand, person);
        return personRepository.changeNameFor(person);
    }

    public Boolean handleUnRegistration(UnRegisterCommand deletePersonCommand) {
        logger.debug("handle(handleUnRegistration) ");
        for (Long personToBeDeletedId : deletePersonCommand.getPeopleToBeDeleted()) {
            personRepository.unRegisterPerson(personToBeDeletedId);
        }
        return Boolean.TRUE;
    }
}
