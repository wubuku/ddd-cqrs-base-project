package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IPersonRepository {

    java.lang.Long registerPerson(Person person);

    Person getPersonWithId(java.lang.Long id);

    void unRegisterPerson(java.lang.Long id);

    Person changeNameFor(Person person);

    Person getPersonWithUid(PersonId personId);

}
