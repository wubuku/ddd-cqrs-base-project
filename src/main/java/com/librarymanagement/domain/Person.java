package com.librarymanagement.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.INamed;
import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;
import org.nthdimenzion.ddd.domain.annotations.PPT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import static org.nthdimenzion.object.utils.StringUtils.nullSafeCopy;
import static org.nthdimenzion.object.utils.StringUtils.replaceNullWithEmptyString;
@PPT
@Entity
public final class Person extends IdGeneratingBaseEntity implements INamed {

    private String firstName;
    private String middleName;
    private String lastName;
    private DateTime dateOfBirth;

    Person() {
    }

    Person(String firstName,String lastName, DateTime dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    Person(String firstName,String middleName,String lastName ,DateTime dateOfBirth) {
        this(firstName,lastName,dateOfBirth);
        this.middleName = middleName;
    }

    @NotNull
    public String getFirstName() {
        return nullSafeCopy(firstName);
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return nullSafeCopy(middleName);
    }

    void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @NotNull
    public String getLastName() {
        return nullSafeCopy(lastName);
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @NotNull
    public DateTime getDateOfBirth() {
        return dateOfBirth!=null ? new DateTime(dateOfBirth) : null;
    }

    void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    @Transient
    public String getName() {
        return firstName + " " + replaceNullWithEmptyString(middleName) + " " + lastName;
    }
}

