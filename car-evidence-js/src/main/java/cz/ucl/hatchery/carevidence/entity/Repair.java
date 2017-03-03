/**
 * 
 */
package cz.ucl.hatchery.carevidence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Andrej Buday
 * @description Class handling information about car maintenance
 */

@Entity
@Table(name = "car_repairs")
public class Repair {

	@Id
	@SequenceGenerator(name = "CAR_REPAIR_ID_GENERATOR", sequenceName = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_REPAIR_ID_GENERATOR")
	private Long id;

	@JoinColumn(name = "car")
	@ManyToOne
	private Car repairCarID;

	@Column(name = "price")
	private BigDecimal repairPrice;

	@Column(name = "repair_resolution")
	private String repairResolution;

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
	 * @return the repairCarID
	 */
	public Car getRepairCarID() {
		return repairCarID;
	}

	/**
	 * @param repairCarID the repairCarID to set
	 */
	public void setRepairCarID(final Car repairCarID) {
		this.repairCarID = repairCarID;
	}

	/**
	 * @return the repairPrice
	 */
	public BigDecimal getRepairPrice() {
		return repairPrice;
	}

	/**
	 * @param repairPrice the repairPrice to set
	 */
	public void setRepairPrice(final BigDecimal repairPrice) {
		this.repairPrice = repairPrice;
	}

	/**
	 * @return the repairResolution
	 */
	public String getRepairResolution() {
		return repairResolution;
	}

	/**
	 * @param repairResolution the repairResolution to set
	 */
	public void setRepairResolution(final String repairResolution) {
		this.repairResolution = repairResolution;
	}

}