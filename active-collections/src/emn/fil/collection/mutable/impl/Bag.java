package emn.fil.collection.mutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.type.OAbstract;

public class Bag<T extends OAbstract> extends AbstractCollection<T> implements ICollection<T> {

	public Bag(List<T> content) {
		super(content);
	}

	public Bag() {
		super();
	}

	public Bag(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public Bag(List<T> content, Predicate<T> func) {
		super(content, func);
	}

	public Bag(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}

	public boolean add(List<T> newList, T element) {
		return newList.add(element);
	}

	public String toString() {
		return this.getContent().toString();
	}

	@Override
	public AbstractCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractCollection<T> c = new Bag<T>(newList);
		link(c, b);
		return c;
	}

	@Override
	public AbstractCollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func) {
		AbstractCollection<T> c = new Bag<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	public AbstractCollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func) {
		AbstractCollection<T> c = new Bag<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	public AbstractCollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort) {
		AbstractCollection<T> c = new Sequence<T>(newList, functionSort);
		link(c);
		return c;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(EventCollectionMessage<T> event) {
		this.getContent().add(event.getElement());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(EventCollectionMessage<T> event) {
		this.getContent().remove(event.getElement());
	}
}
