/**
 * 
 */
package cz.ucl.hatchery.carevidence.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author User
 *
 */
public class ClientDTO implements Serializable {

	private Long id;
	private String clientName;
	private String clientSurname;
	private String clientICO;
	private Date clientDateOfRegistraion;
	private String clientEmail;
	private String clientPhone;

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
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(final String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the clientSurname
	 */
	public String getClientSurname() {
		return clientSurname;
	}

	/**
	 * @param clientSurname the clientSurname to set
	 */
	public void setClientSurname(final String clientSurname) {
		this.clientSurname = clientSurname;
	}

	/**
	 * @return the clientICO
	 */
	public String getClientICO() {
		return clientICO;
	}

	/**
	 * @param clientICO the clientICO to set
	 */
	public void setClientICO(final String clientICO) {
		this.clientICO = clientICO;
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
	public String getClientEmail() {
		return clientEmail;
	}

	/**
	 * @param clientEmail the clientEmail to set
	 */
	public void setClientEmail(final String clientEmail) {
		this.clientEmail = clientEmail;
	}

	/**
	 * @return the clientPhone
	 */
	public String getClientPhone() {
		return clientPhone;
	}

	/**
	 * @param clientPhone the clientPhone to set
	 */
	public void setClientPhone(final String clientPhone) {
		this.clientPhone = clientPhone;
	}

}
