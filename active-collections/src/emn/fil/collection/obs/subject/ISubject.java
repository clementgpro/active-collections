package emn.fil.collection.obs.subject;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.observer.Observer;
import emn.fil.collection.obs.type.OAbstract;

public interface ISubject<T extends OAbstract> {
	public void addObserver(Observer<T> c);

	public void notify(EventCollectionMessage<T> event);
	
	public void notifyAttributeChanged(EventCollectionAttribute<? extends OAbstract> event);
}
