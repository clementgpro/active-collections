package emn.fil.collection;

import java.util.ArrayList;

import test.emn.fil.collection.object.Personne;
import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class AppSort {

	public static void main(String[] args) {

		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(10);
				add(2);
				add(1);
			}
		});

		AbstractImmutableCollection<Integer> b = a.sort((x, y) -> x - y);
		a.add(9);
		System.out.println(b);

		// tests sur les personnes
		AbstractCollection<Personne> a2 = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(53, "Mamadou", 69));
				add(new Personne(22, "Benjamin", 666));
				add(new Personne(18, "Clement", 12345));
			}
		});

		AbstractImmutableCollection<Personne> b2 = a2.sort((Personne p1, Personne p2) -> p1.getAge() - p2.getAge());
		a2.add(new Personne(24, "José", 9393));
		System.out.println(b2);

	}
}
