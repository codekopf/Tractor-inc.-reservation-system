package cz.ucl.hatchery.carevidence.access.authentication;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import cz.ucl.hatchery.carevidence.access.CustomUser;
import cz.ucl.hatchery.carevidence.configuration.ApplicationConfiguration;

public abstract class Authenticator {

	private CustomUser user = new CustomUser();
	private Locale locale;

	protected abstract void processAuthentication(HttpServletRequest request, ApplicationConfiguration systemConfig);

	public abstract String getType();

	public void authenticate(final HttpServletRequest request, final ApplicationConfiguration systemConfig)
			throws Exception {
		processAuthentication(request, systemConfig);
	}

	public CustomUser getCustomUser() {
		return user;
	}

	protected void setCustomUser(final CustomUser user) {
		this.user = user;
	}

	public Locale getLocale() {
		return locale;
	}

	protected void setLocale(final Locale locale) {
		this.locale = locale;
	}

}
