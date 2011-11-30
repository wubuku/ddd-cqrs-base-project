package org.nthdimenzion.ddd.infrastructure.hibernate;

import com.google.common.eventbus.EventBus;
import org.nthdimenzion.ddd.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericHibernateRepository<E extends BaseEntity, K extends Serializable> {
    protected HibernateTemplate hibernateTemplate;
    private Class<E> clazz;

    @Autowired
    @Qualifier("domainEventBus")
    protected EventBus domainEventBus;

    @Autowired
    public GenericHibernateRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected GenericHibernateRepository() {
    }

    protected E get(K id) {
        return hibernateTemplate.get(clazz, id);
    }

    protected E load(K id) {
        return hibernateTemplate.load(clazz, id);
    }

    protected void delete(K id) {
        hibernateTemplate.delete(get(id));
    }

    protected E save(E entity) {
        hibernateTemplate.saveOrUpdate(entity);
        return entity;
    }

    protected E deactivate(K id) {
        E entity = get(id);
        entity.markAsArchived();
        entity = save(entity);
        return entity;
    }

    protected E reactivate(K id) {
        E entity = get(id);
        entity.markAsActive();
        entity = save(entity);
        return entity;
    }



}
