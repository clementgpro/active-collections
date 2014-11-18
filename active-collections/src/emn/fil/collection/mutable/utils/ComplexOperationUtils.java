package emn.fil.collection.mutable.utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.type.OAbstract;

public class ComplexOperationUtils<T extends OAbstract> {
	
	public static <T extends OAbstract> ICollection<T> apply(ICollection<T> a, Function<T, T> func) {
		final List<T> newList = a.getContent().stream().map(func).collect(Collectors.toList());
		ICollection<T> b = a.createCollectionTypeWhenApply(newList, func);

		return b;
	}

	public static <T extends OAbstract> ICollection<T> sort(ICollection<T> a) {
		return a.createCollectionTypeWhenSort(a.getContent().stream().sorted().collect(Collectors.toList()), null);
	}

	public static <T extends OAbstract> ICollection<T> sort(ICollection<T> a, final Comparator<T> functionSort) {
		final List<T> newList = a.getContent().stream().sorted(new Comparator<T>() {
			public int compare(T element1, T element2) {
				return functionSort.compare(element1, element2);
			}
		})
		.map(x -> {return (T) x.copy();})
		.collect(Collectors.toList());
		return a.createCollectionTypeWhenSort(newList, functionSort);
	}

}
