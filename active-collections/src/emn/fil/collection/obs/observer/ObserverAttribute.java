package emn.fil.collection.obs.observer;

import emn.fil.collection.obs.event.EventCollectionAttribute;

public interface ObserverAttribute<T> {

	/**
	 * Update the object with the following event.
	 * 
	 * @param event
	 *            contains the element before and after it's been changed
	 */
	public void update(EventCollectionAttribute<T> event);
}