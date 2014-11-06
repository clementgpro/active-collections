package emn.fil.collection.immutable.impl;

import java.util.List;

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
	 * {@inheritDoc}
	 */
	@Override
	public void update(final EventCollectionMessage<T> event) {
		switch (event.getEventCollection()) {
		case ADD:
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
