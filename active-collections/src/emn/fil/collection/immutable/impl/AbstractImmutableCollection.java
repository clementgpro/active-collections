package emn.fil.collection.immutable.impl;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.interfaces.IImmutableCollection;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.observer.Observer;

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

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 */
	public AbstractImmutableCollection(List<T> content) {
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
	public AbstractImmutableCollection(List<T> content, Function<T, T> functionApply) {
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
	public AbstractImmutableCollection(List<T> content, Predicate<T> functionSelec) {
		super();
		this.content = content;
		this.functionSelec = functionSelec;
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

			this.add(event);
			break;
		case REMOVE:
			this.remove(event);
			break;
		default:
			break;
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
		StringBuilder sb = new StringBuilder("content : { ");
		for (T element : content)
		{
			sb.append(element + " ; ");
		}
		sb.append(" } ");
		return sb.toString();
	}
}
