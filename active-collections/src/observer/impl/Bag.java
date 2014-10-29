package observer.impl;
import java.util.List;

import observer.interfaces.ICollection;


public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {
	
	public Bag(List<T> content) {
		super(content);
	}
	
	public void add(T element) {
		content.add(element);
	}
	
	public String toString(){
		return this.content.toString();
	}
	
//	public static void main(String[] args){
//		
//		ICollection a = new Bag(new ArrayList<Integer>() {{
//		    add(1);
//		    add(2);
//		    add(3);
//		}});
//		ICollection b = new Bag(new ArrayList<Integer>() {{
//		    add(4);
//		    add(5);
//		    add(6);
//		}});
//		ICollection d = new Bag(new ArrayList<Integer>() {{
//		    add(7);
//		}});
//		ICollection c = a.union(b);
//		System.out.println(c);
//		a.add(1);
////		a.getC();
//		System.out.println(c);
//	}
}
