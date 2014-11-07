package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.functions.FunctionSelec;
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
	private FunctionApply<T> functionApply;
	private FunctionSelec<T> functionSelec;
	

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
	 * @param functionApply function used to create this collection
	 */
	public AbstractImmutableCollection(List<T> content, FunctionApply<T> functionApply) {
		super();
		this.content = content;
		this.functionApply = functionApply;
	}
	
	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionSelec function used to create this collection
	 */
	public AbstractImmutableCollection(List<T> content, FunctionSelec<T> functionSelec) {
		super();
		this.content = content;
		this.functionSelec = functionSelec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final EventCollectionMessage<T> event) {
		switch (event.getEventCollection()) {
		case ADD:
			
			if (functionSelec != null && !functionSelec.proceed(event.getElement())) {
				// Add element only if it's matching predicate
				break;
			} else if (functionApply != null) {
				// Modify element to match the function before adding
				event.setElement(functionApply.proceed(event.getElement()));
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
	 * Add the element in the list following the type of the collection itselft.
	 * 
	 * @param element
	 *            the element to add
	 */
	protected abstract void add(EventCollectionMessage<T> event);

	/**
	 * Remove the element in the list following the type of the collection
	 * itselft.
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
