package emn.fil.collection.mutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.immutable.impl.ImmutableOrderedSet;
import emn.fil.collection.immutable.impl.ImmutableSequence;
import emn.fil.collection.immutable.impl.ImmutableSet;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Sequence<T> extends Bag<T> implements IOrdered<T> {

	public Sequence(List<T> content) {
		super(content);
	}
	
	public Sequence() {
		super();
	}

	public void add(final int index, final T element) {
		this.content.add(index, element);
		this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD, index));
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionType(final List<T> newList, final AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c;
		if (b instanceof Bag)
		{
			c = new ImmutableBag<T>(newList);
		}
		else if (b instanceof Set)
		{
			c = new ImmutableSet<T>(newList);
		}
		else
		{
			c = new ImmutableSequence<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSelec(final List<T> newList, final Predicate<T> func) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenApply(final List<T> newList, final Function<T, T> func) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSort(final List<T> newList, final Comparator<T> functionSort) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList);
		link(c);
		return c;
	}

}
