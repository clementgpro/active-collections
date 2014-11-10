package emn.fil.collection;

import java.util.ArrayList;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class AppSort {

	public static void main(String[] args) {

		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(3);
				add(2);
				add(1);
			}
		});

		AbstractImmutableCollection<Integer> b = a.sort((x, y) -> x - y);
		System.out.println(b);

	}
}
