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
	private Car lendedCar;

	@Column(name = "date_from")
	private Date lendedCarDateFrom;

	@Column(name = "date_to")
	private Date lendedCarDateTo;

	@Column(name = "price")
	private BigDecimal lendedCarPrice;

	@JoinColumn(name = "car_client")
	@ManyToOne
	private Client lendedCarClientID;

	@Column(name = "lattitude")
	private String lendedCarLattitude;

	@Column(name = "longitude")
	private String clientCarLongitude;

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
	 * @return the lendedCar
	 */
	public Car getLendedCar() {
		return lendedCar;
	}

	/**
	 * @param lendedCar the lendedCar to set
	 */
	public void setLendedCar(final Car lendedCar) {
		this.lendedCar = lendedCar;
	}

	/**
	 * @return the lendedCarDateFrom
	 */
	public Date getLendedCarDateFrom() {
		return lendedCarDateFrom;
	}

	/**
	 * @param lendedCarDateFrom the lendedCarDateFrom to set
	 */
	public void setLendedCarDateFrom(final Date lendedCarDateFrom) {
		this.lendedCarDateFrom = lendedCarDateFrom;
	}

	/**
	 * @return the lendedCarDateTo
	 */
	public Date getLendedCarDateTo() {
		return lendedCarDateTo;
	}

	/**
	 * @param lendedCarDateTo the lendedCarDateTo to set
	 */
	public void setLendedCarDateTo(final Date lendedCarDateTo) {
		this.lendedCarDateTo = lendedCarDateTo;
	}

	/**
	 * @return the lendedCarPrice
	 */
	public BigDecimal getLendedCarPrice() {
		return lendedCarPrice;
	}

	/**
	 * @param lendedCarPrice the lendedCarPrice to set
	 */
	public void setLendedCarPrice(final BigDecimal lendedCarPrice) {
		this.lendedCarPrice = lendedCarPrice;
	}

	/**
	 * @return the lendedCarClientID
	 */
	public Client getLendedCarClientID() {
		return lendedCarClientID;
	}

	/**
	 * @param lendedCarClientID the lendedCarClientID to set
	 */
	public void setLendedCarClientID(final Client lendedCarClientID) {
		this.lendedCarClientID = lendedCarClientID;
	}

	/**
	 * @return the lendedCarLattitude
	 */
	public String getLendedCarLattitude() {
		return lendedCarLattitude;
	}

	/**
	 * @param lendedCarLattitude the lendedCarLattitude to set
	 */
	public void setLendedCarLattitude(final String lendedCarLattitude) {
		this.lendedCarLattitude = lendedCarLattitude;
	}

	/**
	 * @return the clientCarLongitude
	 */
	public String getClientCarLongitude() {
		return clientCarLongitude;
	}

	/**
	 * @param clientCarLongitude the clientCarLongitude to set
	 */
	public void setClientCarLongitude(final String clientCarLongitude) {
		this.clientCarLongitude = clientCarLongitude;
	}

}