package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.ddd.infrastructure.hibernate.GenericHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorDetailsRepository extends GenericHibernateRepository<ErrorDetails,Long> implements IErrorDetailsRepository{

    ErrorDetailsRepository(){

    }

    @Autowired
    public ErrorDetailsRepository(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public ErrorDetails save(ErrorDetails errorDetails){
        return super.save(errorDetails);
    }

}
