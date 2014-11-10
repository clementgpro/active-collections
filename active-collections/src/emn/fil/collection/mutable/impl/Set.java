package emn.fil.collection.mutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.immutable.impl.ImmutableOrderedSet;
import emn.fil.collection.immutable.impl.ImmutableSet;
import emn.fil.collection.mutable.interfaces.IUniqueness;

public class Set<T> extends Bag<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	protected boolean add(final List<T> newC, final T element) {
		boolean added = false;
		if (!newC.contains(element))
		{
			added = newC.add(element);
		}
		return added;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionType(final List<T> newList, final AbstractCollection<T> b) {
		AbstractImmutableCollection<T> c;
		if (b instanceof Bag)
		{
			c = new ImmutableBag<T>(newList);
		}
		else
		{
			c = new ImmutableSet<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSelec(final List<T> newList, final Predicate<T> func) {
		AbstractImmutableCollection<T> c = new ImmutableSet<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractImmutableCollection<T> createCollectionTypeWhenSort(final List<T> newList, final Comparator<T> functionSort) {
		AbstractImmutableCollection<T> c = new ImmutableOrderedSet<T>(newList);
		link(c);
		return c;
	}
}
