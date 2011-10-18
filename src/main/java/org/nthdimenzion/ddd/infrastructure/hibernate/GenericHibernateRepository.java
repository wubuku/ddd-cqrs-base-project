package org.nthdimenzion.ddd.infrastructure.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericHibernateRepository<E extends Object, K extends Serializable> {
    protected HibernateTemplate hibernateTemplate;
    private Class<E> clazz;

    public GenericHibernateRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected E get(K id) {
        return hibernateTemplate.get(clazz, id);
    }

    protected void delete(K id) {
        hibernateTemplate.delete(get(id));
    }

    protected E save(E entity) {
        hibernateTemplate.saveOrUpdate(entity);
        return entity;
    }

}
