/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

public interface PredicateByType {

	/**
	 * @return class that should be checked by predicate
	 */
	Class<?> getType();

	/**
	 * @param obj
	 *            - instance of {@link #getType()}
	 */
	boolean isTrue(Object obj);

}
