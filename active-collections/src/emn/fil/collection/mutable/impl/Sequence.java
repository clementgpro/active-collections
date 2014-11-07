package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.immutable.impl.ImmutableOrderedSet;
import emn.fil.collection.immutable.impl.ImmutableSequence;
import emn.fil.collection.immutable.impl.ImmutableSet;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Sequence<T> extends Bag<T> implements IOrdered<T> {

	public Sequence(List<T> content) {
		super(content);
	}
	
	public void add(int index, T element) {
		this.content.add(index, element);	
		this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD, index));
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c;
		if (b instanceof Bag) {
			 c = new ImmutableBag<T>(newList);
		} else if (b instanceof Set) {
			 c = new ImmutableSet<T>(newList);
		} else  {
			 c = new ImmutableSequence<T>(newList);
		}
		link(c, b);
		return c;
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList);
		link(c, null);
		return c;
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenApply(List<T> newList) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList);
		link(c, null);
		return c;
	}

}
