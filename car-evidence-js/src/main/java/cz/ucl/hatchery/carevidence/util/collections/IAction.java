package cz.ucl.hatchery.carevidence.util.collections;

/**
 * @author Filip Buchta
 */
public interface IAction<T> {
	void call(T object);
}
