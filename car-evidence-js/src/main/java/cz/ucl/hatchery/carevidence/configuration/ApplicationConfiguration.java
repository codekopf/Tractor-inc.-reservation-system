package cz.ucl.hatchery.carevidence.configuration;

public interface ApplicationConfiguration {

	Object get(String key);

	String getValue(String key);

	<T> T getValue(String key, Class<T> clazz);

	void setValue(String key, Object value);

}
