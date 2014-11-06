package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.immutable.interfaces.IImmutableCollection;

public class ImmutableBag<T> extends AbstractImmutableCollection<T> implements IImmutableCollection<T> {

	public ImmutableBag(List<T> content) {
		super(content);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(T element) {
		this.getContent().add(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(T element) {
		this.getContent().remove(element);
	}
}
