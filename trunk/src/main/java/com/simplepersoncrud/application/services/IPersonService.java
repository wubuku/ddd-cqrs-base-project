package com.simplepersoncrud.application.services;

import com.simplepersoncrud.domain.Person;

public interface IPersonService {

    Long createPerson(Person person);

    Person getPersonWithId(Long id);

    void deletePerson(Long id);
}
