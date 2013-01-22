package org.nthdimenzion.crud;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.nthdimenzion.ddd.domain.BaseArchetype;
import org.nthdimenzion.ddd.domain.LifeCycle;
import org.nthdimenzion.ddd.infrastructure.hibernate.IHibernateDaoOperations;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class CrudDao implements ICrud {

	private final static Logger logger = LoggerFactory.getLogger(CrudDao.class);
    private IHibernateDaoOperations hibernateDaoOperations;

    protected CrudDao(){

    }

    @Autowired
    public CrudDao(IHibernateDaoOperations hibernateDaoOperations) {
        this.hibernateDaoOperations = hibernateDaoOperations;
    }
	
	@Override
    @Transactional
	public Long add(BaseArchetype e) {
		return (Long) hibernateDaoOperations.save(e);
	}

	@Override
    @Transactional
	public <E> void remove(E e) {
		hibernateDaoOperations.delete(e);
	}

	@Override
    @Transactional
	public <E> E update(E e) {
		hibernateDaoOperations.saveOrUpdate(e);
        return e;
	}

	@Override
	public <E> E find(Class clazz,Serializable pk) {
		return (E) hibernateDaoOperations.get(clazz,pk);
	}

    @Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
	return hibernateDaoOperations.findByCriteria(criteria, firstResult, maxResults);
	}

    @Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria) {
	return hibernateDaoOperations.findByCriteria(criteria);
	}


    @Override
	@SuppressWarnings("unchecked")
	public <T> T findByUniqueKey(DetachedCriteria criteria) {
	return (T) hibernateDaoOperations.findByCriteria(criteria).get(0);
	}


    @Override
	public <T> List<T> getAll(Class<T> klass) {
    DetachedCriteria query = DetachedCriteria.forClass(klass);
    query.add(Property.forName("lifeCycle.entityStatus").eq(LifeCycle.EntityStatus.ACTIVE));
	return hibernateDaoOperations.findByCriteria(query);
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