package emn.fil.collection.impl;

import java.util.List;

import emn.fil.collection.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollection;

public class Sequence<T> extends AbstractCollection<T> implements IOrdered<T> {

	private int index;

	public Sequence(List<T> content) {
		super(content);
		this.index = 0;
	}

	@Override
	protected boolean add(List<T> newList, T element) {
		newList.add(getIndex(), element);
		this.index++;
		return true;
	}

	@Override
	public void add(T element) {
		if (this.add(getContent(), element))
		{
			this.notify(element, EventCollection.ADD);
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getIndex() {
		return index;
	}

}
