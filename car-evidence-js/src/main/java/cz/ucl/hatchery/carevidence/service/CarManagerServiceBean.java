/**
 * 
 */
package cz.ucl.hatchery.carevidence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.ucl.hatchery.carevidence.dao.CarDAO;
import cz.ucl.hatchery.carevidence.entity.Car;
import cz.ucl.hatchery.carevidence.model.CarDTO;
import cz.ucl.hatchery.carevidence.model.CarFilter;
import cz.ucl.hatchery.carevidence.model.CarNewForm;
import cz.ucl.hatchery.carevidence.model.enumeration.CarState;
import cz.ucl.hatchery.carevidence.model.enumeration.CarsType;
import cz.ucl.hatchery.carevidence.util.CoreDateUtil;
import cz.ucl.hatchery.carevidence.util.DTOConverter;

/**
 * @author DZCJS9F
 *
 */
@Service
@Transactional
public class CarManagerServiceBean implements CarManagerService {

	@Autowired
	private CarDAO carDAO;

	@Override
	public void createNewCar(final CarNewForm carNewForm) {

		final Car car = createGeneralCar();

		// set parametrs from dto
		setFieldFromDto(carNewForm, car);

		save(car);
	}

	/**
	 * @param carNewForm
	 * @param car
	 */
	private void setFieldFromDto(final CarNewForm carNewForm, final Car car) {
		car.setPrice(carNewForm.getPrice());
		car.setType(CarsType.valueOf(carNewForm.getType()));
		car.setVin(carNewForm.getVin());
	}

	/**
	 * @param car
	 */
	private void save(final Car car) {
		carDAO.save(car);
	}

	/**
	 * 
	 */
	private Car createGeneralCar() {
		final Car car = new Car();
		car.setCarState(CarState.defaultState());
		car.setDateOfAcquisition(CoreDateUtil.getNow());

		return car;
	}

	/** {@inheritDoc} */
	@Override
	public List<CarDTO> getAllCars() {

		return DTOConverter.convert(carDAO.findAll());
	}

	/** {@inheritDoc} */
	@Override
	public List<CarDTO> findCarsByFilter(final CarFilter filter) {

		return DTOConverter.convert(carDAO.findByFilter(filter));
	}

}
