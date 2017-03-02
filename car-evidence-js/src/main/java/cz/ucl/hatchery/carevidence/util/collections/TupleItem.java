/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

/**
 * Tuple element definition which enables to assign its name.
 * 
 * @author Marek Lobotka
 *
 */
public class TupleItem {

	private String name;
	private Object item;

	public static TupleItem valueOf(final String name, final Object item) {
		return new TupleItem(name, item);
	}

	/**
	 * @param name
	 * @param item
	 */
	private TupleItem(final String name, final Object item) {
		this.name = name;
		this.item = item;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the item
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(final Object item) {
		this.item = item;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		final TupleItem other = (TupleItem) obj;
		if (item == null) {
			if (other.item != null) {
				return false;
			}
		} else if (!item.equals(other.item)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
