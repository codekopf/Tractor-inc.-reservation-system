/**
 * 
 */
package cz.ucl.hatchery.carevidence.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Andrej Buday
 * @description Class handling client's information
 */

@Entity
@Table(name = "car_client")
public class Client {

	@Id
	@SequenceGenerator(name = "CLIENT_ID_GENERATOR", sequenceName = "HIBERNATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_ID_GENERATOR")
	private Long id;

	@Column(name = "name")
	private String clientName;

	@Column(name = "surname")
	private String clientSurname;

	@Column(name = "ico")
	private String clientICO;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date clientDateOfRegistraion;

	@Column(name = "email")
	private String clientEmail;

	@Column(name = "ceil_phone")
	private String clientPhone;

	@OneToMany(mappedBy = "lendedCarClientID")
	private Set<Lending> lendings;

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