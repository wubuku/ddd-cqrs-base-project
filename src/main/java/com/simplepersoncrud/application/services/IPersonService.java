package com.simplepersoncrud.application.services;

import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.error.PersonCreationException;

public interface IPersonService {

    Long createPerson(Person person) throws PersonCreationException;

    Person getPersonWithId(Long id);

    void deletePerson(Long id);
}
