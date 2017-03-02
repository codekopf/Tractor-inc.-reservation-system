package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import cz.ucl.hatchery.carevidence.entity.Car;
import cz.ucl.hatchery.carevidence.model.CarFilter;

public interface CarDAO extends GenericDAO<Car, Long> {

	public List<Car> findByFilter(CarFilter filter);

}
