
/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ucl.hatchery.carevidence.dao.LendingDAO;
import cz.ucl.hatchery.carevidence.entity.Lending;
import cz.ucl.hatchery.carevidence.model.LendingDTO;
import cz.ucl.hatchery.carevidence.model.LendingFilter;
import cz.ucl.hatchery.carevidence.model.LendingNewForm;
import cz.ucl.hatchery.carevidence.model.LendingOldForm;
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

	@Override
	public void createNewLending(final LendingNewForm lendingNewForm) {

		final Lending lending = createGeneralLending();

		// set parametrs from dto
		setFieldFromDto(lendingNewForm, lending);

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

}
