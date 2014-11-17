package emn.fil.collection.immutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.interfaces.IImmutableUniqueness;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.type.OAbstract;

public class ImmutableSet<T extends OAbstract> extends ImmutableBag<T> implements IImmutableUniqueness<T> {

	public ImmutableSet(List<T> content) {
		super(content);
	}
	
	public ImmutableSet(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public ImmutableSet(List<T> content, Predicate<T> func) {
		super(content, func);
	}
	
	public ImmutableSet(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
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
}
