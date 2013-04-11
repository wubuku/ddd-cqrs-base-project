package com.librarymanagement.domain;

import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.Person;
import org.nthdimenzion.ddd.domain.annotations.DomainFactory;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.object.utils.IIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@DomainFactory
public class MemberBuilder {
    @Autowired
    private IIdGenerator idGenerator;
    private Member member;

    @Autowired
    @Qualifier("domainEventBus")
    private IEventBus domainEventBus;

    public MemberBuilder createMember(String firstName, String lastName, DateTime dateOfBirth) {
        Person person = new Person(firstName, lastName, dateOfBirth);
//        member = new Member(person, memberId);
        member = new Member(person);
        return this;
    }

    public MemberBuilder withMiddleName(String middleName) {
        member.getPerson().setMiddleName(middleName);
        return this;
    }

    public Member build() {
        validate();
        injectDependencies();
        return member;
    }

    private void injectDependencies() {
//        member.setDomainEventBus(domainEventBus);
    }

    private void validate() {
        Preconditions.checkNotNull(member.getPerson().getFirstName());
        Preconditions.checkNotNull(member.getPerson().getLastName());
//        Preconditions.checkNotNull(member.getPerson().getDateOfBirth());

    }

}
