package emn.fil.collection.obs.observer;

import emn.fil.collection.obs.event.EventCollectionMessage;

public interface Observer<T> {

	/**
	 * Update the list with the element following the event.
	 * 
	 * @param event
	 */
	public void update(EventCollectionMessage<T> event);
}