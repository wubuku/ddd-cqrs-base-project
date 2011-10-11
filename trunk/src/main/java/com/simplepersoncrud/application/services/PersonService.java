package com.simplepersoncrud.application.services;

import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class PersonService implements IPersonService {

private IPersonRepository personRepository = null;

    // Required for CGLIB
    public PersonService(){}

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public Long savePerson(Person person) {
        Preconditions.checkArgument(TransactionSynchronizationManager.isActualTransactionActive());
        return personRepository.savePerson(person);
    }

}
