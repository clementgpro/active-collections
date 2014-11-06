package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Sequence<T> extends Bag<T> implements IOrdered<T> {

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

	public void add(T element) {
		if (this.add(getContent(), element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
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
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, b);
		return c;
	}

}
