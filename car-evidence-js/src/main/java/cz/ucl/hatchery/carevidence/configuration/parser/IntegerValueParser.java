package cz.ucl.hatchery.carevidence.configuration.parser;

public class IntegerValueParser implements ConfigurationItemParser<Integer> {

	@Override
	public Integer encode(final String value) {

		try {
			return Integer.valueOf(value);
		} catch (final NumberFormatException nfe) {
			return 0;
		}

	}

}