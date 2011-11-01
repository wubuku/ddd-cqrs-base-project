package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IPersonRepository {

    java.lang.Long createPerson(Person person);

    Person getPersonWithId(java.lang.Long id);

    void deletePerson(java.lang.Long id);

    Person updatePerson(Person person);

    Person getPersonWithUid(PersonId personId);

}
