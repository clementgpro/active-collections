package emn.fil.collection.obs.event;

import emn.fil.collection.obs.type.OAbstract;

public class EventCollectionAttribute<T extends OAbstract> {
	/** ElemenObject to update. */
	private T elementBefore;
	private T elementAfter;

	public EventCollectionAttribute(T before, T subjectCloneable) {
		super();
		this.elementBefore = before;
		this.elementAfter = subjectCloneable;
	}

	public <T extends OAbstract> T getElementBefore() {
		return (T) elementBefore;
	}

	public void setElementBefore(T elementBefore) {
		this.elementBefore = elementBefore;
	}

	public <T extends OAbstract> T getElementAfter() {
		return (T) elementAfter;
	}

	public void setElementAfter(T elementAfter) {
		this.elementAfter = elementAfter;
	}

}
