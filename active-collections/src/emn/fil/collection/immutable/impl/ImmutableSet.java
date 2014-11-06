package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.immutable.interfaces.IImmutableUniqueness;

public class ImmutableSet<T> extends AbstractImmutableCollection<T> implements IImmutableUniqueness<T> {

	public ImmutableSet(List<T> content) {
		super(content);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(T element) {
		if (!this.getContent().contains(element))
		{
			this.getContent().add(element);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(T element) {
		this.getContent().remove(element);
	}
}
