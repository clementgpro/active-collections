package emn.fil.collection;

import java.util.ArrayList;

import test.emn.fil.collection.object.TestingClass;
import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.functions.FunctionSelec;
import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

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
		AbstractImmutableCollection<Integer> c = a.union(b);
		System.out.println(c);
		// a.add(1);
//		a.remove(1);
		// a.getC();
		System.out.println(c);

		// C<Integer> e = b.intersection(d);
		// System.out.println(e);
		//
		// C<Integer> f = a.difference(b);
		// System.out.println("A\\B=" + f);
		//
		// C<Integer> g = b.difference(d);
		// System.out.println("B\\D=" + g);
		
		AbstractCollection<TestingClass> test = new Bag<TestingClass>(new ArrayList<TestingClass>() {
			{
				add(new TestingClass(18, "Clement", 12345));
				add(new TestingClass(22, "Benjamin", 666));
				add(new TestingClass(53, "Mamadou", 69));
			}
		});
		
		// Test Apply
		FunctionApply<TestingClass> func = (TestingClass element) -> { return new TestingClass(5, element.getName(), element.getNumero()); };
		AbstractImmutableCollection<TestingClass> e = test.apply(func);
		System.out.println(test);
		System.out.println(e);
		
		// Test Selec
		FunctionSelec<TestingClass> func2 = (TestingClass element) -> { return element.getAge() > 18;};
		AbstractImmutableCollection<TestingClass> e2 = test.selection(func2);
		System.out.println(test);
		System.out.println(e2);

	}
}
