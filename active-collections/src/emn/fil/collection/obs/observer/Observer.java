package emn.fil.collection.obs.observer;

import emn.fil.collection.obs.event.EventCollection;

public interface Observer<T> {
	public void update(T element, EventCollection event);
}