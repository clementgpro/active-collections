package observer;

import java.util.ArrayList;

import observer.impl.AbstractCollection;
import observer.impl.Bag;

public class App {

	public static void main(String[] args) {

		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
			}
		});
		AbstractCollection<Integer> b = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(4);
				add(5);
				add(6);
			}
		});
		AbstractCollection<Integer> d = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(5);
				add(6);
				add(7);
			}
		});
		C<Integer> c = a.union(b);
		System.out.println(c);
		//  a.add(1);
		//  a.getC();
		System.out.println(c);
		
		C<Integer> e = b.intersection(d);
		System.out.println(e);
		
		C<Integer> f = a.difference(b);
		System.out.println("A\\B=" + f);
		
		C<Integer> g = b.difference(d);
		System.out.println("B\\D=" + g);
		
		
	}
}
