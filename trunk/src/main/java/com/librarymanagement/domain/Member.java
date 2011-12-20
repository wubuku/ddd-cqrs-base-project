package com.librarymanagement.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.crud.ICrudEntity;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.Role;
import org.nthdimenzion.object.utils.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Role
@Entity
public class Member extends PersonRole implements ICrudEntity{
    public Member() {
    super();
    }

    public Member(Person person) {
        super(person);
    }

    @Transient
    public String fullName(){
    return getPerson().getFirstName() + " " + StringUtils.replaceNullWithEmptyString(getPerson().getMiddleName()) + " " + getPerson().getLastName();
    }

    @Transient
    public DateTime getDateOfBirth(){
        return getPerson().getDateOfBirth();
    }

    @Transient
    public Person getPerson(){
        return super.getPerson();
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + getPerson().getFirstName() + getDateOfBirth() +
                '}';
    }

    @Override
    public void markAsArchived() {
        super.markAsArchived();    //To change body of overridden methods use File | Settings | File Templates.
        getPerson().markAsArchived();

    }
}
