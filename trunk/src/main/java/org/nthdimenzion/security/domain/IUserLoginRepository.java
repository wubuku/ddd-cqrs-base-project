package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

import java.util.List;

@DomainRepository
public interface IUserLoginRepository {

    UserLogin findUserLoginWithUserName(String username);

    List<UserLogin> findAllUserLoginsWithGivenSecurityGroup(SecurityGroup securityGroup);
}
