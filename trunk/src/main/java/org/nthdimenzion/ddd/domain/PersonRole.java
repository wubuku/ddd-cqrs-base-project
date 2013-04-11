package org.nthdimenzion.ddd.domain;

import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Role
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonRole extends IdGeneratingArcheType {

    private Person person;
    private DomainRole domainRole;

    public static enum DomainRole {
        MEMBER
    }

    private PersonRole(){
        // Hibernate
    }

    protected PersonRole(DomainRole domainRole){
        person = new Person();
        this.domainRole = domainRole;
    }

    protected PersonRole(Person person,DomainRole domainRole) {
        this.person = person;
        this.domainRole = domainRole;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public Person getPerson(){
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

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    public DomainRole getDomainRole() {
        return domainRole;
    }

    void setDomainRole(DomainRole domainRole) {
        this.domainRole = domainRole;
    }
}

