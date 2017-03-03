/**
 * 
 */
package cz.ucl.hatchery.carevidence.entity;

import java.util.Date;

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
 * @description Class handling information about car STK
 */

@Entity
@Table(name = "car_stk")
public class STK {

	@Id
	@SequenceGenerator(name = "CAR_STK_ID_GENERATOR", sequenceName = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_STK_ID_GENERATOR")
	private Long id;

	@JoinColumn(name = "car")
	@ManyToOne
	private Car carSTKID;

	@Column(name = "check_date")
	private Date carSTKCheckDate;

	@Column(name = "is_pass")
	// @Enumerated(EnumType.STRING)
	private char carSTKPass;

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
	 * @return the carSTKID
	 */
	public Car getCarSTKID() {
		return carSTKID;
	}

	/**
	 * @param carSTKID the carSTKID to set
	 */
	public void setCarSTKID(final Car carSTKID) {
		this.carSTKID = carSTKID;
	}

	/**
	 * @return the carSTKCheckDate
	 */
	public Date getCarSTKCheckDate() {
		return carSTKCheckDate;
	}

	/**
	 * @param carSTKCheckDate the carSTKCheckDate to set
	 */
	public void setCarSTKCheckDate(final Date carSTKCheckDate) {
		this.carSTKCheckDate = carSTKCheckDate;
	}

	/**
	 * @return the carSTKPass
	 */
	public char getCarSTKPass() {
		return carSTKPass;
	}

	/**
	 * @param carSTKPass the carSTKPass to set
	 */
	public void setCarSTKPass(final char carSTKPass) {
		this.carSTKPass = carSTKPass;
	}
}
