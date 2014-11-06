package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.immutable.interfaces.IImmutableOrdered;

public class ImmutableSequence<T> extends ImmutableBag<T> implements IImmutableOrdered<T> {

	private int index;
	
	public ImmutableSequence(List<T> content) {
		super(content);
		this.index = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(T element) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(T element) {
		// TODO Auto-generated method stub

	}

}
