package emn.fil.collection.mutable.interfaces;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.observer.Observer;
import emn.fil.collection.obs.observer.ObserverAttribute;
import emn.fil.collection.obs.subject.ISubject;
import emn.fil.collection.obs.type.OAbstract;

public interface ICollection<T extends OAbstract> extends ISubject<T>, Observer<T>, ObserverAttribute<OAbstract> {
	/**
	 * Union with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public ICollection<T> union(final ICollection<T> b);

	/**
	 * Intersection with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public ICollection<T> intersection(final ICollection<T> b);

	/**
	 * Difference with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public ICollection<T> difference(final ICollection<T> b);

	/**
	 * Add the element to the collection.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void add(final T element);
	
	/**
	 * Add the element in the list following the type of the collection itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void add(EventCollectionMessage<T> event);
	
	/**
	 * Remove the element in the list following the type of the collection
	 * itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void remove(EventCollectionMessage<T> event);

	/**
	 * Remove the element to the collection.
	 * 
	 * @param element
	 *            the element to remove
	 */
	public void remove(final T element);

	/** Get the content of the collection. */
	public List<T> getContent();

	/**
	 * Apply the function on all the collection
	 * 
	 * @param func
	 *            to apply on elements
	 * @return new collection with member applied by the func
	 */
	public ICollection<T> apply(final Function<T, T> func);

	/**
	 * Selection element of the collection that match the function
	 * 
	 * @param func
	 * @return
	 */
	public ICollection<T> selection(final Predicate<T> func);

	public ICollection<T> sort();

	public ICollection<T> sort(final Comparator<T> functionSort);
	
	/**
	 * Check if this collection contains the same elements as B collection
	 * 
	 * @param b
	 * @return
	 */
	public boolean exists(final ICollection<T> b);

	/**
	 * Return a collection which match the uniqueness predicate
	 * 
	 * @return
	 */
	public ICollection<T> toUnique();

	/**
	 * Return a collection of A without element of B
	 * 
	 * @param b
	 * @return
	 */
	public ICollection<T> reject(final ICollection<T> b);

	/**
	 * Check if the collection is empty
	 * 
	 * @return true or false
	 */
	public boolean isEmpty();

	/**
	 * Return the number of element T contain in this collection
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * Function used inside the collection classes They are implemented by
	 * subclasses to match the requirement of the collection type
	 */
	public boolean add(List<T> newList, T element);
	
	public ICollection<T> createCollectionType(List<T> newList, ICollection<T> b);

	public ICollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func);

	public ICollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func);

	public ICollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort);
}
