package emn.fil.collection.immutable.impl;

import java.util.List;

import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.functions.FunctionSelec;
import emn.fil.collection.obs.event.EventCollectionMessage;

public class ImmutableOrderedSet<T> extends ImmutableSet<T> {

	private ImmutableSequence<T> sequence;

	public ImmutableOrderedSet(List<T> content) {
		super(content);
	}
	
	public ImmutableOrderedSet(List<T> content, FunctionApply<T> func) {
		super(content, func);
	}

	public ImmutableOrderedSet(List<T> content, FunctionSelec<T> func) {
		super(content, func);
	}

	@Override
	protected void add(EventCollectionMessage<T> event) {
		super.add(event);
	}

	@Override
	protected void remove(EventCollectionMessage<T> event) {
		super.remove(event);
	}
}