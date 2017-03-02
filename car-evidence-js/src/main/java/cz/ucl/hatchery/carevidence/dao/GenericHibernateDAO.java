package cz.ucl.hatchery.carevidence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.NullPrecedence;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.ucl.hatchery.carevidence.entity.ordering.ListOrderColumn;
import cz.ucl.hatchery.carevidence.model.AbstractListFilter;
import cz.ucl.hatchery.carevidence.web.CommonConstants;

@Repository
public abstract class GenericHibernateDAO<T, ID extends Serializable> extends AbstractDAO<T, ID>
		implements GenericDAO<T, ID> {

	/**
	 * Vrati prvni polozku v nalezenem seznamu
	 */
	protected final static Integer FIRST_ITEM_IN_RESULT = 1;

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getSession().createCriteria(getPersistentClass()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(final ID id) {
		return (T) getSession().get(getPersistentClass(), id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(final String propertyName, final Object value, final String... fetchedAssociations) {
		final Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq(propertyName, value));
		for (final String fetchedAssociation : fetchedAssociations) {
			criteria.createAlias(fetchedAssociation, fetchedAssociation);
			criteria.setFetchMode(fetchedAssociation, FetchMode.JOIN);
		}
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(final String propertyName, final Object value, final String... fetchedAssociations) {
		final Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq(propertyName, value));
		for (final String fetchedAssociation : fetchedAssociations) {
			criteria.createAlias(fetchedAssociation, fetchedAssociation);
			criteria.setFetchMode(fetchedAssociation, FetchMode.JOIN);
		}
		return (T) criteria.uniqueResult();
	}

	@Override
	public List<T> findNotRemovedOrderedByCreationDate() {
		return findByCriteria();
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final Criterion... criterion) {
		final Criteria crit = getSession().createCriteria(getPersistentClass());
		for (final Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@Override
	public Long countAll() {

		final Criteria query = createCriteria();
		query.setProjection(Projections.rowCount());

		return (Long) query.uniqueResult();

	}

	protected void setRowCountToQuery(final Criteria query) {
		query.setProjection(Projections.rowCount());
	}

	protected void setLimitAndOrderToQuery(final Criteria query, final int offset, final int limit,
			final ListOrderColumn listOrderColumn, final boolean orderAsc) {

		query.setFirstResult(offset);
		query.setMaxResults(limit);

		Order order;
		if (orderAsc) {
			order = Order.asc(listOrderColumn.getField());
			if (listOrderColumn.isOrderNullsRevert()) {
				order = order.nulls(NullPrecedence.FIRST);
			}
		} else {
			order = Order.desc(listOrderColumn.getField());
			if (listOrderColumn.isOrderNullsRevert()) {
				order = order.nulls(NullPrecedence.LAST);
			}
		}

		query.addOrder(order);
	}

	/**
	 * Setup {@link Criteria} query results paging from
	 * {@link AbstractListFitler}.
	 *
	 * @param query
	 *            Criteria query which paging is going to be setup.
	 * @param filter
	 *            Filter that holds paging and ordering values.
	 * @author DZCAVMB
	 */
	protected void setUpCriteriaPaging(final Criteria query, final AbstractListFilter filter) {
		if (LOG.isTraceEnabled()) {
			LOG.trace("setUpCriteriaPaging(...) - start");
		}

		final int firstResult = (filter.getPage() - 1) * filter.getItemsPerPage();
		final int maxResults = filter.getItemsPerPage();

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
	}

	/**
	 * Setup query ordering from {@link AbstractListFitler}.
	 *
	 * @param query
	 *            Criteria query which ordering is going to be setup.
	 * @param filter
	 *            Filter that holds paging and ordering values.
	 * @author DZCAVMB
	 */
	protected void setUpCriteriaOrdering(final Criteria query, final AbstractListFilter filter) {
		if (LOG.isTraceEnabled()) {
			LOG.trace("setUpCriteriaOrdering(...) - start");
		}

		final ListOrderColumn listOrderColumn = filter.getOrderType();

		Order order;
		if (filter.getOrderDirection().isAscending()) {
			order = Order.asc(listOrderColumn.getField());
			if (listOrderColumn.isOrderNullsRevert()) {
				order = order.nulls(NullPrecedence.FIRST);
			}
		} else {
			order = Order.desc(listOrderColumn.getField());
			if (listOrderColumn.isOrderNullsRevert()) {
				order = order.nulls(NullPrecedence.LAST);
			}
		}

		query.addOrder(order);
	}

	protected void setOrderToQuery(final Criteria query, final ListOrderColumn listOrderColumn,
			final boolean orderAsc) {

		query.addOrder(orderAsc ? Order.asc(listOrderColumn.getField()) : Order.desc(listOrderColumn.getField()));

	}

	protected String replaceAsteriskForLike(final String modelMask) {
		return modelMask.replace(CommonConstants.ASTERISK, CommonConstants.UNDERSCORE);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByQuery(final String queryString, final String[] paramNames, final Object[] paramValues) {
		final Query query = getSession().createQuery(queryString);
		for (int i = 0; i < paramNames.length; i++) {
			query.setParameter(paramNames[i], paramValues[i]);
		}
		return query.list();
	}

	/** {@inheritDoc} */
	@Override
	public void saveAllInBatch(final List<T> entityList) {
		final Session session = getSession();

		for (int i = 0; i < entityList.size(); i++) {
			session.save(entityList.get(i));
			if (i + 1 % 50 == 0) { // 50, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		}
	}
}
