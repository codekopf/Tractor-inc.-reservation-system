package cz.ucl.hatchery.carevidence.access;

import java.io.Serializable;

public interface ApplicationUser extends Serializable {

	public String getLogin();

	public String getName();

	public String getSurname();

	public String getEmail();

}
