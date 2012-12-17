package com.librarymanagement.domain;

import org.joda.time.DateTime;
import org.nthdimenzion.crud.ICrudEntity;
import org.nthdimenzion.ddd.domain.INamed;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Role
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"MEMBERID"})})
public class Member extends PersonRole implements ICrudEntity,INamed{
    private String memberId;
    
    public Member() {
    super();
    }

    public Member(Person person) {
        super(person);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    @Transient
    public String getName(){
        return super.getPerson().getName();
    }

    @Transient
    @Override
    public DateTime getDateOfBirth(){
        return super.getDateOfBirth();
    }

    @Override
    public void setDateOfBirth(DateTime dateOfBirth) {
        super.setDateOfBirth(dateOfBirth);
    }

    @Transient
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Transient
    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }

    @Override
    public void setMiddleName(String middleName) {
        super.setMiddleName(middleName);
    }

    @Transient
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public String fullName(){
        return super.getPerson().getName();
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + super.getFirstName() + getDateOfBirth() +
                '}';
    }

    @Override
    public void markAsArchived() {
        super.markAsArchived();
        super.getPerson().markAsArchived();

    }
}
