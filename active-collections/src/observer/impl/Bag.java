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
	
	public static void main(String[] args){
			
		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {{
		    add(1);
		    add(2);
		    add(3);
		}});
		AbstractCollection<Integer> b = new Bag<Integer>(new ArrayList<Integer>() {{
		    add(4);
		    add(5);
		    add(6);
		}});
		AbstractCollection<Integer> d = new Bag<Integer>(new ArrayList<Integer>() {{
		    add(7);
		}});
		C<Integer> c = a.union(b);
		System.out.println(c);
		a.add(new ArrayList<Integer>(), 1);
//		a.getC();
		System.out.println(c);
	}
}
