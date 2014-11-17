package emn.fil.collection.obs.subject;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.observer.Observer;
import emn.fil.collection.obs.type.OAbstract;

public abstract class Subject<T extends OAbstract> {
	protected List<Observer<T>> observers;

	public Subject() {
		this.observers = new ArrayList<Observer<T>>();
	}

	public void addObserver(Observer<T> c) {
		this.observers.add(c);
	}

	protected void notify(EventCollectionMessage<T> event) {
		for (Observer<T> o : observers) {
			o.updateCollection(event);
		}
	}
	
	protected void notifyAttributeChanged(EventCollectionAttribute<? extends OAbstract> event) {
		for (Observer<T> o : observers) {
			o.updateAttributeChanged(event);
		}
	}
}
