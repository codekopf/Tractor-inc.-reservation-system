/**
 * 
 */
package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ucl.hatchery.carevidence.entity.Lending;
import cz.ucl.hatchery.carevidence.model.LendingFilter;
import cz.ucl.hatchery.carevidence.util.QueryBuilder;

/**
 * @author User
 *
 */
@Service
@Transactional
public class LendingDAOBean extends GenericHibernateDAO<Lending, Long> implements LendingDAO {

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Lending> findByFilter(final LendingFilter filter) {
		final Query query = createQueryFromLendingFilter(filter);

		return query.list();
	}

	private Query createQueryFromLendingFilter(final LendingFilter filter) {
		final QueryBuilder builder = new QueryBuilder(getSession(), "SELECT c FROM Lending c WHERE 1 = 1");
		System.out.println("HALOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		builder.appendIfNotNull("AND c.id = :id", "id", filter.getId());
		builder.appendIfNotNull("AND c.car = :car", "car", filter.getCar());
		builder.appendIfNotNull("AND c.dateFrom >= :dateFrom", "dateFrom", filter.getDateFrom());
		builder.appendIfNotNull("AND c.dateTo <= :dateTo", "dateTo", filter.getDateTo());
		builder.appendIfNotNull("AND c.price = :price", "price", filter.getPrice());
		builder.appendIfNotNull("AND c.carClient = :carClient", "carClient", filter.getCarClient());
		builder.appendIfNotNull("AND c.lattitude = :lattitude", "lattitude", filter.getLattitude());
		builder.appendIfNotNull("AND c.longitude = :longitude", "longitude", filter.getLongitude());

		return builder.build();
	}

}
