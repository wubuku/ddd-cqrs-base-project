package com.simplepersoncrud.domain;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.simplepersoncrud.domain.error.PersonCreationException;
import org.apache.commons.lang.StringUtils;
import org.nthdimenzion.ddd.domain.AbstractDomainFactory;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.object.utils.IIdGenerator;

@DomainFactory
public class PersonFactory extends AbstractDomainFactory{

    public SimplePerson createPerson(String name) throws PersonCreationException {
        Preconditions.checkNotNull(name);
        if(name.length() > 10){
            ErrorDetails errorDetails = new ErrorDetails.Builder("101").isShowErrorInView(Boolean.FALSE).args(Lists.newArrayList(name)).build();
            throw new PersonCreationException(errorDetails);
        }
        else if(StringUtils.contains(name, " ")){
            throw new PersonCreationException(new ErrorDetails.Builder("101").args(Lists.newArrayList(name)).build());
        }
        PersonId personId = new PersonId(idGenerator.nextId());
        return new SimplePerson(personId,name);
    }

    public void setIdGenerator(IIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

     private SimplePerson updatePersonWithDependencies(SimplePerson person) {
        if (person != null)
            person.setDomainEventBus(domainEventBus);
        return person;
    }
}
