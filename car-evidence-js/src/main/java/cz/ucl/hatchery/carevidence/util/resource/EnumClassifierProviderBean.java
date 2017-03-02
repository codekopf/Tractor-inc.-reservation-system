/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.resource;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cz.ucl.hatchery.carevidence.util.collections.Collections;
import cz.ucl.hatchery.carevidence.util.collections.Pair;

@Component
public class EnumClassifierProviderBean implements EnumClassifierProvider {

	private static final String CLASSPATH_FOLDER_PREFIX = "enumlabels/";

	private static Map<Pair<?, Locale>, ResourceBundle> enumClassifiers = new HashMap<Pair<?, Locale>, ResourceBundle>();

	private static final Logger LOG = LoggerFactory.getLogger(EnumClassifierProviderBean.class);

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends Enum> Map<String, String> getClassifiers(final Class<T> enumClass, final Locale locale,
			final T... excludes) {
		final Collection<T> enumItems = Arrays.asList(enumClass.getEnumConstants());
		return getClassifiers(enumItems, enumClass, locale, excludes);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Enum> Map<String, String> getClassifiers(final Collection<T> enumItems, final Locale locale,
			final T... excludes) {
		final Class<T> enumClass = Collections.first(enumItems).getDeclaringClass();
		return getClassifiers(enumItems, enumClass, locale, excludes);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String getClassifier(final Enum<? extends Enum> enumValue, final Locale locale) {
		final Pair<?, Locale> resourceBundleIdentifier = Pair.valueOf(enumValue.getDeclaringClass(), locale);
		// Lazy load
		if (!enumClassifiers.containsKey(resourceBundleIdentifier)) {
			loadFromResourceBundle(resourceBundleIdentifier, locale);
		}
		final ResourceBundle bundle = enumClassifiers.get(resourceBundleIdentifier);
		return getClassifier(bundle, enumValue);
	}

	@SuppressWarnings("rawtypes")
	private String getClassifier(final ResourceBundle resourceBundle, final Enum enumItem) {
		final String key = enumItem.name();
		try {
			return resourceBundle.getString(key);
		} catch (final MissingResourceException e) {
			// Exception should be thrown, but set value without localized value
			LOG.warn("No classifier value found for enum value =", key);
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	private <T extends Enum> Map<String, String> getClassifiers(final Collection<T> enumItems, final Class<T> enumClass,
			final Locale locale, final T... excludes) {
		if (enumItems == null || enumItems.isEmpty()) {
			return new LinkedHashMap<String, String>();
		}

		final Pair<?, Locale> resourceBundleIdentifier = Pair.valueOf(enumClass, locale);
		// Lazy load
		if (!enumClassifiers.containsKey(resourceBundleIdentifier)) {
			loadFromResourceBundle(resourceBundleIdentifier, locale);
		}
		final ResourceBundle bundle = enumClassifiers.get(resourceBundleIdentifier);
		return getClassifiers(bundle, enumItems, excludes);
	}

	@SuppressWarnings("rawtypes")
	private <T extends Enum> Map<String, String> getClassifiers(final ResourceBundle resourceBundle,
			final Collection<T> enumItems, final T[] exclude) {
		final Set<T> excludeSet = exclude != null ? new HashSet<T>(Arrays.asList(exclude)) : null;
		final Map<String, String> result = new LinkedHashMap<String, String>();
		for (final Enum enumItem : enumItems) {
			if (excludeSet == null || !excludeSet.contains(enumItem)) {
				final String classifier = getClassifier(resourceBundle, enumItem);
				result.put(enumItem.name(), classifier != null ? classifier : enumItem.name());
			}
		}
		return result;
	}

	/**
	 * @param enumType
	 */
	@SuppressWarnings("rawtypes")
	private void loadFromResourceBundle(final Pair<?, Locale> resourceBundleIdentifier, final Locale locale) {
		@SuppressWarnings("unchecked")
		final Class<? extends Enum> enumClass = (Class<? extends Enum>) resourceBundleIdentifier.getItem1();
		final String resourceBundleName = enumClass.getSimpleName();

		try {
			final ResourceBundle resourceBundle = ResourceBundle.getBundle(CLASSPATH_FOLDER_PREFIX + resourceBundleName,
					locale);
			enumClassifiers.put(resourceBundleIdentifier, resourceBundle);
		} catch (final MissingResourceException e) {
			throw new IllegalStateException(
					"Unable able to locate resource bundle for the specifed base name: " + resourceBundleName);
		}
	};

}
