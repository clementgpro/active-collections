package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.immutable.interfaces.IImmutableUniqueness;
import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableSet<T> extends ImmutableBag<T> implements IImmutableUniqueness<T> {

	public ImmutableSet(List<T> content) {
		super(content);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(EventCollectionMessage<T> event) {
		if (!this.getContent().contains(event.getElement()))
		{
			this.getContent().add(event.getElement());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {
		this.getContent().remove(event.getElement());
	}
}
