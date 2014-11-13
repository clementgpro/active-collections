package emn.fil.collection;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import test.emn.fil.collection.object.Personne;
import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class App {

	public static void main(String[] args) {
		// branch
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
		
		AbstractCollection<Personne> test = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Clement", 12345));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(53, "Mamadou", 69));
			}
		});
		
		// Test Apply
		Function<Personne, Personne> func = (Personne element) -> { return new Personne(5, element.getName(), element.getNumero()); };
		AbstractImmutableCollection<Personne> e = test.apply(func);
		System.out.println(test);
		System.out.println(e);
		
		// Test Selec
		Predicate<Personne> func2 = (Personne element) -> { return element.getAge() > 18;};
		AbstractImmutableCollection<Personne> e2 = test.selection(func2);
		System.out.println("ALLOOOOOOOOOOOOOOOOO \n" + test);
		System.out.println(e2);
		
		// Test exists
		AbstractCollection<Personne> testExist = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Clement", 12345));
				add(new Personne(53, "Mamadou", 69));
			}
		});
		System.out.println(test.exists(testExist));
		
		// test toUnique
		AbstractCollection<Personne> testUnique = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Clement", 12345));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(53, "Mamadou", 69));
				add(new Personne(53, "Mamadou", 69));
			}
		});
		AbstractImmutableCollection<Personne> e3 = testUnique.toUnique();
		System.out.println(testUnique);
		System.out.println(e3);
		
		AbstractImmutableCollection<Personne> e4 = testUnique.reject(test);
		System.out.println("BIS \n" + e4);

	}
}
