/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.resource;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public interface EnumClassifierProvider {

	/**
	 * Get map of localized classifiers for given enum without excluded values
	 * (optional)
	 * 
	 * @param enumClass
	 * @param locale
	 *            TODO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	<T extends Enum> Map<String, String> getClassifiers(Class<T> enumClass, Locale locale, T... exclude);

	@SuppressWarnings("rawtypes")
	<T extends Enum> Map<String, String> getClassifiers(Collection<T> enumItems, Locale locale, T... exclude);

	@SuppressWarnings("rawtypes")
	String getClassifier(Enum<? extends Enum> enumValue, Locale locale);

}
