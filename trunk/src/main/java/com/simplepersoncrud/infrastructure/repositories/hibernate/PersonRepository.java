package com.simplepersoncrud.infrastructure.repositories.hibernate;

import com.simplepersoncrud.domain.IPersonRepository;
import com.simplepersoncrud.domain.PersonId;
import com.simplepersoncrud.domain.SimplePerson;
import org.hibernate.criterion.DetachedCriteria;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.ddd.infrastructure.hibernate.IHibernateDaoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@DomainRepositoryImpl
@Transactional
public class PersonRepository extends GenericHibernateRepository<SimplePerson, java.lang.Long> implements IPersonRepository {

    protected PersonRepository(){

    }

    @Autowired
    public PersonRepository(IHibernateDaoOperations hibernateDaoOperations) {
        super(hibernateDaoOperations);
    }

    @Override
    public void unRegisterPerson(java.lang.Long id) {
        delete(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public java.lang.Long registerPerson(SimplePerson person) {
        person = save(person);
        return person.getId();
    }

    @Override
    public SimplePerson changeNameFor(SimplePerson person) {
        return save(person);
    }

    public SimplePerson getPersonWithUid(PersonId personId) {
        DetachedCriteria personFromUid = criteriaForUid("personId",SimplePerson.class,personId);
        SimplePerson person = (SimplePerson) hibernateDaoOperations.findByCriteria(personFromUid).get(0);
        return updatePersonWithDependencies(person);
    }

    @Override
    public SimplePerson getPersonWithId(java.lang.Long id) {
        SimplePerson person = get(id);
        return updatePersonWithDependencies(person);
    }

    private SimplePerson updatePersonWithDependencies(SimplePerson person) {
        if (person != null)
            person.setDomainEventBus(domainEventBus);
        return person;
    }
}
