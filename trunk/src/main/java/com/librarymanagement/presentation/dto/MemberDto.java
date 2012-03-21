package com.librarymanagement.presentation.dto;

import org.joda.time.DateTime;
import org.nthdimenzion.object.utils.StringUtils;

public class MemberDto {

    public String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String fullName(){
    return getFirstName() + " " + StringUtils.replaceNullWithEmptyString(getMiddleName()) + " " + getLastName();
    }
}
