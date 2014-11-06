package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollection;

public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {

	public Bag(List<T> content) {
		super(content);
	}

	protected boolean add(List<T> newList, T element) {
		return newList.add(element);
	}

	public void add(T element) {
		if (this.add(this.getContent(), element))
		{
			this.notify(element, EventCollection.ADD);
		}
	}

	public void remove(T element) {
		this.getContent().remove(element);
		this.notify(element, EventCollection.REMOVE);
	}

	public String toString() {
		return this.getContent().toString();
	}
}
