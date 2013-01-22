package org.nthdimenzion.security.infrastructure.repositories.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.ddd.infrastructure.hibernate.IHibernateDaoOperations;
import org.nthdimenzion.security.domain.IUserRepository;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DomainRepositoryImpl
@Transactional
public class UserLoginRepository extends GenericHibernateRepository<UserLogin, Long> implements IUserRepository{

    protected UserLoginRepository(){

    }

    @Autowired
    public UserLoginRepository(IHibernateDaoOperations hibernateDaoOperations) {
        super(hibernateDaoOperations);
    }

    @Override
    public UserLogin findUserLoginWithUserName(String username) {
        DetachedCriteria userLoginWithUserNameCriteria = DetachedCriteria.forClass(UserLogin.class);
        userLoginWithUserNameCriteria.add(Restrictions.eq("credentials.username", username));
        return (UserLogin)hibernateDaoOperations.findByCriteria(userLoginWithUserNameCriteria).get(0);
    }
}
