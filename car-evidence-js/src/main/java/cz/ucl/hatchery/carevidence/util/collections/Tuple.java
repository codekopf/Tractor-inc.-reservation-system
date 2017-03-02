package cz.ucl.hatchery.carevidence.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A tuple is an ordered list of elements. In set theory, an n-tuple is a
 * sequence (or ordered list) of n elements, where n is a non-negative integer.
 * Each tuple item can be set with or without its name definition.
 * 
 * @author Marek Lobotka
 */
public class Tuple {

	private List<TupleItem> list;

	/**
	 * Constructor used for unnamed tuple instantiation.
	 * 
	 * @param tupleItems
	 *            Set of unnamed tuple items
	 */
	public Tuple(final Object... tupleItems) {
		list = new ArrayList<TupleItem>();
		for (final Object item : tupleItems) {
			list.add(TupleItem.valueOf(null, item));
		}
	}

	/**
	 * Constructor used for named tuple instantiation.
	 * 
	 * @param tupleItems
	 *            Set of named tuple items
	 */
	public Tuple(final TupleItem... tupleItems) {
		list = new ArrayList<TupleItem>(Arrays.asList(tupleItems));
	}

	/**
	 * Constructor used for named tuple instantiation.
	 * 
	 * @param name
	 *            tuple name
	 * @param item
	 *            defined by given name
	 */
	public Tuple(final String name, final Object item) {
		this(TupleItem.valueOf(name, item));
	}

	/**
	 * Get item of Tuple defined by given index
	 * 
	 * @param index
	 *            Index of tuple item
	 * @return Item defined by given index
	 */
	public Object getItem(final int index) {
		return list.get(index).getItem();
	}

	/**
	 * Get item of Tuple defined by its name
	 * 
	 * @param name
	 *            Name of tuple item
	 * @return Item defined by given name
	 */
	public Object getItem(final String name) {
		return Collections.first(list, new Predicate<TupleItem>() {

			@Override
			public boolean isTrue(final TupleItem item) {
				return item.getName().equals(name);
			}
		}).getItem();
	}

	/**
	 * Merges given Tuple(s) into one
	 *
	 * @param Tuple
	 *            Tuple list
	 * @return not null merged Tuple instance
	 */
	public static Tuple merge(final Tuple... tuples) {
		final List<TupleItem> objectSet = new ArrayList<TupleItem>();
		for (final Tuple item : tuples) {
			objectSet.addAll(item.list);
		}
		return new Tuple(objectSet.toArray(new TupleItem[objectSet.size()]));
	}

	@Override
	public String toString() {
		return "Tuple [list=" + list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		final Tuple other = (Tuple) obj;
		if (list == null) {
			if (other.list != null) {
				return false;
			}
		} else if (!list.equals(other.list)) {
			return false;
		}
		return true;
	}
}
