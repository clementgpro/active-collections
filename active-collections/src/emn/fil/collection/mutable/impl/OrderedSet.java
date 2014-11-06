package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;

public class OrderedSet<T> extends Set<T> {

	private Sequence<T> sequence;

	public OrderedSet(List<T> content) {
		super(content);
	}

	@Override
	public void add(T element) {
		super.add(element);
	}

	@Override
	protected boolean add(List<T> newList, T element) {
		return super.add(newList, element);
	}

	@Override
	public void remove(T element) {
		super.remove(element);
	}
	
	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, b);
		return c;
	}
}