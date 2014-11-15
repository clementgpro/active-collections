package emn.fil.collection;

import java.util.ArrayList;
import java.util.function.Predicate;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.obs.type.OPersonne;

public class AppReification {

	public static void main(String[] args) {
		// branch
		final AbstractCollection<OPersonne> a = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(21, "Emma", 1));
				add(new OPersonne(17, "Oliver", 2));
				add(new OPersonne(12, "Lise", 3));
			}
		});

		Predicate<OPersonne> function = (OPersonne element) -> {
			return element.getAge() > 16;
		};
		AbstractImmutableCollection<OPersonne> b = a.selection(function);
		System.out.println("A=" + a);
		System.out.println("B=" + b);
		System.out.println();

		// modify
		a.getContent().get(0).setAge(14);
		System.out.println("A=" + a);
		System.out.println("B=" + b);
		System.out.println();

		a.add(new OPersonne(19, "Moundir", 4));
		System.out.println("A=" + a);
		System.out.println("B=" + b);
		System.out.println();

	}
}