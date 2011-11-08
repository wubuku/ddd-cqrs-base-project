package com.simplepersoncrud.infrastructure.repositories.hibernate;

import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.Person;
import com.simplepersoncrud.domain.PersonId;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.access.prepost.PreAuthorize;

@DomainRepositoryImpl
public class PersonRepository extends GenericHibernateRepository<Person, java.lang.Long> implements IPersonRepository {

    PersonRepository(){

    }

    @Autowired
    public PersonRepository(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public void deletePerson(java.lang.Long id) {
        delete(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public java.lang.Long createPerson(Person person) {
        person = save(person);
        return person.getId();
    }

    @Override
    public Person updatePerson(Person person) {
        return save(person);
    }

    public Person getPersonWithUid(PersonId personId) {
        DetachedCriteria personFromUid = DetachedCriteria.forClass(Person.class);
        personFromUid.add(Restrictions.eq("personId", personId));
        Person person = (Person) hibernateTemplate.findByCriteria(personFromUid).get(0);
        return updatePersonWithDependencies(person);
    }

    @Override
    public Person getPersonWithId(java.lang.Long id) {
        Person person = get(id);
        return updatePersonWithDependencies(person);
    }

    private Person updatePersonWithDependencies(Person person) {
        if (person != null)
            person.setDomainEventBus(domainEventBus);
        return person;
    }
}
