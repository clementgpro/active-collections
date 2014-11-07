package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.functions.FunctionSelec;
import emn.fil.collection.immutable.interfaces.IImmutableOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableSequence<T> extends ImmutableBag<T> implements IImmutableOrdered<T> {

	public ImmutableSequence(List<T> content) {
		super(content);
	}
	
	public ImmutableSequence(List<T> content, FunctionApply<T> func) {
		super(content, func);
	}

	public ImmutableSequence(List<T> content, FunctionSelec<T> func) {
		super(content, func);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(EventCollectionMessage<T> event) {
		if (event.getIndex() != 0) {
			getContent().add(event.getIndex(), event.getElement());
		} else {
			getContent().add(event.getElement());
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {		
		if (event.getElement() != null) {
			getContent().remove(event.getElement());
		} else {
			getContent().remove(event.getIndex());
		}
	}

}
