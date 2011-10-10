package org.nthdimenzion.tests;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;
import sun.java2d.pipe.ValidatePipe;

import javax.validation.constraints.AssertTrue;

@Service
public class PersonService implements IPersonService{

private IPersonRepository personRepository = null;

    // Required for CGLIB
    PersonService(){}

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
