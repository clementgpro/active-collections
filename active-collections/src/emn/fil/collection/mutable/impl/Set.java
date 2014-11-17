package emn.fil.collection.mutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.interfaces.IUniqueness;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.type.OAbstract;

public class Set<T extends OAbstract> extends Bag<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	public Set() {
		super();
	}

	public Set(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public Set(List<T> content, Predicate<T> func) {
		super(content, func);
	}

	public Set(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}

	public boolean add(final List<T> newC, final T element) {
		boolean added = false;
		if (!newC.contains(element))
		{
			added = newC.add(element);
		}
		return added;
	}

	@Override
	public ICollection<T> createCollectionType(final List<T> newList, final ICollection<T> b) {
		ICollection<T> c;
		if (b instanceof Bag)
		{
			c = new Bag<T>(newList);
		}
		else
		{
			c = new Set<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	public ICollection<T> createCollectionTypeWhenSelec(final List<T> newList, final Predicate<T> func) {
		ICollection<T> c = new Set<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	public ICollection<T> createCollectionTypeWhenSort(final List<T> newList, final Comparator<T> functionSort) {
		ICollection<T> c = new OrderedSet<T>(newList);
		link(c);
		return c;
	}

	//

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(EventCollectionMessage<T> event) {
		if (!this.getContent().contains(event.getElement()))
		{
			add(event.getElement());
		}
	}
}
