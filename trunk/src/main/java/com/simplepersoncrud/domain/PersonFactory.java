package com.simplepersoncrud.domain;

import com.google.common.base.Preconditions;
import com.simplepersoncrud.domain.error.PersonCreationException;
import org.apache.commons.lang.StringUtils;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@DomainFactory
public class PersonFactory {

    @Autowired
    private IIdGenerator idGenerator;

    public SimplePerson createPerson(String name) throws PersonCreationException {
        Preconditions.checkNotNull(name);
        if(name.length() > 10){
            ErrorDetails errorDetails = new ErrorDetails(ErrorDetails.UI_ERROR_DETAILS);
            errorDetails.isSuppresException = Boolean.FALSE;
            throw new PersonCreationException(errorDetails);
        }
        else if(StringUtils.contains(name, " ")){
            throw new PersonCreationException(ErrorDetails.UI_ERROR_DETAILS);
        }
        PersonId personId = new PersonId(idGenerator.nextId());
        return new SimplePerson(personId,name);
    }

    public void setIdGenerator(IIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}
