package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.immutable.interfaces.IImmutableOrdered;

public class ImmutableSequence<T> extends AbstractImmutableCollection<T> implements IImmutableOrdered<T> {

	public ImmutableSequence(List<T> content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(T element) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(T element) {
		// TODO Auto-generated method stub

	}

}
