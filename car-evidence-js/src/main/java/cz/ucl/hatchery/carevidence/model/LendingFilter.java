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
public class LendingFilter {

	private Long id;
	private Car car;
	private Date dateFrom;
	private Date dateTo;
	private BigDecimal price;
	private Client carClient;
	private Integer lattitude;
	private Integer longitude;

	private String type;

	/**
	 * @param id
	 * @param car
	 * @param dateFrom
	 * @param dateTo
	 * @param price
	 * @param carClient
	 * @param lattitude
	 * @param longitude
	 */
	public LendingFilter(final Long id, final Car car, final Date dateFrom, final Date dateTo, final BigDecimal price,
			final Client carClient, final int lattitude, final int longitude, final String type) {
		super();
		this.id = id;
		this.car = car;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
		this.carClient = carClient;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public LendingFilter() {
		super();
	}

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
	public Integer getLattitude() {
		return lattitude;
	}

	/**
	 * @param lattitude the lattitude to set
	 */
	public void setLattitude(final Integer lattitude) {
		this.lattitude = lattitude;
	}

	/**
	 * @return the longitude
	 */
	public Integer getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(final Integer longitude) {
		this.longitude = longitude;
	}

}
