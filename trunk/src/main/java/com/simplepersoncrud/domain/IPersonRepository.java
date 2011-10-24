package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IPersonRepository {

    Long createPerson(Person person);

    Person getPersonWithId(Long id);

    void deletePerson(Long id);


}
