package emn.fil.collection.obs.observer;

import emn.fil.collection.obs.event.EventCollection;

public interface Observer<T> {

	/**
	 * Update the list with the element following the event.
	 * 
	 * @param element
	 *            the element to add or remove
	 * @param event
	 *            the event which has occurred
	 */
	public void update(T element, EventCollection event);
}