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
import emn.fil.collection.obs.type.OAbstract;

public class OrderedSet<T extends OAbstract> extends Set<T> {

	private Sequence<T> sequence;

	public OrderedSet(List<T> content) {
		super(content);
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c;
		if (b instanceof Bag)
		{
			c = new ImmutableBag<T>(newList);
		}
		else if (b instanceof Set)
		{
			c = new ImmutableSet<T>(newList);
		}
		else if (b instanceof Sequence)
		{
			c = new ImmutableSequence<T>(newList);
		}
		else
		{
			c = new ImmutableOrderedSet<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func) {
		AbstractImmutableCollection<T> c = new ImmutableOrderedSet<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func) {
		AbstractImmutableCollection<T> c = new ImmutableSequence<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort) {
		AbstractImmutableCollection<T> c = new ImmutableOrderedSet<T>(newList);
		link(c);
		return c;
	}
}
