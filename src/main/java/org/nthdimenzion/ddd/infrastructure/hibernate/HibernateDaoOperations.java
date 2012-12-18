package org.nthdimenzion.ddd.infrastructure.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 18/12/12
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HibernateDaoOperations implements IHibernateDaoOperations {

    private final SessionFactory sessionFactory;

    private boolean cacheQueries = false;

    private String queryCacheRegion;

    private int fetchSize = 0;

    private int maxResults = 0;

    @Autowired
    public HibernateDaoOperations(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public <T> T get(final Class<T> entityClass, final Serializable id) {
        return get(entityClass, id, null);
    }

    @Override
    public <T> T get(final Class<T> entityClass, final Serializable id, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            return (T) session.get(entityClass, id, lockOptions);
        } else {
            return (T) session.get(entityClass, id);
        }
    }

    @Override
    public Object get(final String entityName, final Serializable id) {
        return get(entityName, id, null);
    }

    @Override
    public Object get(final String entityName, final Serializable id, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            return session.get(entityName, id, lockOptions);
        } else {
            return session.get(entityName, id);
        }
    }

    @Override
    public <T> T load(final Class<T> entityClass, final Serializable id) {
        return load(entityClass, id, null);
    }

    @Override
    public <T> T load(final Class<T> entityClass, final Serializable id, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            return (T) session.load(entityClass, id, lockOptions);
        } else {
            return (T) session.load(entityClass, id);
        }
    }

    @Override
    public Object load(final String entityName, final Serializable id) {
        return load(entityName, id, null);
    }

    @Override
    public Object load(final String entityName, final Serializable id, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            return session.load(entityName, id, lockOptions);
        } else {
            return session.load(entityName, id);
        }
    }

    @Override
    public <T> List<T> loadAll(final Class<T> entityClass) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        prepareCriteria(criteria);
        return criteria.list();
    }

    @Override
    public void load(final Object entity, final Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        session.load(entity, id);
    }

    @Override
    public void refresh(final Object entity) {
        refresh(entity, null);
    }

    @Override
    public void refresh(final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            session.refresh(entity, lockOptions);
        } else {
            session.refresh(entity);
        }
    }

    @Override
    public boolean contains(final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        return session.contains(entity);
    }

    @Override
    public void evict(final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.evict(entity);
    }

    @Override
    public void initialize(final Object proxy) {
        Hibernate.initialize(proxy);
    }

    @Override
    public Filter enableFilter(final String filterName) throws IllegalStateException {
        Session session = sessionFactory.getCurrentSession();
        Filter filter = session.getEnabledFilter(filterName);
        if (filter == null) {
            filter = session.enableFilter(filterName);
        }
        return filter;
    }

    @Override
    public void lock(final String entityName, final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        session.lock(entity, lockOptions.getLockMode());
    }

    @Override
    public Serializable save(final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(entity);
    }

    @Override
    public Serializable save(final String entityName, final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(entityName, entity);
    }

    @Override
    public void update(final Object entity) {
        update(entity, null);
    }

    @Override
    public void update(final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        if (lockOptions != null) {
            session.lock(entity, lockOptions.getLockMode());
        }
    }

    @Override
    public void update(final String entityName, final Object entity) {
        update(entityName, entity, null);
    }

    @Override
    public void update(final String entityName, final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entityName, entity);
        if (lockOptions != null) {
            session.lock(entity, lockOptions.getLockMode());
        }
    }

    @Override
    public void saveOrUpdate(final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(final String entityName, final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entityName, entity);
    }

    @Override
    public void saveOrUpdateAll(final Collection entities) {
        Session session = sessionFactory.getCurrentSession();
        for (Object entity : entities) {
            session.saveOrUpdate(entity);
        }
    }

    @Override
    public void replicate(final Object entity, final ReplicationMode replicationMode) {
        Session session = sessionFactory.getCurrentSession();
        session.replicate(entity, replicationMode);
    }

    @Override
    public void replicate(final String entityName, final Object entity, final ReplicationMode replicationMode) {
        Session session = sessionFactory.getCurrentSession();
        session.replicate(entityName, entity, replicationMode);
    }

    @Override
    public void persist(final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
    }

    @Override
    public void persist(final String entityName, final Object entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entityName, entity);
    }

    @Override
    public <T> T merge(final T entity) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.merge(entity);
    }

    @Override
    public <T> T merge(final String entityName, final T entity) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.merge(entityName, entity);
    }

    @Override
    public void delete(final Object entity) {
        delete(entity, null);
    }

    @Override
    public void delete(final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            session.lock(entity, lockOptions.getLockMode());
        }
        session.delete(entity);
    }

    @Override
    public void delete(final String entityName, final Object entity) {
        delete(entityName, entity, null);
    }

    @Override
    public void delete(final String entityName, final Object entity, final LockOptions lockOptions) {
        Session session = sessionFactory.getCurrentSession();
        if (lockOptions != null) {
            session.lock(entityName, entity, lockOptions.getLockMode());
        }
        session.delete(entityName, entity);
    }

    @Override
    public void deleteAll(final Collection entities) {
        Session session = sessionFactory.getCurrentSession();
        for (Object entity : entities) {
            session.delete(entity);
        }
    }

    @Override
    public void flush() {
        Session session = sessionFactory.getCurrentSession();
        session.flush();
    }

    @Override
    public void clear() {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
    }

    @Override
    public List find(final String queryString) {
        return find(queryString, (Object[]) null);
    }

    @Override
    public List find(final String queryString, final Object value) {
        return find(queryString, new Object[]{value});
    }

    @Override
    public List find(final String queryString, final Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.list();
    }

    @Override
    public List findByNamedParam(final String queryString, final String paramName, final Object value) {
        return findByNamedParam(queryString, new String[]{paramName}, new Object[]{value});
    }

    @Override
    public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values) {
        if (paramNames.length != values.length) {
            throw new IllegalArgumentException("Length of paramNames array must match length of values array");
        }
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
            }
        }
        return queryObject.list();
    }

    @Override
    public List findByValueBean(final String queryString, final Object valueBean) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        prepareQuery(queryObject);
        queryObject.setProperties(valueBean);
        return queryObject.list();
    }

    @Override
    public List findByNamedQuery(final String queryName) {
        return findByNamedQuery(queryName, (Object[]) null);
    }

    @Override
    public List findByNamedQuery(final String queryName, final Object value) {
        return findByNamedQuery(queryName, new Object[]{value});
    }

    @Override
    public List findByNamedQuery(final String queryName, final Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.getNamedQuery(queryName);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.list();
    }

    @Override
    public List findByNamedQueryAndNamedParam(final String queryName, final String paramName, final Object value) {
        return findByNamedQueryAndNamedParam(queryName, new String[]{paramName}, new Object[]{value});
    }

    @Override
    public List findByNamedQueryAndNamedParam(final String queryName, final String[] paramNames, final Object[] values) {
        if (paramNames != null && values != null && paramNames.length != values.length) {
            throw new IllegalArgumentException("Length of paramNames array must match length of values array");
        }
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.getNamedQuery(queryName);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
            }
        }
        return queryObject.list();
    }

    @Override
    public List findByNamedQueryAndValueBean(final String queryName, final Object valueBean) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.getNamedQuery(queryName);
        prepareQuery(queryObject);
        queryObject.setProperties(valueBean);
        return queryObject.list();
    }

    @Override
    public List findByCriteria(final DetachedCriteria criteria) {
        return findByCriteria(criteria, -1, -1);
    }

    @Override
    public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {
        Assert.notNull(criteria, "DetachedCriteria must not be null");
        Session session = sessionFactory.getCurrentSession();
        Criteria executableCriteria = criteria.getExecutableCriteria(session);
        prepareCriteria(executableCriteria);
        if (firstResult >= 0) {
            executableCriteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            executableCriteria.setMaxResults(maxResults);
        }
        return executableCriteria.list();
    }

    @Override
    public Iterator iterate(final String queryString) {
        return iterate(queryString, (Object[]) null);
    }

    @Override
    public Iterator iterate(final String queryString, final Object value) {
        return iterate(queryString, new Object[]{value});
    }

    @Override
    public Iterator iterate(final String queryString, final Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.iterate();
    }

    @Override
    public void closeIterator(final Iterator it) {
        Hibernate.close(it);
    }

    @Override
    public int bulkUpdate(final String queryString) {
        return bulkUpdate(queryString, (Object[]) null);
    }

    @Override
    public int bulkUpdate(final String queryString, final Object value) {
        return bulkUpdate(queryString, new Object[] {value});
    }

    @Override
    public int bulkUpdate(final String queryString, final Object... values) {
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        prepareQuery(queryObject);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.executeUpdate();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void prepareCriteria(Criteria criteria) {
        if (isCacheQueries()) {
            criteria.setCacheable(true);
            if (getQueryCacheRegion() != null) {
                criteria.setCacheRegion(getQueryCacheRegion());
            }
        }
        if (getFetchSize() > 0) {
            criteria.setFetchSize(getFetchSize());
        }
        if (getMaxResults() > 0) {
            criteria.setMaxResults(getMaxResults());
        }
    }

    /**
     * Set whether to cache all queries executed by this template.
     * <p>If this is "true", all Query and Criteria objects created by
     * this template will be marked as cacheable (including all
     * queries through find methods).
     * <p>To specify the query region to be used for queries cached
     * by this template, set the "queryCacheRegion" property.
     *
     * @see #setQueryCacheRegion
     * @see org.hibernate.Query#setCacheable
     * @see org.hibernate.Criteria#setCacheable
     */
    public void setCacheQueries(boolean cacheQueries) {
        this.cacheQueries = cacheQueries;
    }

    /**
     * Return whether to cache all queries executed by this template.
     */
    public boolean isCacheQueries() {
        return this.cacheQueries;
    }

    /**
     * Set the name of the cache region for queries executed by this template.
     * <p>If this is specified, it will be applied to all Query and Criteria objects
     * created by this template (including all queries through find methods).
     * <p>The cache region will not take effect unless queries created by this
     * template are configured to be cached via the "cacheQueries" property.
     *
     * @see #setCacheQueries
     * @see org.hibernate.Query#setCacheRegion
     * @see org.hibernate.Criteria#setCacheRegion
     */
    public void setQueryCacheRegion(String queryCacheRegion) {
        this.queryCacheRegion = queryCacheRegion;
    }

    /**
     * Return the name of the cache region for queries executed by this template.
     */
    public String getQueryCacheRegion() {
        return this.queryCacheRegion;
    }

    /**
     * Set the fetch size for this HibernateTemplate. This is important for processing
     * large result sets: Setting this higher than the default value will increase
     * processing speed at the cost of memory consumption; setting this lower can
     * avoid transferring row data that will never be read by the application.
     * <p>Default is 0, indicating to use the JDBC driver's default.
     */
    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    /**
     * Return the fetch size specified for this HibernateTemplate.
     */
    public int getFetchSize() {
        return this.fetchSize;
    }

    /**
     * Set the maximum number of rows for this HibernateTemplate. This is important
     * for processing subsets of large result sets, avoiding to read and hold
     * the entire result set in the database or in the JDBC driver if we're
     * never interested in the entire result in the first place (for example,
     * when performing searches that might return a large number of matches).
     * <p>Default is 0, indicating to use the JDBC driver's default.
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Return the maximum number of rows specified for this HibernateTemplate.
     */
    public int getMaxResults() {
        return this.maxResults;
    }

    /**
     * Prepare the given Query object, applying cache settings and/or
     * a transaction timeout.
     *
     * @param queryObject the Query object to prepare
     * @see #setCacheQueries
     * @see #setQueryCacheRegion
     */
    protected void prepareQuery(Query queryObject) {
        if (isCacheQueries()) {
            queryObject.setCacheable(true);
            if (getQueryCacheRegion() != null) {
                queryObject.setCacheRegion(getQueryCacheRegion());
            }
        }
        if (getFetchSize() > 0) {
            queryObject.setFetchSize(getFetchSize());
        }
        if (getMaxResults() > 0) {
            queryObject.setMaxResults(getMaxResults());
        }
    }

    /**
     * Apply the given name parameter to the given Query object.
     *
     * @param queryObject the Query object
     * @param paramName   the name of the parameter
     * @param value       the value of the parameter
     * @throws HibernateException if thrown by the Query object
     */
    protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
            throws HibernateException {

        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        } else if (value instanceof Object[]) {
            queryObject.setParameterList(paramName, (Object[]) value);
        } else {
            queryObject.setParameter(paramName, value);
        }
    }

}
