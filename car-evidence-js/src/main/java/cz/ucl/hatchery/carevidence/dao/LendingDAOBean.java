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

	@SuppressWarnings("unchecked")
	public List<Lending> findAvailableVehicleByFilter(final LendingFilter filter) {
		final Query query = createQueryFromLendingFilterForAvailableVehicles(filter);

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

	private Query createQueryFromLendingFilterForAvailableVehicles(final LendingFilter filter) {

		final QueryBuilder builder =
				new QueryBuilder(getSession(), "SELECT C.cars_type, C.vin FROM car AS C WHERE 1 = 1");

		System.out.println("HALOOOOOOOOOOOOOOOOOOOOOOOOOOO");

		builder.appendIfNotNull("C.cars_type = :type", "type", filter.getType());
		builder.appendIfNotNull(
				"AND NOT EXISTS (SELECT * FROM lending AS L WHERE C.id = L.car AND ( (L.date_from BETWEEN :dateFrom",
				"dateFrom", filter.getDateFrom());
		builder.appendIfNotNull("AND :dateTo", "dateTo", filter.getDateTo());
		builder.appendIfNotNull(") OR (L.date_to BETWEEN AND :dateFrom", "dateFrom", filter.getDateFrom());
		builder.appendIfNotNull("AND :dateTo", "dateTo", filter.getDateTo());
		builder.appendIfNotNull(" ) OR (L.date_from <= :dateFrom", "dateFrom", filter.getDateFrom());
		builder.appendIfNotNull(" AND L.date_to >= :dateTo", "dateTo", filter.getDateTo());
		builder.appendIfNotNull(" ) OR (L.date_from >=  :dateFrom", "dateFrom", filter.getDateFrom());
		builder.appendIfNotNull(" AND L.date_to <=  :dateTo", "dateTo", filter.getDateTo());

		return builder.build();

		// SELECT C.cars_type, C.vin FROM car AS C WHERE C.cars_type='BULLDOZER' AND NOT EXISTS (
		// SELECT * FROM lending AS L WHERE
		// C.id = L.car AND (
		// (L.date_from BETWEEN '2000-07-27' AND '2000-07-28' ) OR
		// (L.date_to BETWEEN '2000-07-27' AND '2000-07-28' ) OR
		// (L.date_from <= '2000-07-27' AND L.date_to >= '2000-07-28' ) OR
		// (L.date_from >= '2000-07-27' AND L.date_to <= '2000-07-28' )
		// )
	}

}
