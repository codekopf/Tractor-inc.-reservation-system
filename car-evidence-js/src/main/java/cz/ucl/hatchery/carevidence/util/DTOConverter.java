/**
 * 
 */
package cz.ucl.hatchery.carevidence.util;

import java.util.ArrayList;
import java.util.List;

import cz.ucl.hatchery.carevidence.entity.Car;
import cz.ucl.hatchery.carevidence.model.CarDTO;

/**
 * @author User
 *
 */
public class DTOConverter {

	public static CarDTO convert(final Car car) {
		final CarDTO dto = new CarDTO();

		dto.setId(car.getId());
		dto.setPrice(car.getPrice());
		dto.setVin(car.getVin());
		dto.setCarState(car.getCarState().name());
		dto.setType(car.getType().name());
		dto.setDateOfAcquisition(car.getDateOfAcquisition());
		dto.setDateOfLastTechnicalCheck(car.getDateOfLastTechnicalCheck());

		return dto;
	}

	public static List<CarDTO> convert(final List<Car> cars) {
		final List<CarDTO> dtos = new ArrayList<CarDTO>();

		for (final Car car : cars) {
			dtos.add(convert(car));
		}

		return dtos;
	}
}
