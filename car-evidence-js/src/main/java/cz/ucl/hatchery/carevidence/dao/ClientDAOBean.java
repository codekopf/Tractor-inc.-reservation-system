/**
 * 
 */
package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cz.ucl.hatchery.carevidence.entity.Client;
import cz.ucl.hatchery.carevidence.model.ClientFilter;
import cz.ucl.hatchery.carevidence.util.QueryBuilder;

/**
 * @author User
 *
 */
@Repository
public class ClientDAOBean extends GenericHibernateDAO<Client, Long> implements ClientDAO {

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Client> findByFilter(final ClientFilter filter) {
		final Query query = createQueryFromClientFilter(filter);

		return query.list();
	}

	private Query createQueryFromClientFilter(final ClientFilter filter) {
		final QueryBuilder builder = new QueryBuilder(getSession(), "SELECT c FROM Client c WHERE 1 = 1");
		System.out.println("HALOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		builder.appendIfNotNull("AND c.id = :id", "id", filter.getId());
		builder.appendIfNotNull("AND c.name = :name", "name", filter.getName());
		builder.appendIfNotNull("AND c.surname = :surname", "surname", filter.getSurname());
		builder.appendIfNotNull("AND c.ico = :ico", "ico", filter.getIco());
		builder.appendIfNotNull("AND c.email = :email", "email", filter.getEmail());
		builder.appendIfNotNull("AND c.ceilPhone = :ceilPhone", "ceilPhone", filter.getCeilPhone());
		builder.appendIfNotNull("AND c.clientDateOfRegistraion >= :acquiredFrom", "acquiredFrom",
				filter.getAcquiredFrom());
		builder.appendIfNotNull("AND c.clientDateOfRegistraion <= :acquiredTo", "acquiredTo", filter.getAcquiredTo());

		return builder.build();
	}

}
