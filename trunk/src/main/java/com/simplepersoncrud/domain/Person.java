package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.DomainAggregateRoot;

@javax.persistence.Entity
@DomainAggregateRoot
public class Person extends BaseAggregateRoot {

    private String name;

    Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
