package emn.fil.collection.impl;

import java.util.List;

import emn.fil.collection.interfaces.IOrdered;

public class Sequence<T> extends AbstractCollection<T> implements IOrdered<T> {
	
	public Sequence(List<T> content) {
		super(content);
	}

	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void add(List<T> newList, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		
	}

}
