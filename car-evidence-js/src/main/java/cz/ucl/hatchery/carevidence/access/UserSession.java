package cz.ucl.hatchery.carevidence.access;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public interface UserSession extends Serializable {

	public ApplicationUser getCurrentUser();

	public Locale getLocale();

	public void setLocale(Locale locale);

	public String getLastVisitedURI();

	public boolean isAuthenticated();

	public void authenticate(HttpServletRequest request) throws Exception;

	public void setupLastVisitedURI(HttpServletRequest request);

}