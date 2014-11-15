package emn.fil.collection.immutable.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.immutable.interfaces.IImmutableOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableSequence<T> extends ImmutableBag<T> implements IImmutableOrdered<T> {

	public ImmutableSequence(List<T> content) {
		super(content);
	}

	public ImmutableSequence(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public ImmutableSequence(List<T> content, Predicate<T> func) {
		super(content, func);
	}

	public ImmutableSequence(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void add(EventCollectionMessage<T> event) {
		if (this.functionSort != null)
		{
			final int pos = Collections.binarySearch(getContent(), event.getElement(), this.functionSort);
			if (pos < 0)
				getContent().add(-pos - 1, event.getElement());
		}
		else if (event.getIndex() != 0)
		{
			getContent().add(event.getIndex(), event.getElement());
		}
		else
		{
			// TODO we should have the following code but it's impossible for now
			// cause getContent() is List<T> and T is not identified as a comparable
			// despite T extends Oabstract which implements Comparable
			// final int pos = Collections.binarySearch(getContent(), event.getElement());
//			if (pos < 0)
//				this.getContent().add(-pos - 1, event.getElement());
			getContent().add(event.getElement());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void remove(EventCollectionMessage<T> event) {
		if (event.getElement() != null)
		{
			getContent().remove(event.getElement());
		}
		else
		{
			getContent().remove(event.getIndex());
		}
	}

}
