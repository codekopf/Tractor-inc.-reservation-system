/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.util.Date;

/**
 * @author User
 *
 */
public class ClientFilter {

	private Long id;
	private String name;
	private String surname;
	private String ico;
	private Date clientDateOfRegistraion;
	private Date acquiredFrom;
	private Date acquiredTo;
	private String email;
	private String ceilPhone;

	/**
	 * @param id
	 * @param name
	 * @param surname
	 * @param ico
	 * @param clientDateOfRegistraion
	 * @param clientEmail
	 * @param ceilPhone
	 */
	public ClientFilter(final Long id, final String name, final String surname, final String ico,
			final Date clientDateOfRegistraion, final Date acquiredFrom, final Date acquiredTo, final String email,
			final String ceilPhone) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.ico = ico;
		this.clientDateOfRegistraion = clientDateOfRegistraion;
		this.acquiredFrom = acquiredFrom;
		this.acquiredTo = acquiredTo;
		this.email = email;
		this.ceilPhone = ceilPhone;
	}

	/**
	 * 
	 */
	public ClientFilter() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	/**
	 * @return the ico
	 */
	public String getIco() {
		return ico;
	}

	/**
	 * @param ico the ico to set
	 */
	public void setIco(final String ico) {
		this.ico = ico;
	}

	/**
	 * @return the clientDateOfRegistraion
	 */
	public Date getClientDateOfRegistraion() {
		return clientDateOfRegistraion;
	}

	/**
	 * @param clientDateOfRegistraion the clientDateOfRegistraion to set
	 */
	public void setClientDateOfRegistraion(final Date clientDateOfRegistraion) {
		this.clientDateOfRegistraion = clientDateOfRegistraion;
	}

	/**
	 * @return the clientEmail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param clientEmail the clientEmail to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the ceilPhone
	 */
	public String getCeilPhone() {
		return ceilPhone;
	}

	/**
	 * @param ceilPhone the ceilPhone to set
	 */
	public void setCeilPhone(final String ceilPhone) {
		this.ceilPhone = ceilPhone;
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

}
