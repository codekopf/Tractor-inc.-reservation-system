/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * Object holding map of objects of different types. Allows auto cast on
 * getObject method call.
 * 
 * @author Marek Lobotka
 * 
 */
public class ObjectMap extends AbstractMap<String, Object> {

	// Input object to validate
	final Map<String, Object> objectMap;

	/**
	 * Default constructor
	 * 
	 * @param objectMap
	 */
	public ObjectMap(final Map<String, Object> objectMap) {
		if (objectMap == null) {
			throw new IllegalArgumentException("objectMap");
		}
		this.objectMap = objectMap;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(final String key, final Class<T> clazz) {
		final Object value = objectMap.get(key);
		if (value != null && !clazz.isInstance(value)) {
			throw new IllegalArgumentException("Value for given key=" + key + " is not of given type="
					+ clazz.getCanonicalName() + ". Value type=" + value.getClass().getCanonicalName());
		}
		return (T) value;
	}

	/** {@inheritDoc} */
	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return objectMap.entrySet();
	}

}
