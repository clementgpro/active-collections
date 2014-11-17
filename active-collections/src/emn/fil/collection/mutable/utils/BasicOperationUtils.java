package emn.fil.collection.mutable.utils;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.mutable.impl.AbstractCollection;
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
				a.add(newList, bListElement.copy());
			}
			i++;
		} while (i < bListSize);

		// link
		ICollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> ICollection<T> union(ICollection<T> a, ICollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>();
		
		copyList(a, newList, a.getContent());
		copyList(a, newList, b.getContent());

		// link
		ICollection<T> c = a.createCollectionType(newList, b);

		return c;
	}

	public static <T extends OAbstract> ICollection<T> difference(ICollection<T> a, ICollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();
		
		// fill newList with element of A
		copyList(a, newList, a.getContent());

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
	
	/**
	 * Copy the bList element into newList with creating new instances of them
	 * @param a
	 * @param newList
	 * @param bList
	 */
	private static <T extends OAbstract> void copyList(ICollection<T> a, List<T> newList, List<T> bList) {
		// fill the list
		final int bListSize = bList.size();
		int i = 0;
		do
		{
			a.add(newList, bList.get(i).copy());
			i++;
		} while (i < bListSize);
	}

}
