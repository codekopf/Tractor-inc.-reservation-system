package cz.ucl.hatchery.carevidence.util.enumeration;

import java.lang.reflect.Method;

import cz.ucl.hatchery.carevidence.util.reflection.ReflectionUtil;

/**
 * Java enum related utility methods implementation
 * 
 * @author Marek Lobotka
 *
 */
public class EnumUtil {

	public static <T extends Enum<T>> T getEnumValue(final Class<T> enumClass, final Object value,
			final String valueProperty) {

		if (value != null) {
			final Method getter = ReflectionUtil.getGetter(enumClass, valueProperty);
			for (final T enumConstant : enumClass.getEnumConstants()) {
				final Object valueToCompare = ReflectionUtil.getGetterValue(getter, enumConstant);
				if (value.equals(valueToCompare)) {
					return enumConstant;
				}
			}
		}

		throw new IllegalArgumentException("No enum constant " + enumClass + "." + value);
	}
}
