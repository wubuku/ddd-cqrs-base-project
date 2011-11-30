package com.librarymanagement.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.PPT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AggregateRoot
@PPT
@Entity
public class Person extends BaseAggregateRoot{

    private String firstName;
    private String middleName;
    private String lastName;
    private DateTime dateOfBirth;

    Person() {
    }

    public Person(String firstName, DateTime dateOfBirth) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }

    @NotNull
    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getMiddleName() {
        return middleName;
    }

    void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @NotNull
    DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

