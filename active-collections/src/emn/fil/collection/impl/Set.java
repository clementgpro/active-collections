package emn.fil.collection.impl;

import java.util.List;

import emn.fil.collection.interfaces.IUniqueness;
import emn.fil.collection.obs.event.EventCollection;

public class Set<T> extends AbstractCollection<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	protected boolean add(List<T> newC, T element) {
		if (!newC.contains(element)) {
			return newC.add(element);
		}
		return false;
	}

	public void add(T element) {
		if (!content.contains(element)) {
			content.add(element);
			this.notify(element, EventCollection.ADD);
		}
	}

	public void remove(T element) {
		if (!content.contains(element)) {
			content.remove(element);
			this.notify(element, EventCollection.REMOVE);
		}
	}

}
