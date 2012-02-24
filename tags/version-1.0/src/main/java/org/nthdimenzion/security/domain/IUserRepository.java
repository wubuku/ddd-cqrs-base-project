package org.nthdimenzion.security.domain;

import org.nthdimenzion.ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface IUserRepository {

    UserLogin findUserLoginWithUserName(String username);
}
