package com.simplepersoncrud.domain;

public interface IPersonRepository {

    Long createPerson(Person person);

    Person getPersonWithId(Long id);

    void deletePerson(Long id);


}
