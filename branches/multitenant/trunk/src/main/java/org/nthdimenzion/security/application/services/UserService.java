package org.nthdimenzion.security.application.services;

import org.nthdimenzion.ddd.domain.annotations.DomainService;
import org.nthdimenzion.security.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@DomainService
public class UserService implements UserDetailsService{

    private UserDetailsService userDetailsService;

    @Autowired
    private SystemUser systemUser;

    @Autowired
    public UserService(UserDetailsService userValidationService) {
    this.userDetailsService = userValidationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return systemUser.uses(userDetailsService.loadUserByUsername(username));
    }
}
