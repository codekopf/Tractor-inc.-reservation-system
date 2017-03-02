/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author DZCJS9F
 *
 */
public class CarNewForm {

	private String type;
	private String vin;
	private BigDecimal price;

	private Date testDatum;

	/**
	 * @return the testDatum
	 */
	public Date getTestDatum() {
		return testDatum;
	}

	/**
	 * @param testDatum
	 *            the testDatum to set
	 */
	public void setTestDatum(final Date testDatum) {
		this.testDatum = testDatum;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin
	 *            the vin to set
	 */
	public void setVin(final String vin) {
		this.vin = vin;
	}

}
