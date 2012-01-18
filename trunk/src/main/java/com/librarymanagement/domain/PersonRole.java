package com.librarymanagement.domain;

import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@Role
@MappedSuperclass
public abstract class PersonRole extends IdGeneratingBaseEntity{

    private Person person;

    protected PersonRole(){
        person = new Person();
    }

    protected PersonRole(Person person) {
        this.person = person;
    }

    protected PersonRole(String firstName,String lastName, DateTime dateOfBirth) {
        this.person.setFirstName(firstName);
        this.person.setLastName(lastName);
        this.person.setDateOfBirth(dateOfBirth);
    }

    protected PersonRole(String firstName,String middleName,String lastName ,DateTime dateOfBirth) {
        this(firstName,lastName,dateOfBirth);
        this.person.setMiddleName(middleName);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    protected Person getPerson(){
        return person;
    }

    protected void setPerson(Person person){
        this.person = person;
    }

    @Transient
    protected String getFirstName() {
        return person.getFirstName();
    }

    protected void setFirstName(String firstName) {
        person.setFirstName(firstName);
    }

    @Transient
    protected String getMiddleName() {
        return person.getMiddleName();
    }

    protected void setMiddleName(String middleName) {
        person.setMiddleName(middleName);
    }

    @Transient
    protected String getLastName() {
        return person.getLastName();
    }

    protected void setLastName(String lastName) {
        person.setLastName(lastName);
    }

    @Transient
    protected DateTime getDateOfBirth() {
        return person.getDateOfBirth();
    }

    protected void setDateOfBirth(DateTime dateOfBirth) {
        person.setDateOfBirth(dateOfBirth);
    }

}

