package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.functions.FunctionSelec;
import emn.fil.collection.immutable.interfaces.IImmutableCollection;
import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableBag<T> extends AbstractImmutableCollection<T> implements IImmutableCollection<T> {

	public ImmutableBag(List<T> content) {
		super(content);
	}
	
	public ImmutableBag(List<T> content, FunctionApply<T> func) {
		super(content, func);
	}

	public ImmutableBag(List<T> content, FunctionSelec<T> func) {
		super(content, func);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(EventCollectionMessage<T> event) {
		this.getContent().add(event.getElement());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {
		this.getContent().remove(event.getElement());
	}
}
