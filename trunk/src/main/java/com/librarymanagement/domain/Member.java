package com.librarymanagement.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Role
@AggregateRoot
@Entity
public class Member extends PersonRole{
    private String memberId;

    public Member() {
    }

    public Member(Person person, String memberId) {
        super(person);
        this.memberId = memberId;
    }

    @NotNull
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
