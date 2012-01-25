package org.nthdimenzion.security.infrastructure.repositories.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.security.domain.IUserRepository;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

@DomainRepositoryImpl
public class UserLoginRepository  extends GenericHibernateRepository<UserLogin, Long> implements IUserRepository{

    protected UserLoginRepository(){

    }

    @Autowired
    public UserLoginRepository (HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public UserLogin findUserLoginWithUserName(String username) {
        DetachedCriteria userLoginWithUserNameCriteria = DetachedCriteria.forClass(UserLogin.class);
        userLoginWithUserNameCriteria.add(Restrictions.eq("credentials.username", username));
        return (UserLogin)hibernateTemplate.findByCriteria(userLoginWithUserNameCriteria).get(0);
    }
}
