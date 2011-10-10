package org.nthdimenzion.tests;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository implements IPersonRepository{

    private Session session;

    @Autowired
    public PersonRepository(Session session){
        this.session = session;
    }

    @Override
    public Person savePerson(Person person) {
        return (Person)session.save(person);
    }
}
