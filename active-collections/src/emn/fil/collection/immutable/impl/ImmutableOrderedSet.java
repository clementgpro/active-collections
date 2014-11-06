package emn.fil.collection.immutable.impl;

import java.util.List;

public class ImmutableOrderedSet<T> extends ImmutableSet<T> {

	private ImmutableSequence<T> sequence;

	public ImmutableOrderedSet(List<T> content) {
		super(content);
	}

	@Override
	protected void add(T element) {
		super.add(element);
	}

	@Override
	protected void remove(T element) {
		super.remove(element);
	}
}