package emn.fil.collection.mutable.utils;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.obs.type.OAbstract;

public class BasicOperationUtils<T extends OAbstract> {
	
	/**
	 * Basic operation on Collection
	 */

	public static <T extends OAbstract> AbstractCollection<T> intersection(AbstractCollection<T> a, AbstractCollection<T> b) {

		// we create C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do
		{
			T bListElement = bList.get(i);
			if (a.getContent().contains(bListElement))
			{
				a.add(newList, bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		AbstractCollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> AbstractCollection<T> union(AbstractCollection<T> a, AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(a.getContent());
		final List<T> bList = b.getContent();

		// evite les doublons
		final int bListSize = bList.size();
		int i = 0;
		do
		{
			a.add(newList, bList.get(i));
			i++;
		} while (i < bListSize);

		// link
		AbstractCollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> AbstractCollection<T> difference(AbstractCollection<T> a, AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(a.getContent());
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do
		{
			T bListElement = bList.get(i);
			if (a.getContent().contains(bListElement))
			{
				newList.remove(bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		AbstractCollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

}
