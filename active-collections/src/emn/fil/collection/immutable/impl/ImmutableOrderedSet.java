package emn.fil.collection.immutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableOrderedSet<T> extends ImmutableSet<T> {

	private ImmutableSequence<T> sequence;

	public ImmutableOrderedSet(List<T> content) {
		super(content);
	}

	public ImmutableOrderedSet(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public ImmutableOrderedSet(List<T> content, Predicate<T> func) {
		super(content, func);
	}
	
	public ImmutableOrderedSet(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(EventCollectionMessage<T> event) {
		super.add(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {
		super.remove(event);
	}
}