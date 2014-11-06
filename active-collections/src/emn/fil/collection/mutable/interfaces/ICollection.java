package emn.fil.collection.mutable.interfaces;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;

public interface ICollection<T> {
	/**
	 * Union with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> union(AbstractCollection<T> b);

	/**
	 * Intersection with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> intersection(AbstractCollection<T> b);

	/**
	 * Difference with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> difference(AbstractCollection<T> b);

	/**
	 * Add the element to the collection.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void add(T element);

	/**
	 * Remove the element to the collection.
	 * 
	 * @param element
	 *            the element to remove
	 */
	public void remove(T element);

	/** Get the content of the collection. */
	public List<T> getContent();
}
