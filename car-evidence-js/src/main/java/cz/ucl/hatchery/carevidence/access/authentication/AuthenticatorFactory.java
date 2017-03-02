package cz.ucl.hatchery.carevidence.access.authentication;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AuthenticatorFactory {

	private static final Logger LOG = Logger.getLogger(AuthenticatorFactory.class);

	private Map<String, Class<? extends Authenticator>> authenticators = new HashMap<String, Class<? extends Authenticator>>();

	public static final String AUTH_TYPE_SIMPLE = "simple";

	public static final String DEFAULT_AUTH_TYPE = AUTH_TYPE_SIMPLE;

	public AuthenticatorFactory() {

		authenticators.put(AUTH_TYPE_SIMPLE, SimpleAuthenticationProvider.class);

	}

	public Authenticator getAuthenticator(final String type) {

		Class<? extends Authenticator> authenticator;
		if (StringUtils.isBlank(type)) {
			authenticator = authenticators.get(DEFAULT_AUTH_TYPE);
		} else {

			authenticator = authenticators.get(type);
			if (authenticator == null) {
				authenticator = authenticators.get(DEFAULT_AUTH_TYPE);
			}

		}

		try {
			return authenticator.newInstance();
		} catch (final InstantiationException e) {
			LOG.error(e.getMessage(), e);
		} catch (final IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
		}

		return null;

	}

}
