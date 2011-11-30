package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Role
@MappedSuperclass
public abstract class PersonRole extends BaseAggregateRoot{

    private Person person;

    PersonRole(){
        person = null;
    }

    protected PersonRole(Person person) {
        this.person = person;
    }

    @ManyToOne
    protected Person getPerson(){
        return person;
    }

    void setPerson(Person person){
        this.person = person;
    }
}

