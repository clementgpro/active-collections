package emn.fil.collection.obs.subject;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.observer.ObserverAttribute;

public abstract class SubjectAttribute<T> {
	protected List<ObserverAttribute<T>> observers;

	public SubjectAttribute() {
		this.observers = new ArrayList<ObserverAttribute<T>>();
	}

	public void addObserver(ObserverAttribute<T> c) {
		this.observers.add(c);
	}

	protected void notify(EventCollectionAttribute<T> event) {
		for (ObserverAttribute<T> o : observers)
		{
			o.update(event);
		}
	}
}
