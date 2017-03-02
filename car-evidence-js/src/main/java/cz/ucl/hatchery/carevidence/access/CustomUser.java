package cz.ucl.hatchery.carevidence.access;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomUser implements ApplicationUser {

	private static final long serialVersionUID = 3190369192682229406L;

	private String login = null;

	private String name;
	private String surname;
	private String email;

	private Date initiated;

	public CustomUser() {
	}

	public CustomUser(final String login, final String name, final String surname) {
		this.login = login;
		this.name = name;
		this.surname = surname;
	}

	public CustomUser(final String login) {
		this.login = login;
	}

	@Override
	public String getLogin() {
		return login;
	}

	public void setLogin(final String uid) {
		this.login = uid;
		this.initiated = Calendar.getInstance().getTime();
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getSurname() {
		return surname;

	}

	public void setSurname(final String surName) {
		this.surname = surName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Date getInitiated() {
		return initiated;
	}

	public void setInitiated(final Date initiated) {
		this.initiated = initiated;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
