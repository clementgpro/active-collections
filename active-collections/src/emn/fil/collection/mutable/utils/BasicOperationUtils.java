package emn.fil.collection.mutable.utils;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.type.OAbstract;

public class BasicOperationUtils<T extends OAbstract> {
	
	/**
	 * Basic operation on Collection
	 */

	public static <T extends OAbstract> ICollection<T> intersection(ICollection<T> a, ICollection<T> b) {

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
		ICollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> ICollection<T> union(ICollection<T> a, ICollection<T> b) {

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
		ICollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> ICollection<T> difference(ICollection<T> a, ICollection<T> b) {

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
		ICollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

}
