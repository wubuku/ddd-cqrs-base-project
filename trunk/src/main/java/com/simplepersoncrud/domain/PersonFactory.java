package com.simplepersoncrud.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainFactory;

@DomainFactory
public class PersonFactory {

    public Person createPerson(String name){
        return new Person(name);
    }
}
