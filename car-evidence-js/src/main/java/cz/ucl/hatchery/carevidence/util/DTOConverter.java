/**
 * 
 */
package cz.ucl.hatchery.carevidence.util;

import java.util.ArrayList;
import java.util.List;

import cz.ucl.hatchery.carevidence.entity.Car;
import cz.ucl.hatchery.carevidence.entity.Client;
import cz.ucl.hatchery.carevidence.entity.Lending;
import cz.ucl.hatchery.carevidence.model.CarDTO;
import cz.ucl.hatchery.carevidence.model.ClientDTO;
import cz.ucl.hatchery.carevidence.model.LendingDTO;

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

	public static ClientDTO convertClient(final Client client) {
		final ClientDTO dto = new ClientDTO();

		dto.setId(client.getId());
		dto.setClientName(client.getClientName());
		dto.setClientSurname(client.getClientSurname());
		dto.setClientICO(client.getClientICO());
		dto.setClientEmail(client.getClientEmail());
		dto.setClientDateOfRegistraion(client.getClientDateOfRegistraion());
		dto.setClientPhone(client.getClientPhone());

		return dto;
	}

	public static List<ClientDTO> convertClient(final List<Client> clients) {
		final List<ClientDTO> dtos = new ArrayList<ClientDTO>();

		for (final Client client : clients) {
			dtos.add(convertClient(client));
		}

		return dtos;
	}

	public static LendingDTO convertLending(final Lending lending) {
		final LendingDTO dto = new LendingDTO();

		dto.setId(lending.getId());
		dto.setCar(lending.getCar());
		dto.setDateFrom(lending.getDateFrom());
		dto.setDateTo(lending.getDateTo());
		dto.setPrice(lending.getPrice());
		dto.setCarClient(lending.getCarClient());
		dto.setLattitude(lending.getLattitude());
		dto.setLongitude(lending.getLongitude());

		return dto;
	}

	public static List<LendingDTO> convertLending(final List<Lending> lendings) {
		final List<LendingDTO> dtos = new ArrayList<LendingDTO>();

		for (final Lending lending : lendings) {
			dtos.add(convertLending(lending));
		}

		return dtos;
	}

}
