package cz.ucl.hatchery.carevidence.util.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Wraps LoggerFactory and add ability of creating logger of current class
 * automatically
 *
 */
public class LoggerFactory {

	/**
	 * Get logger for class which has called the method
	 * 
	 * @return {@link Logger}
	 */
	public static Logger getLogger() {
		final StackTraceElement[] array = Thread.currentThread().getStackTrace();
		for (int i = 0; i < array.length - 1; i++) {
			// Stack trace contains method for create stack trace also
			if (LoggerFactory.class.getName().equals(array[i].getClassName())) {
				return getLogger(array[i + 1].getClassName());
			}
		}
		return getLogger("loggerUnknownClass");
	}

	/**
	 * Get logger for given class using slf4j.LoggerFactory
	 *
	 * @param clazz
	 * @return {@link Logger}
	 */
	public static Logger getLogger(final Class<?> clazz) {
		return org.slf4j.LoggerFactory.getLogger(clazz);
	}

	/**
	 * Get logger for given name using slf4j.LoggerFactory
	 *
	 * @param loggerName
	 * @return {@link Logger}
	 */
	public static Logger getLogger(final String loggerName) {
		return org.slf4j.LoggerFactory.getLogger(loggerName);
	}

	/**
	 * Get ILoggerFactory using slf4j.LoggerFactory
	 * 
	 * @return {@link ILoggerFactory}
	 */
	public static ILoggerFactory getILoggerFactory() {
		return org.slf4j.LoggerFactory.getILoggerFactory();
	}
}
