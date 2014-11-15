package emn.fil.collection.obs.type;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.subject.SubjectAttribute;

public abstract class OAbstract extends SubjectAttribute<OAbstract> implements Comparable<OAbstract> {
	/** State of the object before a change of any attributes. */
	private OAbstract before;

	/**
	 * Give a copy of the current object.
	 * 
	 * @return the copy
	 */
	public abstract OAbstract copy();

	public abstract boolean equals(final Object o);

	/**
	 * Save the current object before any changes.
	 */
	protected void beforeSet() {
		this.before = this.copy();
	}

	/**
	 * Notify all the observers.
	 */
	protected void afterSet() {
		this.notify(new EventCollectionAttribute<OAbstract>(before, this));
	}
}
