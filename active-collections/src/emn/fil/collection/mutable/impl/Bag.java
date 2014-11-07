package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {

	public Bag(List<T> content) {
		super(content);
	}
	
	public Bag(){
		super();
	}
	
	protected boolean add(List<T> newList, T element) {
		return newList.add(element);
	}

	public void add(T element) {
		if (this.add(this.content, element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
		}
	}

	public String toString() {
		return this.getContent().toString();
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, b);
		return c;
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList) {
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, null);
		return c;
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenApply(List<T> newList) {
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, null);
		return c;
	}
}
