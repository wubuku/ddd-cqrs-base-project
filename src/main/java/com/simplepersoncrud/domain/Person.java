package com.simplepersoncrud.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embedded;

@javax.persistence.Entity
@AggregateRoot
public class Person extends BaseAggregateRoot {

    private String name;
    private PersonId personId;

    Person() {
    }

    public Person(PersonId personId,String name) {
        Preconditions.checkNotNull(personId);
        Preconditions.checkNotNull(name);
        this.name = name;
        this.personId = personId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Embedded
    public PersonId getPersonId() {
        return new PersonId(personId.getUid());
    }

    void setPersonId(PersonId personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return personId.toString();
    }

    @Override
    public boolean equals(Object o) {
    if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            Person obj = (Person)o;
            return new EqualsBuilder().append(personId,obj.personId).isEquals();
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(personId);
    }


}
