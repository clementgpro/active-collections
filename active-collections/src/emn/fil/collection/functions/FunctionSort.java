package emn.fil.collection.functions;

import java.util.Comparator;

public interface FunctionSort<T> extends Comparator<T> {
	/**
	 * Calculate element1 < element2
	 * 
	 * @param element1
	 * @param element2
	 * @return < 0 if element 1 < element2 0 if element1 = element2 > 0 if
	 *         element 1 > element2
	 */
	int compare(final T element1, final T element2);
}