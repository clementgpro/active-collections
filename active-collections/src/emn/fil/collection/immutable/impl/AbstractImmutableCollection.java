package emn.fil.collection.immutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.interfaces.IImmutableCollection;
import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.observer.Observer;
import emn.fil.collection.obs.type.OAbstract;

/**
 * Immutable representation of collection, which is C.
 * 
 * @author Clement, Benjamin
 *
 * @param <T>
 */
public abstract class AbstractImmutableCollection<T> implements Observer<T>, IImmutableCollection<T> {
	/** The content of the collection. */
	private List<T> content;

	/** The Function used to create this collection */
	private Function<T, T> functionApply;
	private Predicate<T> functionSelec;
	protected Comparator<T> functionSort;

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 */
	public AbstractImmutableCollection(final List<T> content) {
		super();
		this.content = content;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionApply
	 *            function used to create this collection
	 */
	public AbstractImmutableCollection(final List<T> content, final Function<T, T> functionApply) {
		super();
		this.content = content;
		this.functionApply = functionApply;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionSelec
	 *            function used to create this collection
	 */
	public AbstractImmutableCollection(final List<T> content, final Predicate<T> functionSelec) {
		super();
		this.content = content;
		this.functionSelec = functionSelec;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionSort
	 *            function used to create this collection
	 */
	public AbstractImmutableCollection(final List<T> content, final Comparator<T> functionSort) {
		super();
		this.content = content;
		this.functionSort = functionSort;
	}

	public boolean isEmpty() {
		return this.content.isEmpty();
	}

	public int size() {
		return this.content.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final EventCollectionMessage<T> event) {
		switch (event.getEventCollection()) {
		case ADD:

			if (functionSelec != null && !functionSelec.test(event.getElement()))
			{
				// Add element only if it's matching predicate
				break;
			}
			else if (functionApply != null)
			{
				// Modify element to match the function before adding
				event.setElement(functionApply.apply(event.getElement()));
			}
			else
			{
				this.add(event);
			}
			break;
		case REMOVE:
			this.remove(event);
			break;
		default:
			break;
		}
	}

	// TODO !!!!!!!!!!
	// At the moment we use an hack by using the cast to T.
	// We need to fix this because we never know for sure that this is T.
	// The origin of the problem is because "before" in OAbstract has OAbstract
	// type.
	// The solution will consists in give T type for before and after.
	@Override
	public void updateAttributeChanged(final EventCollectionAttribute<? extends OAbstract> event) {

		// Check if the element before modification was in this collection
		if (this.content.contains(event.getElementBefore()))
		{
			// Then if the element still check the predicate, we update it
			if (functionSelec.test((T) event.getElementAfter()))
			{
				this.content.set(this.content.indexOf(event.getElementBefore()), (T) event.getElementAfter());
			}
			// else we delete it
			else
			{
				this.getContent().remove(this.content.indexOf(event.getElementBefore()));
			}
		}
		// If it is a new element we add it
		else
		{
			this.content.add((T) event.getElementAfter());
		}
	}

	/**
	 * Add the element in the list following the type of the collection itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	protected abstract void add(EventCollectionMessage<T> event);

	/**
	 * Remove the element in the list following the type of the collection
	 * itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	protected abstract void remove(EventCollectionMessage<T> event);

	/**
	 * Get the content of the collection.
	 * 
	 * @return the list.
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * Returns a textual representation of the content.
	 */
	public String toString() {
		return this.content.toString();
	}
}
