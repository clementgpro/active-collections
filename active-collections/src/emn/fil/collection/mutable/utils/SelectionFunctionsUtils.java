package emn.fil.collection.mutable.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.type.OAbstract;

public class SelectionFunctionsUtils<T extends OAbstract> {
	
	public static <T extends OAbstract> ICollection<T> selection(ICollection<T> a, Predicate<T> func) {

		final List<T> newList = new ArrayList<T>();

		for (T element : a.getContent())
		{
			if (func.test(element))
			{
				newList.add(element.copy());
			}
		}
		ICollection<T> b = a.createCollectionTypeWhenSelec(newList, func);

		return b;
	}

	/**
	 * Operations using Selection function of the collection
	 */

	public static <T extends OAbstract> boolean exists(ICollection<T> a, ICollection<T> b) {
		List<T> tmpList = new ArrayList<T>(b.getContent());
		Predicate<T> func = (T e) -> {
			boolean res = tmpList.contains(e);
			tmpList.remove(e);
			return res;
		};
		return a.selection(func).size() == b.size();
	}

	public static <T extends OAbstract> ICollection<T> toUnique(ICollection<T> a) {
		List<T> tmpList = new ArrayList<T>();
		Predicate<T> func = (T e) -> {
			if (tmpList.contains(e))
			{
				return false;
			}
			else
			{
				tmpList.add(e);
				return true;
			}
		};

		return a.selection(func);
	}

	public static <T extends OAbstract> ICollection<T> reject(ICollection<T> a, ICollection<T> b) {
		List<T> tmpList = new ArrayList<T>(b.getContent());
		Predicate<T> func = (T e) -> {
			if (tmpList.contains(e))
			{
				tmpList.remove(e);
				return false;
			}
			else
			{
				return true;
			}
		};

		return a.selection(func);
	}

}
