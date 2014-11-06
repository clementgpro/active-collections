package emn.fil.collection.mutable.impl;

import java.util.List;

public class OrderedSet<T> extends Set<T> {

	private Sequence<T> sequence;

	public OrderedSet(List<T> content) {
		super(content);
	}

	@Override
	public void add(T element) {
		// return false;
	}

	@Override
	public boolean add(List<T> newList, T element) {
		return false;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
	}
}