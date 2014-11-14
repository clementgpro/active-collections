package emn.fil.collection.immutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.interfaces.IImmutableCollection;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class ImmutableBag<T> extends AbstractImmutableCollection<T> implements IImmutableCollection<T> {

	public ImmutableBag(List<T> content) {
		super(content);
	}

	public ImmutableBag(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public ImmutableBag(List<T> content, Predicate<T> func) {
		super(content, func);
	}

	public ImmutableBag(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
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
	protected void addSort(EventCollectionMessage<T> event) {
		this.add(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {
		this.getContent().remove(event.getElement());	
	}
}
