/**
 *
 */
package cz.ucl.hatchery.carevidence.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * @author Marek Lobotka
 */
public class ReflectionUtil {

	private static final String PREFIX_GETTER = "get";
	private static final String PREFIX_SETTER = "set";

	public static boolean isGetter(final Method method) {
		if (!method.getName().startsWith(PREFIX_GETTER)) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		if (void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isSetter(final Method method) {
		if (!method.getName().startsWith(PREFIX_SETTER)) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}

	public static Set<Method> getAllGetters(final Class<?> clazz) {
		final Set<Method> methods = new HashSet<Method>();
		for (final Method method : clazz.getMethods()) {
			if (ReflectionUtil.isGetter(method)) {
				methods.add(method);
			}
		}
		return methods;
	}

	public static Annotation[] findAllAnnotations(final Method method,
			final Class<? extends Annotation>... annotationClasses) {
		if (annotationClasses == null || annotationClasses.length == 0) {
			return method.getAnnotations();
		}

		final List<Annotation> annotations = new ArrayList<Annotation>();
		for (final Annotation ann : method.getAnnotations()) {
			for (final Class<? extends Annotation> annotationClass : annotationClasses) {
				if (ann.annotationType().equals(annotationClass)
						|| ann.annotationType().isAnnotationPresent(annotationClass)) {
					annotations.add(ann);
				}
			}
		}
		return annotations.toArray(new Annotation[annotations.size()]);
	}

	public static Annotation[] findAllAnnotations(final Class<?> clazz,
			final Class<? extends Annotation>... annotationClasses) {
		if (annotationClasses == null || annotationClasses.length == 0) {
			return clazz.getAnnotations();
		}

		final List<Annotation> annotations = new ArrayList<Annotation>();
		for (final Annotation ann : clazz.getAnnotations()) {
			for (final Class<? extends Annotation> annotationClass : annotationClasses) {
				if (ann.annotationType().equals(annotationClass)
						|| ann.annotationType().isAnnotationPresent(annotationClass)) {
					annotations.add(ann);
				}
			}
		}
		return annotations.toArray(new Annotation[annotations.size()]);
	}

	/**
	 * Finds passed annotation of passed method
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T findSpecificMethodAnnotation(final Method method,
			final Class<T> annotation) {
		final Annotation[] anns = method.getAnnotations();
		for (final Annotation ann : anns) {
			if (ann.annotationType().equals(annotation)) {
				return (T) ann;
			}
		}
		return null;
	}

	/**
	 * Get list of values returned by invocation of specifically annotated
	 * methods.
	 *
	 * @param entity
	 *            Entity instance
	 * @param annotationClass
	 *            Annotations
	 * @return List of values returned by invocation of specifically annotated
	 *         methods.
	 * @throws InvalidPropertyException
	 *             Thrown if method invocation problem
	 */
	public static List<Object> getMethodValuesSpecifficallyAnnotated(final Object entity,
			final Class<? extends Annotation>... annotationClass) {
		return getMethodValuesSpecifficallyAnnotated(entity, Object.class, annotationClass);
	}

	/**
	 * Get list of values returned by invocation of methods by given names.
	 *
	 * @param entity
	 *            Entity instance
	 * @param clazz
	 *            Expected return type
	 * @param methodNames
	 *            Names of methods
	 * @return List of values returned by invocation of methods by given names
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getMethodValues(final Object entity, final Class<T> clazz, final String... methodNames) {
		final List<T> result = new ArrayList<T>();

		final Set<String> methodNamesSet = new HashSet<String>();
		Collections.addAll(methodNamesSet, methodNames);

		for (final Method m : entity.getClass().getMethods()) {
			if (methodNamesSet.contains(m.getName())) {
				final Object value = getMethodValue(m, entity);
				if (!clazz.isAssignableFrom(value.getClass())) {
					throw new InvalidPropertyException("Object returned by invocation of " + m.getName()
							+ " method is not type of or assignable from " + clazz.getName() + " class.");
				}
				result.add((T) value);
			}
		}
		return result;
	}

	/**
	 * Get list of values returned by invocation of specifically annotated
	 * methods.
	 *
	 * @param entity
	 *            Entity instance
	 * @param clazz
	 *            Expected return type
	 * @param annotationClass
	 *            Annotations
	 * @return List of values returned by invocation of specifically annotated
	 *         methods
	 * @throws InvalidPropertyException
	 *             Thrown if method invocation problem or incompatible expected
	 *             return type on annotated methods
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getMethodValuesSpecifficallyAnnotated(final Object entity, final Class<T> clazz,
			final Class<? extends Annotation>... annotationClass) throws InvalidPropertyException {
		final List<T> result = new ArrayList<T>();

		for (final Method m : entity.getClass().getMethods()) {
			if (isMethodSpecificallyAnnotated(m, annotationClass)) {
				final Object value = getMethodValue(m, entity);
				if (clazz.isAssignableFrom(value.getClass())) {
					throw new InvalidPropertyException("Object returned by invocation of " + m.getName()
							+ " method is not type of or assignable from " + clazz.getName() + " class.");
				}
				result.add((T) value);
			}
		}
		return result;
	}

	/**
	 * Get list of methods speciffically annotated by given annotation classes
	 *
	 * @param entity
	 *            Entity instance
	 * @param annotationClass
	 *            Annotations
	 * @return List of methods speciffically annotated by given annotation
	 *         classes
	 */
	public static List<Method> getMethodsSpecifficallyAnnotated(final Class<?> clazz,
			final Class<? extends Annotation>... annotationClass) {
		final List<Method> result = new ArrayList<Method>();
		for (final Method m : clazz.getMethods()) {
			if (findAllAnnotations(m, annotationClass).length != 0) {
				result.add(m);
			}
		}
		return result;
	}

	/**
	 * Finds passed annotation of passed method
	 */
	public static boolean isMethodSpecificallyAnnotated(final Object entity, final String property,
			final Class<? extends Annotation>... annotations) {
		final Method method = getGetter(entity.getClass(), property);
		if (method != null) {
			final Annotation[] anns = method.getAnnotations();
			for (final Annotation ann : anns) {
				for (final Class<? extends Annotation> annotation : annotations) {
					if (ann.annotationType().equals(annotation)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Finds if given object class is annotated with at least one of given
	 * annotations
	 * 
	 * @param object
	 *            Object to check
	 * @param annotations
	 *            Specified annotations
	 * @return true if there is at least one of given annotations present on
	 *         given class, else return false
	 */
	public static boolean isObjectSpecificallyAnnotated(final Object object,
			final Class<? extends Annotation>... annotations) {
		return isClassSpecificallyAnnotated(object.getClass(), annotations);
	}

	/**
	 * Finds if given class is annotated with at least one of given annotations
	 * 
	 * @param clazz
	 *            Class to check
	 * @param annotations
	 *            Specified annotations
	 * @return true if there is at least one of given annotations present on
	 *         given class, else return false
	 */
	public static boolean isClassSpecificallyAnnotated(final Class<?> clazz,
			final Class<? extends Annotation>... annotations) {
		for (final Class<? extends Annotation> annotation : annotations) {
			if (clazz.isAnnotationPresent(annotation)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Get annotation instance of given annotation type on given entity if
	 * present.
	 *
	 * @param entity
	 *            Entity
	 * @param annotation
	 *            Annotation
	 * @return Annotation instance of given annotation type on given entity or
	 *         NULL
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T findSpecificClassAnnotation(final Class<?> entity,
			final Class<T> annotation) {
		final Annotation[] anns = entity.getAnnotations();
		for (final Annotation ann : anns) {
			if (ann.annotationType().equals(annotation)) {
				return (T) ann;
			}
		}
		return null;
	}

	/**
	 * Determines if there is at least one of given annotations present on given
	 * method
	 *
	 * @param method
	 *            Method
	 * @param annotations
	 *            Annotations
	 * @return TRUE if the is at least one of given annotations present on given
	 *         method else return FALSE
	 */
	public static boolean isMethodSpecificallyAnnotated(final Method method,
			final Class<? extends Annotation>... annotations) {
		final Annotation[] anns = method.getAnnotations();
		for (final Annotation ann : anns) {
			for (final Class<? extends Annotation> annotation : annotations) {
				if (ann.annotationType().equals(annotation)) {
					return true;
				}
			}
		}
		return false;
	}

	private static String getGetterName(final String property) {
		return PREFIX_GETTER + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	public static Method[] getAllGettersByProperties(final Class<?> clazz, final String[] properties) {
		final Method[] methods = new Method[properties.length];
		for (int i = 0; i < properties.length; i++) {
			methods[i] = ReflectionUtil.getGetter(clazz, properties[i]);
		}
		return methods;
	}

	/**
	 * Get getter method by given property name
	 * 
	 * @param clazz
	 * @param property
	 *            Property name of requested getter
	 * @return Getter Method for given property
	 */
	public static Method getGetter(final Class<?> clazz, final String property) {
		final String getterName = getGetterName(property);
		try {
			final Method getter = clazz.getMethod(getterName);
			return getter;
		} catch (final NoSuchMethodException e) {
			throw new IllegalStateException("There is no getter found on class=" + clazz + " for property=" + property);
		}
	}

	/**
	 * Call property setter (dot notation for property can be used)
	 *
	 * @param property
	 *            Property name
	 * @param target
	 *            Target object
	 * @param value
	 *            Value to set
	 */
	public static void setPropertyValue(final String property, final Object target, final Object value)
			throws InvalidPropertyException {
		if (property.contains(".")) {
			final String currentProperty = property.substring(0, property.indexOf("."));
			final Object nextObject = getSimplePropertyValue(currentProperty, target);
			if (nextObject == null) {
				return;
			}
			final String nextProperty = property.substring(property.indexOf(".") + 1);

			setPropertyValue(nextProperty, nextObject, value);
		} else {
			setSimplePropertyValue(property, target, value);
		}
	}

	/**
	 * Call property getter (dot notation for property can be used) on target
	 * object recursively
	 *
	 * @param property
	 *            Property Name
	 * @param target
	 *            Target object
	 * @return Get value
	 */
	public static Object getPropertyValue(final String property, final Object target) throws InvalidPropertyException {
		if (property.contains(".")) {
			final String currentProperty = property.substring(0, property.indexOf("."));
			final Object nextObject = getSimplePropertyValue(currentProperty, target);
			if (nextObject == null) {
				return null;
			}

			final String nextProperty = property.substring(property.indexOf(".") + 1);
			if (nextObject instanceof Collection<?>) {
				return getPropertyValue(nextProperty, (Collection<?>) nextObject);
			} else {
				return getPropertyValue(nextProperty, nextObject);
			}
		} else {
			return getSimplePropertyValue(property, target);
		}
	}

	/**
	 * Call property getters (dot notation for property can be used) on target
	 * collection recursively
	 *
	 * @param property
	 *            Property Name
	 * @param target
	 *            Target collection
	 * @return Get value
	 */
	private static List<Object> getPropertyValue(final String property, final Collection<?> target) {
		final List<Object> resultList = new ArrayList<Object>();
		for (final Object nextObjectItem : target) {
			final Object nextObjectItemValue = getPropertyValue(property, nextObjectItem);
			if (nextObjectItemValue instanceof Collection<?>) {
				resultList.addAll((Collection<?>) nextObjectItemValue);
			} else {
				resultList.add(nextObjectItemValue);
			}
		}
		return resultList;
	}

	/**
	 * Call property setter, support only simple property name
	 *
	 * @param property
	 *            Property name
	 * @param target
	 *            Target object
	 * @param value
	 *            Value to set
	 */
	private static void setSimplePropertyValue(final String property, final Object target, final Object value)
			throws InvalidPropertyException {
		final Class<?> clazz = target.getClass();

		final String setterName = PREFIX_SETTER + Character.toUpperCase(property.charAt(0)) + property.substring(1);
		try {
			final Method setter = clazz.getMethod(setterName, clazz.getDeclaredField(property).getType());
			setter.invoke(target, value);
		} catch (final NoSuchMethodException e) {
			throw new InvalidPropertyException(e);
		} catch (final NoSuchFieldException e) {
			throw new InvalidPropertyException(e);
		} catch (final InvocationTargetException e) {
			throw new InvalidPropertyException(e);
		} catch (final IllegalAccessException e) {
			throw new InvalidPropertyException(e);
		}
	}

	/**
	 * Call property getter, support only simple property name
	 *
	 * @param property
	 *            Property Name
	 * @param target
	 *            Target object
	 * @return Get value
	 */
	private static Object getSimplePropertyValue(final String property, final Object target)
			throws InvalidPropertyException {
		final Class<?> clazz = target.getClass();

		final String getterName = getGetterName(property);
		Object value = null;
		try {
			final Method getter = clazz.getMethod(getterName);
			value = getter.invoke(target);
		} catch (final NoSuchMethodException e) {
			throw new InvalidPropertyException(e);
		} catch (final InvocationTargetException e) {
			throw new InvalidPropertyException(e);
		} catch (final IllegalAccessException e) {
			throw new InvalidPropertyException(e);
		}

		return value;
	}

	public static <T> T getGetterValue(final String property, final Object target, final Class<T> returnClazz) {
		final String getterName = getGetterName(property);
		return getMethodValue(target, getterName, returnClazz);
	}

	public static boolean isMethodPresent(final Object target, final String methodName,
			final Class<?>... parameterType) {
		// Exception flow control but there is no suitable public method
		// declared on java.lang.Class
		try {
			target.getClass().getDeclaredMethod(methodName, parameterType);
			return true;
		} catch (final NoSuchMethodException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getMethodValue(final Object target, final String methodName, final Class<T> returnClazz,
			final Class<?>... parameterType) {
		final Method method;
		try {
			method = target.getClass().getDeclaredMethod(methodName, parameterType);
		} catch (final NoSuchMethodException e) {
			throw new IllegalArgumentException("There is no such method=" + methodName, e);
		}

		final Object getterValue = getMethodValue(method, target);
		// type check done by checking getterReturnType, there is no need to
		// check again
		return (T) getterValue;
	}

	public static Object getGetterValue(final Method getter, final Object target) {
		if (!isGetter(getter)) {
			throw new IllegalArgumentException("Given method is not getter");
		}
		return getMethodValue(getter, target);
	}

	public static Object setSetterValue(final Method setter, final Object target, final Object value) {

		if (!isSetter(setter)) {
			throw new IllegalArgumentException("Given method is not setter");
		}

		try {
			setter.invoke(target, value);
		} catch (final InvocationTargetException e) {
			throw new InvalidPropertyException(e);
		} catch (final IllegalAccessException e) {
			throw new InvalidPropertyException(e);
		}

		return value;
	}

	public static Object getMethodValue(final Method method, final Object target) {
		Object value = null;
		try {
			value = method.invoke(target);
		} catch (final InvocationTargetException e) {
			throw new InvalidPropertyException(e);
		} catch (final IllegalAccessException e) {
			throw new InvalidPropertyException(e);
		}

		return value;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static Set<Method> getAllSetters(final Class<?> clazz) {
		final Set<Method> methods = new HashSet<Method>();
		for (final Method method : clazz.getMethods()) {
			if (ReflectionUtil.isSetter(method)) {
				methods.add(method);
			}
		}
		return methods;
	}

	/**
	 * @param getter
	 * @return
	 */
	public static Method getSetterForGetter(final Method getter) {
		final String setterName = getter.getName().replaceFirst(PREFIX_GETTER, PREFIX_SETTER);
		final Class<?>[] setterMethodParams = { getter.getReturnType() };
		try {
			return getter.getDeclaringClass().getMethod(setterName, setterMethodParams);
		} catch (final SecurityException e) {
			throw new InvalidPropertyException(e);
		} catch (final NoSuchMethodException e) {
			throw new InvalidPropertyException(e);
		}
	}

	public static String getFieldNameForGetter(final Method getter) {
		if (!isGetter(getter)) {
			throw new IllegalArgumentException("getter");
		}
		final String getterName = getter.getName();
		return Character.toLowerCase(getterName.charAt(3)) + getterName.substring(4);
	}

	/**
	 * Gets field value ignoring access modifier
	 *
	 * @param o
	 *            Object
	 * @param fieldName
	 *            name of Field
	 * @return Value of field
	 */
	public static Object getPrivateField(final Object o, final String fieldName) {
		if (o == null) {
			throw new IllegalArgumentException("Parameter o can not be null");
		}
		if (fieldName == null) {
			throw new IllegalArgumentException("Parameter fieldName can not be null");
		}

		final Field fields[] = o.getClass().getDeclaredFields();
		for (final Field field : fields) {
			if (fieldName.equals(field.getName())) {
				try {
					final Object resultObject;
					final boolean wasAccessible = field.isAccessible();
					field.setAccessible(true);
					resultObject = field.get(o);
					field.setAccessible(wasAccessible);
					return resultObject;
				} catch (final IllegalAccessException ex) {
					throw new RuntimeException("IllegalAccessException accessing " + fieldName);
				}
			}
		}

		throw new InvalidPropertyException("Field '" + fieldName + "' not found");
	}

	/**
	 * Invoke method ignoring access modifier
	 *
	 * @param o
	 *            Object
	 * @param methodName
	 *            Name of method
	 * @param params
	 *            List of parameters
	 * @return Return value of called method
	 */
	public static Object invokePrivateMethod(final Object o, final String methodName, final Object[] params) {
		if (o == null) {
			throw new IllegalArgumentException("Parameter o can not be null");
		}
		if (methodName == null) {
			throw new IllegalArgumentException("Parameter methodName can not be null");
		}
		if (params == null) {
			throw new IllegalArgumentException("Parameter params can not be null");
		}

		final Method methods[] = o.getClass().getDeclaredMethods();
		for (final Method method : methods) {
			if (methodName.equals(method.getName())) {
				try {
					final Object resultObject;
					final boolean wasAccessible = method.isAccessible();
					method.setAccessible(true);
					resultObject = method.invoke(o, params);
					method.setAccessible(wasAccessible);
					return resultObject;
				} catch (final IllegalAccessException ex) {
					throw new RuntimeException("IllegalAccessException accessing " + methodName);
				} catch (final InvocationTargetException ite) {
					throw new RuntimeException("InvocationTargetException accessing " + methodName);
				}
			}
		}
		throw new InvalidPropertyException("Method '" + methodName + "' not found");
	}

	public static Object[] getGettersValues(final Method[] method, final Object object) {
		final Object[] values = new Object[method.length];
		for (int i = 0; i < method.length; i++) {
			values[i] = getGetterValue(method[i], object);
		}
		return values;
	}

	/**
	 * Sets {@code toInject} value on the {@code target} on single field of type
	 * {@code targetFieldType}.
	 *
	 * @param targetFieldType
	 *            not null, type of the field on which {@code toInject} must be
	 *            set. Must be exactly the same as declared in {@code target}
	 * @param target
	 *            not null, target object on which to set {@code toInject}
	 * @param toInject
	 *            what to be injected
	 * @throws IllegalArgumentException
	 *             if {@code target} has not field of type
	 *             {@code targetFieldType}
	 * @throws IllegalArgumentException
	 *             if {@code target} more then one field of type
	 *             {@code targetFieldType}
	 */
	public static void setByFieldType(final Class<?> targetFieldType, final Object target, final Object toInject) {
		Assert.notNull(targetFieldType, "targetFieldType");
		Assert.notNull(target, "target");
		try {
			injectOnFieldOfType(targetFieldType, target, toInject);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void injectOnFieldOfType(final Class<?> targetFieldType, final Object target, final Object toInject)
			throws IllegalAccessException {
		final SetOnSingleFieldOfTypeCallback callback = new SetOnSingleFieldOfTypeCallback(targetFieldType, target,
				toInject);
		ReflectionUtils.doWithFields(target.getClass(), callback);
		if (!callback.fieldWasSet) {
			throw new IllegalArgumentException("not field of type " + targetFieldType.getName()
					+ " found for target.class=" + target.getClass().getName() + "\n"
					+ " Did you specify implementation Class, but field had interface Class?");
		}
	}

	private static class SetOnSingleFieldOfTypeCallback implements ReflectionUtils.FieldCallback {

		private final Class<?> targetFieldType;
		private final Object target;
		private final Object toInject;

		boolean fieldWasSet = false;

		public SetOnSingleFieldOfTypeCallback(final Class<?> targetFieldType, final Object target,
				final Object toInject) {
			this.targetFieldType = targetFieldType;
			this.target = target;
			this.toInject = toInject;
		}

		@Override
		public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
			if (targetFieldType.equals(field.getType())) {
				if (fieldWasSet) {
					throw new IllegalArgumentException(
							"found more than one field of type " + targetFieldType.getName());
				}
				field.setAccessible(true);
				field.set(target, toInject);
				fieldWasSet = true;
			}
		}
	}
}
