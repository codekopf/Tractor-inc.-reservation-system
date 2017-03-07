/**
 * 
 */
package cz.ucl.hatchery.carevidence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Andrej Buday
 * @description Class handling car lending information
 */

@Entity
public class Lending {

	@Id
	@SequenceGenerator(name = "LENDING_ID_GENERATOR", sequenceName = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LENDING_ID_GENERATOR")
	private Long id;

	@JoinColumn(name = "car")
	@ManyToOne
	private Car car;

	@Column(name = "date_from")
	private Date dateFrom;

	@Column(name = "date_to")
	private Date dateTo;

	@Column(name = "price")
	private BigDecimal price;

	@JoinColumn(name = "car_client")
	@ManyToOne
	private Client carClient;

	@Column(name = "lattitude")
	private Integer lattitude;

	@Column(name = "longitude")
	private Integer longitude;

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