package com.simplepersoncrud.application.services;

import com.google.common.base.Preconditions;
import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import org.nthdimenzion.ddd.application.annotation.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@ApplicationService
public class PersonService implements IPersonService {

private IPersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    // Required for CGLIB
    protected PersonService(){}

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public Long createPerson(Person person) {
        logger.debug("Entry into createPerson(Person person) " + TransactionSynchronizationManager.isActualTransactionActive());
        Preconditions.checkNotNull(person);
        return personRepository.createPerson(person);
    }

    @Override
    public Person getPersonWithId(Long id) {
        return personRepository.getPersonWithId(id);
    }

    @Override
    @Transactional
    public void deletePerson(Long id){
        Preconditions.checkNotNull(id);
        personRepository.deletePerson(id);
    }

}
