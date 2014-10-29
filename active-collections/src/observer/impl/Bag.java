package observer.impl;
import java.util.ArrayList;
import java.util.List;

import observer.C;
import observer.interfaces.ICollection;


public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {
	
	public Bag(List<T> content) {
		super(content);
	}
	
	protected void add(List<T> newList, T element) {
		newList.add(element);
	}

	public void add(T element) {
		this.content.add(element);
	}
	
	public String toString(){
		return this.content.toString();
	}
}
