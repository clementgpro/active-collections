package emn.fil.collection.impl;

import java.util.List;

import emn.fil.collection.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollection;

public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {

	public Bag(List<T> content) {
		super(content);
	}
	
	protected void add(List<T> newList, T element) {
		newList.add(element);
	}

	public void add(T element) {
		this.content.add(element);
		this.notify(element, EventCollection.ADD);
	}

	public void remove(T element) {
		this.content.remove(element);
		this.notify(element, EventCollection.REMOVE);
	}

	public String toString() {
		return this.content.toString();
	}
}
