package com.librarymanagement.presentation.dto;

import org.joda.time.DateTime;
import org.nthdimenzion.object.utils.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

public class MemberDto {

    public Long id;
    public String firstName;
    public String middleName;
    public String lastName;
    public DateTime dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String fullName(){
    return getFirstName() + " " + StringUtils.replaceNullWithEmptyString(getMiddleName()) + " " + getLastName();
    }


}
