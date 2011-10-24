package com.simplepersoncrud.infrastructure.repositories.hibernate;

import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nthdimenzion.ddd.domain.annotations.DomainRepository;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import sun.net.www.content.text.Generic;

@DomainRepositoryImpl
public class PersonRepository extends GenericHibernateRepository<Person,Long> implements IPersonRepository {

    @Autowired
    public PersonRepository(HibernateTemplate hibernateTemplate){
        super(hibernateTemplate);
    }

    @Override
    public void deletePerson(Long id) {
        delete(id);
    }

    @Override
    public Long createPerson(Person person) {
        person = save(person);
        return person.getId();
    }

    @Override
    public Person getPersonWithId(Long id) {
        Person person = get(id);
        if(person!=null)
            person.setDomainEventBus(domainEventBus);
        return person;
    }
}
