package org.nthdimenzion.crud;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.nthdimenzion.ddd.domain.BaseArchetype;
import org.nthdimenzion.ddd.domain.LifeCycle;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CrudDao implements ICrud {

	private final static Logger logger = LoggerFactory.getLogger(CrudDao.class);
    private HibernateTemplate hibernateTemplate;

    CrudDao(){

    }

    @Autowired
    public CrudDao(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
    @Transactional
	public Long add(BaseArchetype e) {
		return (Long) hibernateTemplate.save(e);
	}

	@Override
    @Transactional
	public <E> void remove(E e) {
		hibernateTemplate.delete(e);
	}

	@Override
    @Transactional
	public <E> E update(E e) {
		hibernateTemplate.saveOrUpdate(e);
        return e;
	}

	@Override
	public <E> E find(Class clazz,Serializable pk) {
		return (E) hibernateTemplate.get(clazz,pk);
	}

    @Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
	return hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
	}

    @Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria) {
	return hibernateTemplate.findByCriteria(criteria);
	}


    @Override
	@SuppressWarnings("unchecked")
	public <T> T findByUniqueKey(DetachedCriteria criteria) {
	return (T) hibernateTemplate.findByCriteria(criteria).get(0);
	}


    @Override
	public <T> List<T> getAll(Class<T> klass) {
    DetachedCriteria query = DetachedCriteria.forClass(klass);
    query.add(Property.forName("lifeCycle.entityStatus").eq(LifeCycle.EntityStatus.ACTIVE));
	return hibernateTemplate.findByCriteria(query);
    }

    @Override
    public <T> List<T> getAll(Class<T> klass, int firstResult, int maxResults) {
    DetachedCriteria query = DetachedCriteria.forClass(klass);
    query.add(Property.forName("entityStatus").eq(0));
	return findByCriteria(query, firstResult, maxResults);
    }

    @Override
    public <T extends BaseArchetype> List<T> unify(List<T> list) {
	if (UtilValidator.isEmpty(list))
		return list;
	Set<T> unifier = new HashSet<T>();
	unifier.addAll(list);
	return new ArrayList<T>(unifier);
    }

    @Override
    @Transactional
    public <T extends BaseArchetype> T deactivate(T t){
        t.markAsArchived();;
        return  update(t);
    }


}