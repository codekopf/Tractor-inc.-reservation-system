/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import cz.ucl.hatchery.carevidence.model.LendingDTO;
import cz.ucl.hatchery.carevidence.model.LendingFilter;
import cz.ucl.hatchery.carevidence.model.LendingNewForm;
import cz.ucl.hatchery.carevidence.model.LendingOldForm;

/**
 * @author User
 *
 */
public interface LendingManagerService {

	public void createNewLending(LendingNewForm lendingNewForm);

	public List<LendingDTO> getAllLendings();

	public List<LendingDTO> findLendingByFilter(LendingFilter filter);

	public List<LendingDTO> findAvailableVehicleByFilter(LendingFilter filter);

	public void saveOrUpdate(LendingOldForm oldLendingForm);

}
