/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import cz.ucl.hatchery.carevidence.model.CarDTO;
import cz.ucl.hatchery.carevidence.model.CarFilter;
import cz.ucl.hatchery.carevidence.model.CarNewForm;

/**
 * @author DZCJS9F
 *
 */
public interface CarManagerService {

	public void createNewCar(CarNewForm carNewForm);

	public List<CarDTO> getAllCars();

	public List<CarDTO> findCarsByFilter(CarFilter filter);

}
