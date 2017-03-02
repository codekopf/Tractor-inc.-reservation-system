/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

/**
 * @author Marek Lobotka
 */
public interface IEqualityComparator<TFirst, TSecond> {

	boolean equalsTo(TFirst firstItem, TSecond secondItem);
}
