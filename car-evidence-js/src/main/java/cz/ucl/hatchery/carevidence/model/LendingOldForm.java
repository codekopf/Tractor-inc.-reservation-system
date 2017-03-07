/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.math.BigDecimal;
import java.util.Date;

import cz.ucl.hatchery.carevidence.entity.Car;
import cz.ucl.hatchery.carevidence.entity.Client;

/**
 * @author User
 *
 */
public class LendingOldForm {

	private Long id;
	private Car car;
	private Date dateFrom;
	private Date dateTo;
	private BigDecimal price;
	private Client carClient;
	private int lattitude;
	private int longitude;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(final Car car) {
		this.car = car;
	}

	/**
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(final Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public Date getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(final Date dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the carClient
	 */
	public Client getCarClient() {
		return carClient;
	}

	/**
	 * @param carClient the carClient to set
	 */
	public void setCarClient(final Client carClient) {
		this.carClient = carClient;
	}

	/**
	 * @return the lattitude
	 */
	public int getLattitude() {
		return lattitude;
	}

	/**
	 * @param lattitude the lattitude to set
	 */
	public void setLattitude(final int lattitude) {
		this.lattitude = lattitude;
	}

	/**
	 * @return the longitude
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(final int longitude) {
		this.longitude = longitude;
	}

}
