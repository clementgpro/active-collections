package emn.fil.collection.mutable.impl;

import java.util.List;

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
}