package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IPersonRepository {

    java.lang.Long registerPerson(SimplePerson person);

    SimplePerson getPersonWithId(java.lang.Long id);

    void unRegisterPerson(java.lang.Long id);

    SimplePerson changeNameFor(SimplePerson person);

    SimplePerson getPersonWithUid(PersonId personId);

}
