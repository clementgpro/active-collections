package emn.fil.collection.obs.event;

public class EventCollectionMessage<T> {
	/** Element to update. */
	private T element;

	/** Event type. */
	private TypeEventEnum eventCollection;

	/** Index for ordered collections. */
	private int index;

	public EventCollectionMessage(T element, TypeEventEnum eventCollection, int index) {
		super();
		this.element = element;
		this.eventCollection = eventCollection;
		this.index = index;
	}

	public EventCollectionMessage(T element, TypeEventEnum eventCollection) {
		super();
		this.element = element;
		this.eventCollection = eventCollection;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public TypeEventEnum getEventCollection() {
		return eventCollection;
	}

	public void setEventCollection(TypeEventEnum eventCollection) {
		this.eventCollection = eventCollection;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
