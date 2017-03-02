/**
 * 
 */
package cz.ucl.hatchery.carevidence.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author User
 *
 */
public class QueryBuilder {

	private static final String SPACE = " ";
	private Map<String, Object> queryParams;
	private Session s;
	private String queryString;

	public QueryBuilder(final Session s) {
		this.s = s;
		queryParams = new HashMap<String, Object>();
	}

	public QueryBuilder(final Session em, final String baseQuery) {
		this(em);
		this.queryString = baseQuery;
	}

	public QueryBuilder append(final String queryPart, final String paramName, final Object param) {
		if (!queryPart.startsWith(SPACE)) {
			queryString += SPACE;
		}
		queryString += queryPart;
		if (paramName != null) {
			queryParams.put(paramName, param);
		}
		return this;
	}

	public QueryBuilder appendIfNotNull(final String queryPart, final String paramName, final Object param) {
		if (param != null) {
			append(queryPart, paramName, param);
		}
		return this;
	}

	public QueryBuilder appendBetween(final String queryPart, final String paramFromName, final String paramToName,
			final Object paramFrom, final Object paramTo) {
		if (!queryPart.startsWith(SPACE)) {
			queryString += SPACE;
		}
		queryString += queryPart;
		queryParams.put(paramFromName, paramFrom);
		queryParams.put(paramToName, paramTo);

		return this;
	}

	public Query build() {
		final Query query = s.createQuery(queryString);
		for (final String paramName : queryParams.keySet()) {
			query.setParameter(paramName, queryParams.get(paramName));
		}
		return query;
	}

}
