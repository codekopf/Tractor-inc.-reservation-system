package cz.ucl.hatchery.carevidence.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarEvidenceLocaleProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarEvidenceLocaleProvider.class);

	private static final String PROP_KEY_LOCALE_DEFAULT = "locale.default";
	private static final String PROP_KEY_LOCALE_SUPPORTED = "locale.supported";
	private static final String PROP_KEY_COUNTRY_SUPPORTED = "country.supported";
	private static final Pattern ALTERNATIVE_LOCALE_PROP_KEY_PATTERN = Pattern
			.compile("^locale\\.default\\.([a-z]{2,})$");

	public static final String PARAM_LANG = "lang";

	private static CarEvidenceLocaleProvider INSTANCE = new CarEvidenceLocaleProvider();

	private String defaultLocale;

	private List<String> locales;
	private List<String> country;
	private Map<String, String> alternative;

	private Map<String, String> dateFormatPattern;
	private Map<String, String> timeFormatPattern;

	private CarEvidenceLocaleProvider() {
		locales = new ArrayList<String>();
		country = new ArrayList<String>();
		alternative = new HashMap<String, String>();
		dateFormatPattern = new HashMap<String, String>();
		timeFormatPattern = new HashMap<String, String>();
	}

	public static void load() throws IOException {

		final Properties properties = new Properties();
		final InputStream is = CarEvidenceLocaleProvider.class.getClassLoader()
				.getResourceAsStream("i18n/car-evidence-locale.properties");
		properties.load(is);
		is.close();

		final String supported = properties.getProperty(PROP_KEY_LOCALE_SUPPORTED);
		final String[] localesFromProps = supported.split(",");

		INSTANCE.locales = Arrays.asList(localesFromProps);
		INSTANCE.defaultLocale = properties.getProperty(PROP_KEY_LOCALE_DEFAULT);

		final String country = properties.getProperty(PROP_KEY_COUNTRY_SUPPORTED);
		final String[] countrysFromProps = country.split(",");

		INSTANCE.country = Arrays.asList(countrysFromProps);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("loaded " + PROP_KEY_LOCALE_SUPPORTED + ": " + Arrays.toString(localesFromProps));
			LOGGER.debug("loaded " + PROP_KEY_LOCALE_DEFAULT + ": " + INSTANCE.defaultLocale);
		}

		setupAlternativeLocale(properties);
		setupDateFormatPatterns(properties);
		setupTimeFormatPatterns(properties);

	}

	private static void setupAlternativeLocale(final Properties properties) {

		@SuppressWarnings("unchecked")
		final Enumeration<String> keys = (Enumeration<String>) properties.propertyNames();
		while (keys.hasMoreElements()) {

			final String key = keys.nextElement();
			final Matcher matcher = ALTERNATIVE_LOCALE_PROP_KEY_PATTERN.matcher(key);
			if (matcher.find()) {

				final String loc = matcher.group(1);
				INSTANCE.alternative.put(loc, properties.getProperty(key));

				LOGGER.debug("loaded lang alternative {} -> {}", loc, properties.getProperty(key));

			}

		}
	}

	private static void setupDateFormatPatterns(final Properties properties) {
		// date format pattern
		for (final String locale : INSTANCE.locales) {

			final String datePattern = properties.getProperty("locale." + locale + ".format.date");
			INSTANCE.dateFormatPattern.put(locale, datePattern);

		}

	}

	private static void setupTimeFormatPatterns(final Properties properties) {
		// date format pattern
		for (final String locale : INSTANCE.locales) {

			final String datePattern = properties.getProperty("locale." + locale + ".format.time");
			INSTANCE.timeFormatPattern.put(locale, datePattern);

		}

	}

	public static boolean isSupported(final Locale locale) {
		return isSupported(locale.getLanguage());
	}

	public static boolean isSupported(final String language) {
		return INSTANCE.locales.contains(language);
	}

	public static String getSupportedLanguage(final Locale locale) {
		return getSupportedLanguage(locale.getLanguage());
	}

	public static String getSupportedLanguage(String language) {

		if (!isSupported(language)) {
			language = getDefault(language);
		}

		return language;

	}

	public static Locale getSupportedLocale(String language) {

		if (!isSupported(language)) {
			language = getDefault(language);
		}

		final int indexOf = INSTANCE.locales.indexOf(language);

		if (INSTANCE.country.size() > indexOf) {
			return new Locale(language, INSTANCE.country.get(indexOf));

		} else {

			return new Locale(language);
		}

	}

	public static String getDefault(final Locale locale) {
		return getDefault(locale.getLanguage());
	}

	public static String getDefault(String language) {

		if (INSTANCE.alternative.containsKey(language)) {
			language = INSTANCE.alternative.get(language);
		} else {
			language = INSTANCE.defaultLocale;
		}

		return language;

	}

	public static String getDefault() {
		return INSTANCE.defaultLocale;
	}

	public static List<String> getSupportedLanguages() {
		return INSTANCE.locales;
	}

	public static String getDateFormat(final String language) {
		return INSTANCE.dateFormatPattern.get(language);
	}

	public static String getDateFormat(final Locale locale) {
		return INSTANCE.dateFormatPattern.get(locale.getLanguage());
	}

	public static String getTimeFormat(final String language) {
		return INSTANCE.timeFormatPattern.get(language);
	}

	public static String getTimeFormat(final Locale locale) {
		return INSTANCE.timeFormatPattern.get(locale.getLanguage());
	}

}
