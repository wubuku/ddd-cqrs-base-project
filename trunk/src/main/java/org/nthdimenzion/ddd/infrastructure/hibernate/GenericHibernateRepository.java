package org.nthdimenzion.ddd.infrastructure.hibernate;

import com.librarymanagement.domain.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.BaseArchetype;
import org.nthdimenzion.ddd.domain.IPersistable;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericHibernateRepository<E extends BaseAggregateRoot, K extends Serializable> {
    protected final HibernateTemplate hibernateTemplate;
    private Class<E> clazz;

    @Autowired
    @Qualifier("domainEventBus")
    protected IEventBus domainEventBus;

    @Autowired
    public GenericHibernateRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected GenericHibernateRepository() {
        hibernateTemplate = null;
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

    protected DetachedCriteria criteriaForUid(String fieldName, Class clazz, Object uid) {
        DetachedCriteria uidCriteria = DetachedCriteria.forClass(clazz);
        uidCriteria.add(Restrictions.eq(fieldName, uid));
        return uidCriteria;
    }


}
