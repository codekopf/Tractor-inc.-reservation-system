/**
 *
 */
package cz.ucl.hatchery.carevidence.util.collections;

/**
 * Represents a condition
 *
 * @author Marek Lobotka
 */
public interface Predicate<T> {

	/**
	 * Evaluates truth value of this predicate on specific object
	 *
	 * @param item
	 *            that this condition is applied on
	 * @return result of condition evaluation
	 */
	boolean isTrue(T item);
}
