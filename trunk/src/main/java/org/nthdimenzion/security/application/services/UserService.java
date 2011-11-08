package org.nthdimenzion.security.application.services;

import org.nthdimenzion.ddd.domain.annotations.DomainService;
import org.nthdimenzion.security.domain.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@DomainService
public class UserService implements UserDetailsService{

    private JdbcDaoImpl jdbcDao;
    private UserDetailsDto userDetailsDto;

    @Autowired
    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    @Autowired
    public UserService(JdbcDaoImpl jdbcDao) {
    this.jdbcDao = jdbcDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        System.out.println(jdbcDao.getUsersByUsernameQuery());
        return userDetailsDto.uses(jdbcDao.loadUserByUsername(username));
    }
}
