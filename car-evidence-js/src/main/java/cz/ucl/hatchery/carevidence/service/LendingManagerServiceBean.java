
/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ucl.hatchery.carevidence.dao.CarDAO;
import cz.ucl.hatchery.carevidence.dao.ClientDAO;
import cz.ucl.hatchery.carevidence.dao.LendingDAO;
import cz.ucl.hatchery.carevidence.entity.Lending;
import cz.ucl.hatchery.carevidence.model.AvailableCar;
import cz.ucl.hatchery.carevidence.model.LendingDTO;
import cz.ucl.hatchery.carevidence.model.LendingFilter;
import cz.ucl.hatchery.carevidence.model.LendingNewForm;
import cz.ucl.hatchery.carevidence.model.LendingOldForm;
import cz.ucl.hatchery.carevidence.model.enumeration.CarsType;
import cz.ucl.hatchery.carevidence.util.DTOConverter;

/**
 * @author User
 *
 */

@Service
@Transactional
public class LendingManagerServiceBean implements LendingManagerService {

	@Autowired
	private LendingDAO lendingDAO;

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public void createNewLending(final LendingNewForm lendingNewForm) {

		final Lending lending = createGeneralLending();

		final Long car = lendingNewForm.getCar();
		final Long client = lendingNewForm.getClient();

		lending.setCar(carDAO.findById(car));
		lending.setDateFrom(lendingNewForm.getDateFrom());
		lending.setDateTo(lendingNewForm.getDateTo());
		lending.setPrice(lendingNewForm.getPrice());
		lending.setCarClient(clientDAO.findById(client));
		lending.setLattitude(lendingNewForm.getLattitude());
		lending.setLongitude(lendingNewForm.getLongitude());

		// // set parametrs from dto
		// setFieldFromDto(lendingNewForm, lending);

		save(lending);
	}

	@Override
	public void saveOrUpdate(final LendingOldForm oldLendingForm) {

		final Long oldLendingID = oldLendingForm.getId();
		final Lending lending = lendingDAO.findById(oldLendingForm.getId());

		lending.setCar(oldLendingForm.getCar());
		lending.setDateFrom(oldLendingForm.getDateFrom());
		lending.setDateTo(oldLendingForm.getDateTo());
		lending.setPrice(oldLendingForm.getPrice());
		lending.setCarClient(oldLendingForm.getCarClient());
		lending.setLattitude(oldLendingForm.getLattitude());
		lending.setLongitude(oldLendingForm.getLongitude());

		save(lending);

	}

	/**
	 * @param clientNewForm
	 * @param client
	 */
	private void setFieldFromDto(final LendingNewForm lendingNewForm, final Lending lending) {
		lending.setCar(lendingNewForm.getCar());
		lending.setDateFrom(lendingNewForm.getDateFrom());
		lending.setDateTo(lendingNewForm.getDateTo());
		lending.setPrice(lendingNewForm.getPrice());
		lending.setCarClient(lendingNewForm.getCarClient());
		lending.setLattitude(lendingNewForm.getLattitude());
		lending.setLongitude(lendingNewForm.getLongitude());
	}

	/**
	 * @param client
	 */
	private void save(final Lending lending) {
		lendingDAO.save(lending);
	}

	/**
	 * 
	 */
	private Lending createGeneralLending() {
		final Lending lending = new Lending();

		return lending;
	}

	/** {@inheritDoc} */
	@Override
	public List<LendingDTO> getAllLendings() {

		return DTOConverter.convertLending(lendingDAO.findAll());
	}

	/** {@inheritDoc} */
	@Override
	public List<LendingDTO> findLendingByFilter(final LendingFilter filter) {

		return DTOConverter.convertLending(lendingDAO.findByFilter(filter));
	}

	/** {@inheritDoc} */
	@Override
	public List<AvailableCar> findAvailableVehicleByFilter(final LendingFilter filter) {
		final List<AvailableCar> result = new ArrayList<>();

		final List<Object[]> availableVehicle = lendingDAO.findAvailableVehicleByFilter(filter);
		for (final Object[] objects : availableVehicle) {
			result.add(new AvailableCar((Long) objects[0], (CarsType) objects[1], (String) objects[2]));
		}
		return result;
	}

}
