/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

/**
 * Selects single property of class
 *
 * @author Marek Lobotka
 */
public interface Selector<T, U> {
	/**
	 * Returns desired value of type U from object of type T
	 * 
	 * @param item
	 *            template object on which is selector applied
	 * @return selected value
	 */
	U getValue(T item);
}
