/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author User
 *
 */
public class CarDTO implements Serializable {

	private Long id;
	private String type;
	private String vin;
	private String carState;
	private Date dateOfAcquisition;
	private Date dateOfLastTechnicalCheck;
	private BigDecimal price;

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
	public String getCarState() {
		return carState;
	}

	/**
	 * @param carState the carState to set
	 */
	public void setCarState(final String carState) {
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

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", type=" + type + ", vin=" + vin + ", carState=" + carState
				+ ", dateOfAcquisition=" + dateOfAcquisition + ", dateOfLastTechnicalCheck=" + dateOfLastTechnicalCheck
				+ ", price=" + price + "]";
	}

}
