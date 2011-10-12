package com.simplepersoncrud.infrastructure.repositories.hibernate;

import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository implements IPersonRepository {

    private SessionFactory sessionFactory;

//    PersonRepository(){}

    @Autowired
    public PersonRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long savePerson(Person person) {
        Session session = sessionFactory.openSession();
        return (Long)session.save(person);
    }
}
