/**
 *
 */
package cz.ucl.hatchery.carevidence.util.collections;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.util.Assert;

/**
 * Helper class that provides support methods for collections
 *
 * @author Marek Lobotka
 * @author Filip Buchta
 */
public final class Collections {

	public static final String DEFAULT_SEPARATOR = ", ";

	private Collections() {
	}

	private static Selector<Object, Object> identitySelector = null;

	/**
	 * @return
	 */
	public static Selector<Object, Object> getIdentitySelector() {
		if (identitySelector == null) {
			identitySelector = new Selector<Object, Object>() {

				@Override
				public Object getValue(final Object item) {
					return item;
				}
			};
		}
		return identitySelector;
	}

	public static <T> Selector<T, T> getIdentitySelector(final Class<T> clazz) {
		return new Selector<T, T>() {

			@Override
			public T getValue(final T item) {
				return item;
			}
		};
	}

	/**
	 * Returns first element of sequence
	 *
	 * @param source
	 *            sequence to iterate through
	 * @return element
	 * @throws NoSuchElementException
	 *             if sequence does not contain at least one element
	 */
	public static <TSource> TSource first(final Iterable<TSource> source) {
		final TSource result = firstOrDefault(source);
		if (result == null) {
			throw new NoSuchElementException();
		} else {
			return result;
		}
	}

	/**
	 * Returns the first element of the sequence that satisfies a condition.
	 *
	 * @param source
	 *            sequence to iterate through
	 * @param predicate
	 *            function to test each element for a condition
	 * @return element that satisfies condition
	 * @throws NoSuchElementException
	 *             if sequence does not contain at least one element fulfilling
	 *             predicate
	 */
	public static <TSource> TSource first(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		final TSource result = firstOrDefault(source, predicate);
		if (result == null) {
			throw new NoSuchElementException();
		} else {
			return result;
		}
	}

	/**
	 * Returns the first element of the sequence that satisfies a condition.
	 *
	 * @param source
	 *            sequence to iterate through
	 * @param predicate
	 *            function to test each element for a condition
	 * @return element that satisfies condition or null if no such element is
	 *         found
	 */
	public static <TSource> TSource firstOrDefault(final Iterable<? extends TSource> source,
			final Predicate<TSource> predicate) {
		if (source != null) {
			for (final TSource item : source) {
				if (predicate.isTrue(item)) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the last element of the sequence that satisfies a condition.
	 *
	 * @param source
	 *            sequence to iterate through
	 * @param predicate
	 *            function to test each element for a condition
	 * @return element that satisfies condition or null if no such element is
	 *         found
	 */
	public static <TSource> TSource lastOrDefault(final Iterable<? extends TSource> source,
			final Predicate<TSource> predicate) {
		TSource returnItem = null;
		if (source != null) {
			for (final TSource item : source) {
				if (predicate.isTrue(item)) {
					returnItem = item;
				}
			}
		}
		return returnItem;
	}

	/**
	 * Returns the last element of the sequence
	 *
	 * @param source
	 *            sequence to iterate through
	 * @return last element element or null if no such element is found
	 */
	public static <TSource> TSource lastOrDefault(final Iterable<? extends TSource> source) {
		TSource returnItem = null;
		if (source != null) {
			for (final TSource item : source) {
				returnItem = item;
			}
		}
		return returnItem;
	}

	/**
	 * Returns first element of sequence or null if sequence is empty
	 *
	 * @param source
	 *            sequence to iterate through
	 * @return element or null
	 */
	public static <TSource> TSource firstOrDefault(final Iterable<TSource> source) {
		if (source != null) {
			for (final TSource item : source) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Groups sequence by specified property (in the same order as given source
	 * sequence - that is the reason of using java.util.LinkedHashMap, not
	 * general java.util.Map interface)
	 *
	 * @param source
	 *            sequence to iterate through
	 * @param selector
	 *            property that is used as group key
	 * @return grouped sequence
	 */
	public static <T, U> LinkedHashMap<U, List<T>> groupBy(final Iterable<T> source, final Selector<T, U> selector) {
		if (source == null) {
			throw new IllegalArgumentException("source");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}

		final LinkedHashMap<U, List<T>> grouped = new LinkedHashMap<U, List<T>>();
		for (final T value : source) {
			final U key = selector.getValue(value);
			if (!grouped.containsKey(key)) {
				grouped.put(key, new ArrayList<T>());
			}
			final ArrayList<T> list = (ArrayList<T>) grouped.get(key);
			list.add(value);
		}
		return grouped;
	}

	/**
	 * Filters a sequence of values based on a predicate.
	 *
	 * @param source
	 *            collection to filter
	 * @param predicate
	 *            that is used as filter on each element of collection
	 * @return filtered collection by a predicate
	 */
	@SuppressWarnings("unchecked")
	public static <TSource> List<TSource> where(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		return where(source, predicate, (Selector<TSource, TSource>) getIdentitySelector());
	}

	/**
	 * Filters a sequence of values based on a predicate + converts to specified
	 * object.
	 *
	 * @param source
	 *            collection to filter
	 * @param predicate
	 *            that is used as filter on each element of collection
	 * @return filtered collection by a predicate
	 */
	public static <TResult, TSource> List<TResult> where(final Iterable<TSource> source,
			final Predicate<TSource> predicate, final Selector<TSource, TResult> selector) {
		final List<TResult> result = new LinkedList<TResult>();
		if (source != null) {
			for (final TSource item : source) {
				if (predicate.isTrue(item)) {
					result.add(selector.getValue(item));
				}
			}
		}
		return result;
	}

	/**
	 * Filters map based on a predicate for given map key.
	 *
	 * @param source
	 *            collection to filter
	 * @param predicate
	 *            that is used as filter on each element of collection (based on
	 *            key value)
	 * @return filtered map by a predicate
	 */
	public static <TSource, TMapValueType> Map<TSource, TMapValueType> where(final Map<TSource, TMapValueType> source,
			final Predicate<TSource> predicate) {

		final Map<TSource, TMapValueType> result = new HashMap<TSource, TMapValueType>();
		if (source != null) {
			for (final Entry<TSource, TMapValueType> entry : source.entrySet()) {
				if (predicate.isTrue(entry.getKey())) {
					result.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Determines whether any element of a sequence satisfies a condition.
	 *
	 * @param source
	 *            collection to apply predicate to
	 * @param predicate
	 *            function to test each element for a condition
	 * @return true if any element in source collection satisfies condition
	 *         given by predicate, false otherwise
	 */
	public static <TSource> boolean any(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		if (source != null) {
			for (final TSource item : source) {
				if (predicate.isTrue(item)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Determines whether a sequence contains a specified element by using their
	 * equals function
	 *
	 * @param source
	 *            collection to check for element
	 * @param value
	 *            to check for element
	 * @return true if element is in the collection, else otherwise
	 */
	public static <TSource> boolean contains(final Iterable<TSource> source, final TSource value) {
		if (source != null) {
			for (final TSource item : source) {
				if (item.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Determines whether a sequence contains a specified element by using given
	 * comparator
	 *
	 * @param source
	 *            collection to check for element
	 * @param value
	 *            to check for element
	 * @param comparator
	 *            {@link IEqualityComparator}
	 * @return true if element is in the collection, else otherwise
	 */
	public static <TSource, TSource2> boolean contains(final Iterable<TSource> source, final TSource2 value,
			final IEqualityComparator<TSource, TSource2> comparator) {
		if (source != null) {
			return Collections.any(source, new Predicate<TSource>() {

				@Override
				public boolean isTrue(final TSource item) {
					return comparator.equalsTo(item, value);
				}
			});
		}
		return false;
	}

	/**
	 * Check if collections are same
	 *
	 * @param first
	 * @param second
	 * @param comparator
	 * @return
	 */
	public static <TFirst, TSecond> boolean compare(final Collection<TFirst> first, final Collection<TSecond> second,
			final IEqualityComparator<TFirst, TSecond> comparator) {

		return first.size() == second.size() && Collections.all(second, new Predicate<TSecond>() {

			@Override
			public boolean isTrue(final TSecond item) {
				return Collections.contains(first, item, comparator);
			}

			;
		});
	}

	/**
	 * Check if arrays are same
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean compare(final Object[] first, final Object[] second) {
		return Arrays.asList(first).equals(Arrays.asList(second));
	}

	/**
	 * Creates an array list from array of elements
	 *
	 * @param source
	 * @return
	 */
	public static <TSource> List<TSource> toList(final TSource... source) {
		final List<TSource> result = new ArrayList<TSource>();
		for (final TSource item : source) {
			result.add(item);
		}
		return result;
	}

	/**
	 * Creates a hash set from array of elements
	 *
	 * @param source
	 * @return
	 */
	public static <TSource> Set<TSource> toSet(final TSource... elements) {
		final HashSet<TSource> set = new HashSet<TSource>();
		java.util.Collections.addAll(set, elements);
		return set;
	}

	/**
	 * Filters elements of sequence based on specific type
	 *
	 * @param source
	 * @param clazz
	 *            to use as filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <TSource, TTarget> Iterable<TTarget> ofType(final Iterable<TSource> source,
			final Class<TTarget> clazz) {
		return (Iterable<TTarget>) where(source, new Predicate<TSource>() {

			@Override
			public boolean isTrue(final TSource item) {
				return clazz.isInstance(item);
			}

		});

	}

	/**
	 * Projects each element of collection into a new form.
	 *
	 * @param source
	 *            sequence
	 * @param selector
	 *            transformation to apply to each element
	 * @return
	 */
	public static <TSource, TResult> List<TResult> select(final Iterable<? extends TSource> source,
			final Selector<TSource, TResult> selector) {
		if (source == null) {
			return java.util.Collections.emptyList();
		}

		final List<TResult> result = new ArrayList<TResult>();
		for (final TSource value : source) {
			result.add(selector.getValue(value));
		}
		return result;
	}

	/**
	 * Projects each element of collection into a new form and return distinct
	 * list of results as object equals defined.
	 *
	 * @param source
	 *            sequence
	 * @param selector
	 *            transformation to apply to each element
	 * @return
	 */
	public static <TSource, TResult> List<TResult> selectDistinct(final Iterable<? extends TSource> source,
			final Selector<TSource, TResult> selector) {
		if (source == null) {
			return java.util.Collections.emptyList();
		}

		final Set<TResult> result = new HashSet<TResult>();
		for (final TSource value : source) {
			result.add(selector.getValue(value));
		}
		return new ArrayList<TResult>(result);
	}

	/**
	 * Projects each element of collection into a new form of sequence and
	 * flattens resulting sequence into one sequence.
	 *
	 * @param source
	 *            sequence
	 * @param selector
	 *            transformation to apply to each element
	 * @return
	 */
	public static <TSource, TResult> List<TResult> selectMany(final Iterable<TSource> source,
			final Selector<TSource, Iterable<TResult>> selector) {
		final List<TResult> result = new ArrayList<TResult>();
		for (final TSource s : source) {
			for (final TResult r : selector.getValue(s)) {
				result.add(r);
			}
		}
		return result;
	}

	/**
	 * Projects each element of collection into a new form of sequence and
	 * flattens resulting sequence into one sequence.
	 *
	 * @param source
	 *            sequence
	 * @param selector
	 *            transformation to apply to each element
	 * @return
	 */
	public static <TSource, TResult> List<TResult> selectMany(final Iterable<TSource> source,
			final Selector<TSource, Iterable<TResult>> selector, final Predicate<TSource> predicate) {
		final List<TResult> result = new ArrayList<TResult>();
		for (final TSource s : source) {
			for (final TResult r : selector.getValue(s)) {
				if (predicate.isTrue(s)) {
					result.add(r);
				}
			}
		}
		return result;
	}

	/**
	 * Projects each element of collection into a new form of sequence and
	 * flattens resulting sequence into one sequence.
	 *
	 * @param source
	 *            sequence
	 * @param selector
	 *            transformation to apply to each element
	 * @param predicate
	 *            predicate to choose item from source sequence
	 * @return
	 */
	public static <TSource, TResult> List<TResult> selectManyWithPredicate(final Iterable<TSource> source,
			final Selector<TSource, TResult> selector, final Predicate<TSource> predicate) {
		final List<TResult> result = new ArrayList<TResult>();
		for (final TSource s : source) {
			final TResult r = selector.getValue(s);
			if (predicate.isTrue(s)) {
				result.add(r);
			}

		}
		return result;
	}

	public static <TSource> int count(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		int result = 0;
		if (source != null) {
			for (final TSource item : source) {
				if (predicate == null || predicate.isTrue(item)) {
					++result;
				}
			}
		}
		return result;
	}

	public static <TSource> int count(final Iterable<TSource> source) {
		return count(source, null);
	}

	public static <TSource extends Comparable<TSource>> TSource max(final List<TSource> source) {
		if (source == null) {
			throw new IllegalArgumentException("source");
		}

		TSource value = null;
		boolean hasValue = false;
		for (final TSource item : source) {
			if (hasValue) {
				if (item.compareTo(value) > 0) {
					value = item;
				}
			} else {
				value = item;
				hasValue = true;
			}

		}
		if (hasValue) {
			return value;
		} else {
			return null;
		}
	}

	public static <TSource, TResult extends Comparable<TResult>> TResult max(final List<TSource> source,
			final Selector<TSource, TResult> selector) {

		if (source == null) {
			throw new IllegalArgumentException("source");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}

		final List<TResult> result = select(source, selector);
		return max(result);

	}

	public static <TSource, TResult extends Comparable<TResult>> TResult max(final List<TSource> source,
			final Selector<TSource, TResult> selector, final Predicate<TSource> predicate) {
		if (source == null) {
			throw new IllegalArgumentException("source");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}
		if (predicate == null) {
			throw new IllegalArgumentException("predicate");
		}

		final List<TSource> result = where(source, predicate);
		return max(result, selector);
	}

	public static <TSource extends Comparable<TSource>> TSource min(final List<TSource> source) {
		if (source == null) {
			throw new IllegalArgumentException("source");
		}

		TSource value = null;
		boolean hasValue = false;
		for (final TSource item : source) {
			if (hasValue) {
				if (item.compareTo(value) < 0) {
					value = item;
				}
			} else {
				value = item;
				hasValue = true;
			}

		}
		if (hasValue) {
			return value;
		} else {
			return null;
		}
	}

	public static <TSource, TResult extends Comparable<TResult>> TResult min(final List<TSource> source,
			final Selector<TSource, TResult> selector) {

		if (source == null) {
			throw new IllegalArgumentException("source");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}

		final List<TResult> result = select(source, selector);
		return min(result);

	}

	public static <TSource, TResult extends Comparable<TResult>> TResult min(final List<TSource> source,
			final Selector<TSource, TResult> selector, final Predicate<TSource> predicate) {
		if (source == null) {
			throw new IllegalArgumentException("source");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}
		if (predicate == null) {
			throw new IllegalArgumentException("predicate");
		}

		final List<TSource> result = where(source, predicate);
		return min(result, selector);
	}

	public static <TInput> BigDecimal sum(final Iterable<TInput> source, final Selector<TInput, BigDecimal> selector) {
		BigDecimal sum = BigDecimal.ZERO;
		for (final TInput item : source) {
			sum = sum.add(selector.getValue(item));
		}
		return sum;
	}

	public static <TFirst, TSecond> Iterable<TFirst> except(final Iterable<TFirst> first,
			final Iterable<TSecond> second, final IEqualityComparator<TFirst, TSecond> comparator) {
		final List<TFirst> results = new ArrayList<TFirst>();

		for (final TFirst firstItem : first) {
			if (!Collections.any(second, new Predicate<TSecond>() {

				@Override
				public boolean isTrue(final TSecond secondItem) {
					return comparator.equalsTo(firstItem, secondItem);
				}
			})) {
				results.add(firstItem);
			}
		}

		return results;
	}

	public static <TSource> List<TSource> singletonList(final TSource item) {
		final List<TSource> result = new ArrayList<TSource>();
		result.add(item);
		return result;
	}

	public static <TKey, TValue> Map<TKey, TValue> singletonMap(final TKey key, final TValue value) {
		final Map<TKey, TValue> result = new HashMap<TKey, TValue>();
		result.put(key, value);
		return result;
	}

	public static <TKey, TValue> Map<TKey, TValue> arraysAsMap(final TKey[] keys, final TValue[] values) {
		Assert.isNull(keys);
		Assert.isNull(values);

		if (keys.length != values.length) {
			throw new IllegalArgumentException(
					"There must be same length of given arrays to construct Map. 'keys.length'= " + keys.length
							+ ", 'values.length'=" + values.length);
		}
		final Map<TKey, TValue> result = new HashMap<TKey, TValue>();
		for (int i = 0; i < keys.length; i++) {
			result.put(keys[i], values[i]);
		}
		return result;
	}

	public static <TSource> boolean all(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		if (source != null) {
			for (final TSource item : source) {
				if (!predicate.isTrue(item)) {
					return false;
				}
			}
		}
		return true;
	}

	public static <T1, T2> List<Pair<T1, T2>> cartesian(final Iterable<T1> source1, final Iterable<T2> source2) {
		return Collections.selectMany(source1, new Selector<T1, Iterable<Pair<T1, T2>>>() {

			@Override
			public Iterable<Pair<T1, T2>> getValue(final T1 value1) {
				return Collections.select(source2, new Selector<T2, Pair<T1, T2>>() {

					@Override
					public Pair<T1, T2> getValue(final T2 value2) {
						return new Pair<T1, T2>(value1, value2);
					}
				});
			}
		});
	}

	public static <T1, T2, T3> List<Triplet<T1, T2, T3>> cartesian(final Iterable<T1> source1,
			final Iterable<T2> source2, final Iterable<T3> source3) {

		return Collections.selectMany(source1, new Selector<T1, Iterable<Triplet<T1, T2, T3>>>() {

			@Override
			public Iterable<Triplet<T1, T2, T3>> getValue(final T1 value1) {

				return Collections.selectMany(source2, new Selector<T2, Iterable<Triplet<T1, T2, T3>>>() {

					@Override
					public Iterable<Triplet<T1, T2, T3>> getValue(final T2 value2) {
						return Collections.select(source3, new Selector<T3, Triplet<T1, T2, T3>>() {

							@Override
							public Triplet<T1, T2, T3> getValue(final T3 value3) {
								return new Triplet<T1, T2, T3>(value1, value2, value3);
							}
						});
					}
				});
			}
		});
	}

	/**
	 * merges passed lists into one without duplicates removed
	 *
	 * @param lists
	 *            not null lists of lists. Every List element is expected to
	 *            have elements of coherent types
	 * @param <T>
	 *            type of object in list of lists
	 * @return not null merged list
	 */
	public static <T> List<T> mergeLists(final List<T>... lists) {
		return mergeLists(Arrays.asList(lists));
	}

	/**
	 * merges passed lists into one without duplicates removed
	 *
	 * @param listsOfListsToMerge
	 *            not null lists of lists. Every List element is expected to
	 *            have elements of coherent types
	 * @param <T>
	 *            type of object in list of lists
	 * @return not null merged list
	 */
	public static <T> List<T> mergeLists(final List<List<T>> listsOfListsToMerge) {
		final List<T> mergingList = new ArrayList<T>();
		for (final List<T> eachList : listsOfListsToMerge) {
			mergingList.addAll(eachList);
		}
		final List<T> result = new ArrayList<T>(mergingList);
		return result;
	}

	/**
	 * merges passed arrays into one without duplicates removed
	 *
	 * @param arrays
	 *            not null arrays of T. Every Array element is expected to have
	 *            elements of coherent types
	 * @param <T>
	 *            type of object in arrays
	 * @return not null merged array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] mergeArrays(final T[] array1, final T... array2) {
		Assert.notNull(array1);
		Assert.notNull(array2);

		final Class<?> type1 = array1.getClass().getComponentType();
		final T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	/**
	 * merges passed lists into one with duplicates removed
	 *
	 * @param lists
	 *            not null lists of lists. Every List element is expected to
	 *            have elements of coherent types
	 * @param <T>
	 *            type of object in list of lists
	 * @return not null merged list
	 */
	public static <T> List<T> mergeListsWithoutDuplicates(final List<T>... lists) {
		final Set<T> mergingSet = new HashSet<T>();
		for (final List<T> eachList : lists) {
			mergingSet.addAll(eachList);
		}
		final List<T> result = new ArrayList<T>(mergingSet);
		return result;
	}

	/**
	 * merges passed arrays into one with duplicates removed
	 *
	 * @param arrays
	 *            not null arrays of T. Every Array element is expected to have
	 *            elements of coherent types
	 * @param <T>
	 *            type of object in arrays
	 * @return not null merged array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] mergeArraysWithoutDuplicates(final T[] array1, final T... array2) {
		final Set<T> mergingSet = new HashSet<T>();
		for (final T array : array1) {
			mergingSet.add(array);
		}
		for (final T array : array2) {
			mergingSet.add(array);
		}

		final Class<?> type = array1.getClass().getComponentType();
		return (new ArrayList<T>(mergingSet)).toArray((T[]) Array.newInstance(type, mergingSet.size()));
	}

	/**
	 * Joins collection using selector and custom separator
	 *
	 * @param collection
	 *            {@link Collection} to join
	 * @param selector
	 *            transformation to apply to each element
	 * @param separator
	 *            custom separator
	 * @return joint string
	 */
	public static <T, U> String toString(final Collection<T> collection, final Selector<T, U> selector,
			final String separator) {
		if (collection == null) {
			throw new IllegalArgumentException("collection");
		}
		if (selector == null) {
			throw new IllegalArgumentException("selector");
		}

		final StringBuffer result = new StringBuffer();
		// avoids index access and does not print last comma
		int index = 0;
		for (final T each : collection) {
			final U value = selector.getValue(each);
			result.append(value);
			index++;
			if (index != collection.size()) {
				result.append(separator);
			}
		}

		return result.toString();
	}

	/**
	 * Joins collection using selector and custom separator
	 *
	 * @param collection
	 *            {@link Collection} to join
	 * @param selector
	 *            transformation to apply to each element
	 * @param separator
	 *            custom separator
	 * @return joint string
	 */
	public static <T, U> String toString(final Collection<T> collection, final Selector<T, U> selector) {
		return toString(collection, selector, DEFAULT_SEPARATOR);
	}

	/**
	 * Joins collection using custom separator
	 *
	 * @param collection
	 *            {@link Collection} to join
	 * @param separator
	 *            custom separator
	 * @return joint string
	 */
	public static String toString(final Collection<?> collection, final String separator) {
		if (collection == null) {
			throw new IllegalArgumentException("collection");
		}

		final StringBuffer result = new StringBuffer();
		// avoids index access and does not print last comma
		int index = 0;
		for (final Object each : collection) {
			result.append(each);
			index++;
			if (index != collection.size()) {
				result.append(separator);
			}
		}

		return result.toString();
	}

	/**
	 * Joins collection fields using DEFAULT_SEPARATOR as separator
	 *
	 * @param collection
	 *            {@link Collection} to join
	 * @return joint string
	 */
	public static String toString(final Collection<?> collection) {
		return toString(collection, DEFAULT_SEPARATOR);
	}

	/**
	 * @param contents
	 *            not null, contents of the call to Map.toString()
	 * @return not null, Map<String, String> filled with values from the
	 *         contents
	 * @throws IllegalArgumentException
	 *             if contents is bad formed string
	 */
	public static Map<String, String> mapFromString(final String contents) {
		final HashMap<String, String> result = new HashMap<String, String>();
		try {
			final String withoutBorderBrackets = contents.substring(1, contents.length() - 1);
			for (final String eachEntry : withoutBorderBrackets.split(DEFAULT_SEPARATOR)) {
				final String key = eachEntry.split("=")[0];
				final String value = eachEntry.split("=")[1];
				result.put(key, value);
			}
		} catch (final Exception e) {
			throw new IllegalArgumentException("could not parse contents as map, contents=" + contents, e);
		}
		return result;
	}

	/**
	 * Get generic type of given collection
	 *
	 * @param method
	 * @return
	 */
	public static Class<?> getCollectionGenericReturnType(final Method method) {
		if (!Collection.class.isAssignableFrom(method.getReturnType())) {
			throw new IllegalArgumentException("Given method return type is not collection");
		}
		final Type returnType = method.getGenericReturnType();
		if (ParameterizedType.class.isAssignableFrom(returnType.getClass())) {
			return (Class<?>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
		}
		// Not parametrized collection
		return null;
	}

	/**
	 * Remove elements from given iterable matching given predicate
	 *
	 * @param source
	 * @param predicate
	 * @param <TSource>
	 */
	public static <TSource> void remove(final Iterable<TSource> source, final Predicate<TSource> predicate) {
		if (source == null) {
			return;
		}

		for (final Iterator<TSource> iterator = source.iterator(); iterator.hasNext();) {
			final TSource next = iterator.next();
			if (predicate.isTrue(next)) {
				iterator.remove();
			}
		}
	}

	/**
	 * @param source
	 *            source list which has to be split to parts with given maxSize
	 * @param maxSize
	 *            - max size of the result list parts
	 * @param <T>
	 *            - type of object in the list
	 * @return list of lists containing parts of the source list
	 */
	public static <T> List<List<T>> splitList(final List<T> source, final int maxSize) {
		final List<List<T>> sourceParts = new ArrayList<List<T>>();
		if (source != null && !source.isEmpty()) {
			int numberOfParts = source.size() / maxSize;
			numberOfParts = numberOfParts + (source.size() % maxSize > 0 ? 1 : 0);

			for (int i = 0; i < numberOfParts; i++) {
				final int from = i * maxSize;
				int to = (i + 1) * maxSize;
				to = to < source.size() ? to : source.size();
				sourceParts.add(source.subList(from, to));
			}

		}
		return sourceParts;
	}

	/**
	 * Checks whether one collection is composed only of null values.
	 * 
	 * @param list
	 *            <tt>List</tt> of <tt>Objects</tt>
	 * @return <tt>True</tt> if collection contains only null values
	 *         <tt>False</tt> otherwise
	 */
	public static boolean allIsNull(final List<Object> list) {
		return all(list, new Predicate<Object>() {

			@Override
			public boolean isTrue(final Object item) {
				return item == null;
			}
		});
	}

	/**
	 * Checks whether one collection is composed only of null values.
	 * 
	 * @param list
	 *            <tt>List</tt> of <tt>Objects</tt>
	 * @return <tt>True</tt> if collection contains only null values
	 *         <tt>False</tt> otherwise
	 */
	public static boolean allIsNull(final Object[] array) {
		if (array != null) {
			for (final Object item : array) {
				if (item != null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks that collection does not contain any null values
	 * 
	 * @param list
	 *            <tt>List</tt> of <tt>Objects</tt>
	 * @return <tt>True</tt> if collection does not contain any null values
	 *         <tt>False</tt> otherwise
	 */
	public static boolean allIsNotNull(final List<Object> list) {
		return all(list, new Predicate<Object>() {

			@Override
			public boolean isTrue(final Object item) {
				return item != null;
			}
		});
	}

	/**
	 * Checks that collection does not contain any null values
	 * 
	 * @param list
	 *            <tt>List</tt> of <tt>Objects</tt>
	 * @return <tt>True</tt> if collection does not contain any null values
	 *         <tt>False</tt> otherwise
	 */
	public static boolean allIsNotNull(final Object[] array) {
		if (array != null) {
			for (final Object item : array) {
				if (item == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks there is the intersection of an arbitrary nonempty collection of
	 * sets
	 * 
	 * @param s1
	 *            Set number 1
	 * @param s2
	 *            Set number 2
	 * @return <tt>True</tt> if there is the intersection of given sets
	 *         <tt>False</tt> otherwise
	 */
	public static <T> boolean hasIntersection(final Set<T> s1, final Set<T> s2) {
		final Set<T> intersection = new HashSet<T>(s1);
		intersection.retainAll(s2);
		return !intersection.isEmpty();
	}
}