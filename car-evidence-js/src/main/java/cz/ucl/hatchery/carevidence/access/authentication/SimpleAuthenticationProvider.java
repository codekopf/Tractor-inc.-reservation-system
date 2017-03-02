package cz.ucl.hatchery.carevidence.access.authentication;

import javax.servlet.http.HttpServletRequest;

import cz.ucl.hatchery.carevidence.access.CustomUser;
import cz.ucl.hatchery.carevidence.configuration.ApplicationConfiguration;
import cz.ucl.hatchery.carevidence.i18n.CarEvidenceLocaleProvider;
import cz.ucl.hatchery.carevidence.web.CommonConstants;

public class SimpleAuthenticationProvider extends Authenticator {

	@Override
	public String getType() {
		return AuthenticatorFactory.AUTH_TYPE_SIMPLE;
	}

	@Override
	protected void processAuthentication(final HttpServletRequest request,
			final ApplicationConfiguration systemConfig) {

		setupUser();

	}

	private void setupUser() {

		final CustomUser user = getCustomUser();

		user.setLogin("FakeLogin");
		user.setName("Jan");
		user.setSurname("Seifrt");
		user.setEmail("jan.seifrt@plus4u.cz");

		setLocale(CarEvidenceLocaleProvider.getSupportedLocale(CommonConstants.DEFAULT_LOCALE.getCountry()));

	}

}
