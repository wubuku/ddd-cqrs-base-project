package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

import java.util.Set;

@DomainRepository
public interface ISecurityGroupRepository {

    Set<SecurityGroup> findSecurityGroups(String... securityGroupNames);
}
