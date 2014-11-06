package emn.fil.collection.impl;

import java.util.List;

public class OrderedSet<T> extends Set<T> {
	
	private Sequence<T> sequence;

	public OrderedSet(List<T> content) {
		super(content);		
	}

	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		super.add(element);
	}

	@Override
	protected void add(List<T> newList, T element) {
		// TODO Auto-generated method stub
		super.add(newList, element);
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		super.remove(element);
	}
	
}