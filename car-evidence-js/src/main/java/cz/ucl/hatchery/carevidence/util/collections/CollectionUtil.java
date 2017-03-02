package cz.ucl.hatchery.carevidence.util.collections;

import static org.apache.commons.lang3.text.WordUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtil {

	private static final String GET_PREFIX = "get";
	private static final String DOT_PATTERN = "\\.";
	private static final String DOT = ".";

	public static <T> Set<T> getCommonElements(final Collection<? extends Collection<T>> collections) {

		final Set<T> common = new LinkedHashSet<T>();
		if (!collections.isEmpty()) {
			final Iterator<? extends Collection<T>> iterator = collections.iterator();
			common.addAll(iterator.next());
			while (iterator.hasNext()) {
				common.retainAll(iterator.next());
				if (common.size() == 0) {
					break;
				}
			}
		}
		return common;
	}

	/**
	 * Adds null predicate
	 * 
	 * @see {@link CollectionUtil#collectValues(Object, String, Predicate)}
	 */
	public static <E> List<E> collectValues(final Object source, final String path) {
		return collectValues(source, path, null);
	}

	/**
	 * This method accumulates property values using getter methods<br/>
	 * Optionally, <code>predicate</code> may be specified, to filter by class
	 * <br/>
	 * <br/>
	 * Simple usage is collecting entity ID's:<br/>
	 * <code>collectValues(fleetActions, "id")</code> will return list of ID's
	 * <br/>
	 * Nested objects, maps and collections are supported, for example:<br/>
	 * <code>collectValues(fleetActions, "creditNoteSet.debitNoteSet.code")</code>
	 * <br/>
	 * 
	 * @param source
	 *            - collection of root objects
	 * @param path
	 *            - path to seeking property, separated by dot
	 * @return - collected values, without any ordering
	 * @see PredicateByType
	 */
	public static <E> List<E> collectValues(final Object source, final String path, final PredicateByType predicate) {
		final Collection<?> sourceCollection = source instanceof Collection ? (Collection<?>) source
				: Arrays.asList(source);
		final List<E> result = new ArrayList<E>(sourceCollection.size());
		collectValuesInternal(sourceCollection, path, result, predicate);
		return result;
	}

	/**
	 * Adds null predicate
	 * 
	 * @see #collectUniqueValues(Object, String, Predicate)
	 */
	public static <E> Set<E> collectUniqueValues(final Object source, final String path) {
		return collectUniqueValues(source, path, null);
	}

	/**
	 * The only difference from
	 * {@link #collectValues(Collection, String, Predicate)} is using HashSet
	 * for storing properties
	 * 
	 * @see #collectValues(Collection, String, Predicate)
	 */
	public static <E> Set<E> collectUniqueValues(final Object source, final String path,
			final PredicateByType predicate) {
		final Collection<?> sourceCollection = source instanceof Collection ? (Collection<?>) source
				: Arrays.asList(source);
		final Set<E> result = new HashSet<E>(sourceCollection.size());
		collectValuesInternal(sourceCollection, path, result, predicate);
		return result;
	}

	@SuppressWarnings("unchecked")
	private static <E, T extends Collection<E>> void collectValuesInternal(final Collection<?> source,
			final String path, final T result, final PredicateByType predicate) {
		final ArrayList<String> innerPath = new ArrayList<String>(Arrays.asList(path.split(DOT_PATTERN)));
		try {
			for (final Object obj : source) {

				Object innerSource = obj;

				final Iterator<String> iterator = innerPath.iterator();

				while (iterator.hasNext()) {
					if (innerSource instanceof Collection) {
						collectValuesInternal((Collection<?>) innerSource, StringUtils.join(iterator, DOT), result,
								predicate);
					} else if (innerSource instanceof Map<?, ?>) {
						collectValuesInternal(((Map<?, ?>) innerSource).values(), StringUtils.join(iterator, DOT),
								result, predicate);
					} else {
						final String nextName = iterator.next();

						// Do predicate check twice due to cases when we are
						// iterating through root collection
						if (isPredicateFalse(predicate, innerSource)) {
							continue;
						}
						innerSource = innerSource.getClass().getMethod(GET_PREFIX + capitalize(nextName))
								.invoke(innerSource);
						if (isPredicateFalse(predicate, innerSource)) {
							continue;
						}

						// innerSource is property we were looking for -> add
						// to result collection
						if (!iterator.hasNext()) {
							result.add((E) innerSource);
						}
					}
				}
			}
		} catch (final Exception e) {
			throw new RuntimeException("Collecting failed", e);
		}
	}

	private static boolean isPredicateFalse(final PredicateByType predicate, final Object obj) {
		return predicate != null && obj.getClass().equals(predicate.getType()) && !predicate.isTrue(obj);
	}

	/**
	 * Convert array of enums to array of strings using their names
	 */
	public static String[] enumToString(final Enum<?>[] enumeration) {
		final String[] str = new String[enumeration.length];
		for (int i = 0; i < enumeration.length; i++) {
			str[i] = enumeration[i].name();
		}
		return str;
	}

	public static boolean notEmpty(final Collection<?> collecion) {
		return collecion != null && !collecion.isEmpty();
	}

	public static <T> T first(final Set<T> set) {
		return set.iterator().next();
	}

	/**
	 * @return count of pairs, where value equals to <code>etalon</code>
	 */
	public static <V> int filteredCount(final Map<?, V> map, final V etalon) {
		int res = 0;
		for (final Entry<?, V> e : map.entrySet()) {
			if (etalon == null) {
				if (e.getValue() == null) {
					res++;
				}
			} else if (etalon.equals(e.getValue())) {
				res++;
			}
		}
		return res;
	}

	/**
	 * Adds default hash map
	 * 
	 * @see #collectionToMap(Collection, String, Map)
	 */
	public static <K, V> Map<K, V> collectionToMap(final Collection<V> collection, final String pathToKey) {
		return collectionToMap(collection, pathToKey, new HashMap<K, V>());
	}

	/**
	 * @param collection
	 *            - source collection
	 * @param pathToKey
	 *            - literal path to field, that would be used as map key in
	 *            object from source collection
	 * @param targetMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> collectionToMap(final Collection<V> collection, final String pathToKey,
			final Map<K, V> targetMap) {
		if (collection.isEmpty()) {
			return Collections.emptyMap();
		}
		try {
			for (final V value : collection) {
				final Object key = value.getClass().getMethod(GET_PREFIX + capitalize(pathToKey)).invoke(value);
				targetMap.put((K) key, value);
			}
		} catch (final Exception e) {
			throw new RuntimeException("Failed to transform collection to map", e);
		}
		return targetMap;
	}

}
