/**
 * 
 */
package cz.ucl.hatchery.carevidence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import cz.ucl.hatchery.carevidence.model.enumeration.CarState;
import cz.ucl.hatchery.carevidence.model.enumeration.CarsType;

/**
 * @author DZCJS9F
 *
 */
@Entity
public class Car {

	@Id
	@SequenceGenerator(name = "CAR_ID_GENERATOR", sequenceName = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_ID_GENERATOR")
	private Long id;

	@Column(name = "cars_type")
	@Enumerated(EnumType.STRING)
	private CarsType type;

	private String vin;

	@Column(name = "state_type")
	@Enumerated(EnumType.STRING)
	private CarState carState;

	@Column(name = "date_of_acquisition")
	private Date dateOfAcquisition;

	@Column(name = "last_control")
	private Date dateOfLastTechnicalCheck;

	private BigDecimal price;

	// @OneToMany(mappedBy = "repairCarID")
	// private Set<Repair> repairs;

	@OneToMany(mappedBy = "car")
	private Set<Lending> lendings;

	// @OneToMany(mappedBy = "carSTKID")
	// private Set<STK> stks;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 *            TODO - This should be probably deleted
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public CarsType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final CarsType type) {
		this.type = type;
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
	 * @return the carState
	 */
	public CarState getCarState() {
		return carState;
	}

	/**
	 * @param carState the carState to set
	 */
	public void setCarState(final CarState carState) {
		this.carState = carState;
	}

	/**
	 * @return the dateOfAcquisition
	 */
	public Date getDateOfAcquisition() {
		return dateOfAcquisition;
	}

	/**
	 * @param dateOfAcquisition the dateOfAcquisition to set
	 */
	public void setDateOfAcquisition(final Date dateOfAcquisition) {
		this.dateOfAcquisition = dateOfAcquisition;
	}

	/**
	 * @return the dateOfLastTechnicalCheck
	 */
	public Date getDateOfLastTechnicalCheck() {
		return dateOfLastTechnicalCheck;
	}

	/**
	 * @param dateOfLastTechnicalCheck the dateOfLastTechnicalCheck to set
	 */
	public void setDateOfLastTechnicalCheck(final Date dateOfLastTechnicalCheck) {
		this.dateOfLastTechnicalCheck = dateOfLastTechnicalCheck;
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

}