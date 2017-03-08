/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import cz.ucl.hatchery.carevidence.model.enumeration.CarsType;

/**
 * @author User
 *
 */
public class AvailableCar {

	private Long carId;
	private CarsType carsType;
	private String vin;

	/**
	 * @return the carId
	 */
	public Long getCarId() {
		return carId;
	}

	/**
	 * @param carId the carId to set
	 */
	public void setCarId(final Long carId) {
		this.carId = carId;
	}

	/**
	 * @return the carsType
	 */
	public CarsType getCarsType() {
		return carsType;
	}

	/**
	 * @param carsType the carsType to set
	 */
	public void setCarsType(final CarsType carsType) {
		this.carsType = carsType;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(final String vin) {
		this.vin = vin;
	}

	/**
	 * @param carId
	 * @param carsType
	 * @param vin
	 */
	public AvailableCar(final Long carId, final CarsType carsType, final String vin) {
		super();
		this.carId = carId;
		this.carsType = carsType;
		this.vin = vin;
	}

}
