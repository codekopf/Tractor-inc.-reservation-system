/**
 * 
 */
package cz.ucl.hatchery.carevidence.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.ucl.hatchery.carevidence.AbstractTransactionalSpringTesting;
import cz.ucl.hatchery.carevidence.entity.Car;

/**
 * @author DZCJS9F
 *
 */
public class CarDaoTest extends AbstractTransactionalSpringTesting {

	@Autowired
	private CarDAO dao;

	@Test
	public void getAllTest() {

		final List<Car> findAll = dao.findAll();
		System.out.println("result" + findAll);
	}

}
