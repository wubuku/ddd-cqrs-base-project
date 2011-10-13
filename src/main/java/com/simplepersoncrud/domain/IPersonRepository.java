package com.simplepersoncrud.domain;

public interface IPersonRepository {

     Long savePerson(Person person);

     Person getPersonWithId(Long id);
}
