package com.simplepersoncrud.application.services;

import com.simplepersoncrud.domain.SimplePerson;
import com.simplepersoncrud.domain.error.PersonCreationException;

public interface IPersonService {

    Long createPerson(SimplePerson person) throws PersonCreationException;

    SimplePerson getPersonWithId(Long id);

    void deletePerson(Long id);
}
