package org.nthdimenzion.security.infrastructure.repositories.hibernate;

import com.google.common.collect.Sets;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.annotations.DomainRepositoryImpl;
import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.nthdimenzion.ddd.infrastructure.hibernate.IHibernateDaoOperations;
import org.nthdimenzion.security.domain.ISecurityGroupRepository;
import org.nthdimenzion.security.domain.IUserLoginRepository;
import org.nthdimenzion.security.domain.SecurityGroup;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@DomainRepositoryImpl
public class SecurityGroupRepository extends GenericHibernateRepository<SecurityGroup,
        Long> implements ISecurityGroupRepository {

    protected SecurityGroupRepository(){

    }

    @Autowired
    public SecurityGroupRepository(IHibernateDaoOperations hibernateDaoOperations) {
        super(hibernateDaoOperations);
    }


    @Override
    public Set<SecurityGroup> findSecurityGroups(String... securityGroupNames) {
        DetachedCriteria securityGroupCriteria = DetachedCriteria.forClass(SecurityGroup.class);
        securityGroupCriteria.add(Restrictions.in("name", securityGroupNames));
        return Sets.newHashSet(hibernateDaoOperations.findByCriteria(securityGroupCriteria));
    }
}
