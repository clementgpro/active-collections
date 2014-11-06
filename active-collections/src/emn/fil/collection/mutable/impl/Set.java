package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.immutable.impl.ImmutableSet;
import emn.fil.collection.mutable.interfaces.IUniqueness;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Set<T> extends Bag<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	protected boolean add(List<T> newC, T element) {
		boolean added = false;
		if (!newC.contains(element))
		{
			added = newC.add(element);
		}
		return added;
	}

	public void add(T element) {
		if (this.add(getContent(), element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
		}
	}

	public void remove(T element) {
		if (!this.content.contains(element))
		{
			this.content.remove(element);
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.REMOVE));
		}
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c;
		if (b instanceof Bag) {
			c = new ImmutableBag<T>(newList);
		} else {
			c = new ImmutableSet<T>(newList);
		}
		link(c, b);
		return c;
	}

}
