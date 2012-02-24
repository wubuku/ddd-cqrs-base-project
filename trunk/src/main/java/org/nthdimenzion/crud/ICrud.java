package org.nthdimenzion.crud;


import org.hibernate.criterion.DetachedCriteria;
import org.nthdimenzion.ddd.domain.BaseArchetype;

import java.io.Serializable;
import java.util.List;

public interface ICrud {
	/**
	 * The add method. CREATE
	 * @param e the entity you want to add to a repository.
	 */
	Long add(BaseArchetype e);
	/**
	 * The remove method. DELETE (DESTROY)
	 * @param e the entity you want to remove from a repository.
	 */
	<E> void remove(E e);
	/**
	 * The save method. UPDATE
	 * @param e the entity you want to update/save to the repository.
	 * @return the entity updated.
	 */
	<E> E update(E e);
	/**
	 * The find method. READ (RETRIEVE)
	 * @param pk the identification to find the specific entity.
	 * @return the entity that corresponds the informed id.
	 */
	<E> E find(Class clazz,Serializable pk);

    <T> List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

    <T> List<T> findByCriteria(DetachedCriteria criteria);

    <T> T findByUniqueKey(DetachedCriteria criteria);

    <T> List<T> getAll(Class<T> klass);

    <T> List<T> getAll(Class<T> klass, int firstResult, int maxResults);

    <T extends BaseArchetype> List<T> unify(List<T> list);

    <T extends BaseArchetype> T deactivate(T t);

}
