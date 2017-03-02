package cz.ucl.hatchery.carevidence.i18n;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component(value = "localeResolver")
public class CarEvidenceLocaleResolver implements LocaleResolver {

	public static final String ATTR_LOCALE = "locale";
	public static final String PARAM_LANG = "lang";

	private static final Logger LOGGER = LoggerFactory.getLogger(CarEvidenceLocaleResolver.class);

	public CarEvidenceLocaleResolver() throws IOException {
		CarEvidenceLocaleProvider.load();
	}

	public Locale resolveLocale(final HttpServletRequest request) {
		return new Locale(CarEvidenceLocaleProvider.getDefault());
	}

	public void setLocale(final HttpServletRequest request, final HttpServletResponse response, final Locale locale) {
		setLocaleToUserSession(locale);
	}

	public void setLocaleFromRequestParameter(final ServletRequest request) {

		final String language = request.getParameter(PARAM_LANG);
		final Locale newLocale = CarEvidenceLocaleProvider.getSupportedLocale(language);

		setLocaleToUserSession(newLocale);

		LOGGER.debug("locale has been changed to {}", newLocale);

	}

	private void setLocaleToUserSession(final Locale locale) {

		final boolean supported = CarEvidenceLocaleProvider.isSupported(locale);
		final Locale newLocale = supported ? locale : new Locale(CarEvidenceLocaleProvider.getDefault(locale));

		LOGGER.debug("locale has been changed to {}", newLocale);
	}

}
