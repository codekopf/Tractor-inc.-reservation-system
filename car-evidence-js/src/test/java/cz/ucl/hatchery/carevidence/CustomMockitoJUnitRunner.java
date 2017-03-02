package cz.ucl.hatchery.carevidence;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Log4jConfigurer;

public class CustomMockitoJUnitRunner extends MockitoJUnitRunner {

	static {
		try {
			Log4jConfigurer.initLogging("classpath:car-evidence-log.xml");
		} catch (final FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	public CustomMockitoJUnitRunner(final Class<?> clazz) throws InvocationTargetException {
		super(clazz);
	}

}
