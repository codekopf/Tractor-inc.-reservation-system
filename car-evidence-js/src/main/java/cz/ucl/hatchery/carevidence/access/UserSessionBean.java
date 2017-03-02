package cz.ucl.hatchery.carevidence.access;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.ucl.hatchery.carevidence.access.authentication.Authenticator;
import cz.ucl.hatchery.carevidence.access.authentication.AuthenticatorFactory;
import cz.ucl.hatchery.carevidence.configuration.ApplicationConfiguration;
import cz.ucl.hatchery.carevidence.configuration.ConfigurationKeys;
import cz.ucl.hatchery.carevidence.util.logging.LoggerFactory;
import cz.ucl.hatchery.carevidence.web.TimeDurationFormatter;

@Service
public class UserSessionBean implements UserSession {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger();

	@Autowired
	private UserSessionHolder userSessionHolder;

	@Autowired
	private ApplicationConfiguration systemConfig;

	public void setUserSessionHolder(final UserSessionHolder userSessionHolder) {
		this.userSessionHolder = userSessionHolder;
	}

	@Override
	public CustomUser getCurrentUser() {
		return userSessionHolder.getCurrectUser();
	}

	public void setCurrentUser(final CustomUser user) {
		this.userSessionHolder.setCurrectUser(user);
	}

	@Override
	public Locale getLocale() {
		return userSessionHolder.getUserLocale();
	}

	@Override
	public void setLocale(final Locale locale) {
		userSessionHolder.setUserLocale(locale);
	}

	@Override
	public String getLastVisitedURI() {
		return userSessionHolder.getLastVisitedUri();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void authenticate(final HttpServletRequest request) throws Exception {

		if (!isAuthenticated()) {

			final String authType = systemConfig.getValue(ConfigurationKeys.KEY_AUTHETICATION_TYPE);
			final Authenticator authenticator = new AuthenticatorFactory().getAuthenticator(authType);

			if (authenticator == null) {
				LOGGER.error("No authenticator for type '" + authType + " ' has not been found.");
				return;
			}

			authenticator.authenticate(request, systemConfig);

			if (userSessionHolder.getUserLocale() == null) {
				userSessionHolder.setUserLocale(authenticator.getLocale());
			}

			if (isAuthenticated()) {

				if (systemConfig.getValue(ConfigurationKeys.KEY_APP_SESSION_TIMEOUT) == null) {

					systemConfig.setValue(ConfigurationKeys.KEY_APP_SESSION_TIMEOUT_SEC,
							request.getSession().getMaxInactiveInterval());
					systemConfig.setValue(ConfigurationKeys.KEY_APP_SESSION_TIMEOUT,
							(new TimeDurationFormatter(request.getSession().getMaxInactiveInterval(), false)
									.getDuration()));

				}

			}

		}

		setupLastVisitedURI(request);

	}

	@Override
	public void setupLastVisitedURI(final HttpServletRequest request) {

		// TODO do provideru
		if (request.getMethod().toLowerCase().equals("get")) {

			final StringBuffer actualURI = new StringBuffer(request.getRequestURI());
			if (StringUtils.isNotBlank(request.getQueryString())) {
				actualURI.append("?");
				actualURI.append(request.getQueryString());
			}

			LOGGER.debug("Request url '{}' with method {}.", actualURI, request.getMethod());

			userSessionHolder.setActualURI(actualURI.toString());

		}

	}

}
