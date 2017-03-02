package cz.ucl.hatchery.carevidence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<T, ID extends Serializable> {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sf;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sf.getCurrentSession();
	}

	protected StatelessSession getStatelessSession() {
		return sf.openStatelessSession();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void saveOrUpdate(final T entity) {
		getSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public ID save(final T entity) {
		return (ID) getSession().save(entity);
	}

	public void saveAll(final List<T> entityList) {
		for (final T entity : entityList) {
			save(entity);
		}
	}

	public void delete(final T entity) {
		getSession().delete(entity);
	}

	public void flush() {
		getSession().flush();
	}

	public void refresh(final T entity) {
		getSession().refresh(entity);
	}

	public void clear() {
		getSession().clear();
	}

	public void evictAll(final Class<?> clazz) {
		getSession().getSessionFactory().getCache().evictEntityRegion(clazz);
	}

	public Criteria createCriteria() {
		final Session ses = getSession();
		return ses.createCriteria(getPersistentClass());
	}

	public Criteria createCriteria(final String alias) {
		final Session ses = getSession();
		return ses.createCriteria(getPersistentClass(), alias);
	}

}
