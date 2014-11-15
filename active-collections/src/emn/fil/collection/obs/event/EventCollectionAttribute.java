package emn.fil.collection.obs.event;

public class EventCollectionAttribute<T> {
	/** ElemenObject to update. */
	private T elementBefore;
	private T elementAfter;

	public EventCollectionAttribute(T before, T subjectCloneable) {
		super();
		this.elementBefore = before;
		this.elementAfter = subjectCloneable;
	}

	public T getElementBefore() {
		return elementBefore;
	}

	public void setElementBefore(T elementBefore) {
		this.elementBefore = elementBefore;
	}

	public T getElementAfter() {
		return elementAfter;
	}

	public void setElementAfter(T elementAfter) {
		this.elementAfter = elementAfter;
	}

}
