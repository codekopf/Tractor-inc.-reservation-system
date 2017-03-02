package cz.ucl.hatchery.carevidence.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	List<T> findAll();

	T findById(ID id);

	List<T> findByProperty(String propertyName, Object value, String... fetchedAssociations);

	public T findUniqueByProperty(final String propertyName, final Object value, final String... fetchedAssociations);

	List<T> findNotRemovedOrderedByCreationDate();

	void saveOrUpdate(T entity);

	ID save(T entity);

	void saveAll(List<T> entityList);

	void saveAllInBatch(final List<T> entityList);

	void delete(T entity);

	Long countAll();

	Class<T> getPersistentClass();

	void clear();

	void refresh(T entity);

	void flush();
}