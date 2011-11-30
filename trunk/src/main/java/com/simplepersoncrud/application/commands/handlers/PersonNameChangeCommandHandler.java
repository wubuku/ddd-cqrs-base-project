package com.simplepersoncrud.application.commands.handlers;


import com.simplepersoncrud.application.commands.PersonNameChangeCommand;
import com.simplepersoncrud.domain.SimplePerson;
import com.simplepersoncrud.infrastructure.repositories.hibernate.PersonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.cqrs.command.ICommandHandler;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@CommandHandler
@Deprecated
public class PersonNameChangeCommandHandler implements ICommandHandler<PersonNameChangeCommand,SimplePerson>{

    private final Logger logger = LoggerFactory.getLogger(PersonNameChangeCommand.class);

    private ModelMapper modelMapper = new ModelMapper();


    @Autowired
    private PersonRepository personRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public SimplePerson handle(PersonNameChangeCommand personRegistrationCommand) {
        modelMapper.getConfiguration().setMethodAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE);
        logger.debug("personRegistrationCommand.getId() " + personRegistrationCommand.getId() );
        SimplePerson person = personRepository.getPersonWithId(personRegistrationCommand.getId());
        modelMapper.map(personRegistrationCommand,person);
        return personRepository.changeNameFor(person);
    }
}
