/**
 * 
 */
package cz.ucl.hatchery.carevidence.util.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The <code>HistoryStack</code> class extends standard java.util.Stack with
 * item history function. There can be get all items ever pushed into the stack
 * until last item popped (empty stack)
 * 
 * @author Marek Lobotka
 * 
 */
public class HistoryStack<T> extends Stack<T> {

	private static final long serialVersionUID = 1872831054166464335L;

	private final List<T> items = new ArrayList<T>();

	/** {@inheritDoc} */
	@Override
	public T push(final T item) {
		items.add(item);
		return super.push(item);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized T pop() {
		// If there is last item to pop, clear history
		if (this.size() == 1) {
			items.clear();
		}
		return super.pop();
	}

	public List<T> getHistory() {
		return items;
	}

}
