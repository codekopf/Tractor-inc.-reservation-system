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
		final Query query = createQueryFromCarFilter(filter);

		return query.list();
	}

	private Query createQueryFromCarFilter(final ClientFilter filter) {
		final QueryBuilder builder = new QueryBuilder(getSession(), "SELECT c FROM Client c WHERE 1 = 1");

		builder.appendIfNotNull("AND c.id = :id", "id", filter.getId());
		builder.appendIfNotNull("AND c.name = :name", "name", filter.getName());
		builder.appendIfNotNull("AND c.surname = :surname", "surname", filter.getSurname());
		builder.appendIfNotNull("AND c.ico = :ico", "ico", filter.getIco());
		builder.appendIfNotNull("AND c.email = :email", "email", filter.getEmail());
		builder.appendIfNotNull("AND c.ceilPhone = :ceilPhone", "ceilPhone", filter.getCeilPhone());

		// TODO - upravit
		// builder.appendIfNotNull("AND c.dateOfAcquisition >= :acquiredFrom", "acquiredFrom",
		// filter.getAcquiredFrom());
		// builder.appendIfNotNull("AND c.dateOfAcquisition <= :acquiredTo", "acquiredTo", filter.getAcquiredTo());
		// builder.appendIfNotNull("AND c.dateOfLastTechnicalCheck >= :checkFrom", "checkFrom",
		// filter.getLastTechnicalCheckFrom());
		// builder.appendIfNotNull("AND c.dateOfLastTechnicalCheck <= :checkTo", "checkTo",
		// filter.getLastTechnicalCheckTo());
		// builder.appendIfNotNull("AND c.price >= :priceFrom", "priceFrom", filter.getPriceFrom());
		// builder.appendIfNotNull("AND c.price <= :priceTo", "priceTo", filter.getPriceTo());

		return builder.build();
	}

}
