package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Role
@MappedSuperclass
public abstract class PersonRole extends IdGeneratingBaseEntity{

    private Person person;

    PersonRole(){
        person = new Person();
    }

    protected PersonRole(Person person) {
        this.person = person;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    protected Person getPerson(){
        return person;
    }

    void setPerson(Person person){
        this.person = person;
    }
}

