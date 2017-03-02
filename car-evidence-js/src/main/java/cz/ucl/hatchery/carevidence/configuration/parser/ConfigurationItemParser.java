package cz.ucl.hatchery.carevidence.configuration.parser;

public interface ConfigurationItemParser<T> {

	public T encode(String value);

}
