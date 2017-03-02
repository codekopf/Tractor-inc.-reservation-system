/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author User
 *
 */
public class CarFilter {

	private Long id;
	private String type;
	private String vin;
	private String state;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private Date acquiredFrom;
	private Date acquiredTo;
	private Date lastTechnicalCheckFrom;
	private Date lastTechnicalCheckTo;

	/**
	 * 
	 */
	public CarFilter() {
		super();
	}

	/**
	 * @param id
	 * @param type
	 * @param vin
	 * @param state
	 * @param priceFrom
	 * @param priceTo
	 * @param acquiredFrom
	 * @param acquiredTo
	 * @param lastTechnicalCheckFrom
	 * @param lastTechnicalCheckTo
	 */
	public CarFilter(final Long id, final String type, final String vin, final String state, final BigDecimal priceFrom,
			final BigDecimal priceTo, final Date acquiredFrom, final Date acquiredTo, final Date lastTechnicalCheckFrom,
			final Date lastTechnicalCheckTo) {
		super();
		this.id = id;
		this.type = type;
		this.vin = vin;
		this.state = state;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.acquiredFrom = acquiredFrom;
		this.acquiredTo = acquiredTo;
		this.lastTechnicalCheckFrom = lastTechnicalCheckFrom;
		this.lastTechnicalCheckTo = lastTechnicalCheckTo;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the priceFrom
	 */
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	/**
	 * @return the priceTo
	 */
	public BigDecimal getPriceTo() {
		return priceTo;
	}

	/**
	 * @return the acquiredFrom
	 */
	public Date getAcquiredFrom() {
		return acquiredFrom;
	}

	/**
	 * @return the acquiredTo
	 */
	public Date getAcquiredTo() {
		return acquiredTo;
	}

	/**
	 * @return the lastTechnicalCheckFrom
	 */
	public Date getLastTechnicalCheckFrom() {
		return lastTechnicalCheckFrom;
	}

	/**
	 * @return the lastTechnicalCheckTo
	 */
	public Date getLastTechnicalCheckTo() {
		return lastTechnicalCheckTo;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(final String vin) {
		this.vin = vin;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param priceFrom the priceFrom to set
	 */
	public void setPriceFrom(final BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	/**
	 * @param priceTo the priceTo to set
	 */
	public void setPriceTo(final BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	/**
	 * @param acquiredFrom the acquiredFrom to set
	 */
	public void setAcquiredFrom(final Date acquiredFrom) {
		this.acquiredFrom = acquiredFrom;
	}

	/**
	 * @param acquiredTo the acquiredTo to set
	 */
	public void setAcquiredTo(final Date acquiredTo) {
		this.acquiredTo = acquiredTo;
	}

	/**
	 * @param lastTechnicalCheckFrom the lastTechnicalCheckFrom to set
	 */
	public void setLastTechnicalCheckFrom(final Date lastTechnicalCheckFrom) {
		this.lastTechnicalCheckFrom = lastTechnicalCheckFrom;
	}

	/**
	 * @param lastTechnicalCheckTo the lastTechnicalCheckTo to set
	 */
	public void setLastTechnicalCheckTo(final Date lastTechnicalCheckTo) {
		this.lastTechnicalCheckTo = lastTechnicalCheckTo;
	}

}
