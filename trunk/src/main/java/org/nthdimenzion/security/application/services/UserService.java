package org.nthdimenzion.security.application.services;

import org.nthdimenzion.ddd.domain.annotations.DomainService;
import org.nthdimenzion.security.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.zkoss.spring.SpringUtil;

@DomainService
public class UserService implements UserDetailsService{

    private JdbcDaoImpl jdbcDao;

    @Autowired
    private SystemUser systemUser;

    @Autowired
    public UserService(JdbcDaoImpl jdbcDao) {
    this.jdbcDao = jdbcDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return systemUser.uses(jdbcDao.loadUserByUsername(username));
    }
}
