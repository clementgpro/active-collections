package emn.fil.collection.interfaces;

import java.util.List;

import emn.fil.collection.impl.AbstractCollection;
import emn.fil.collection.obs.observer.C;

public interface ICollection<T> {
	public C<T> union(AbstractCollection<T> b);

	public void add(T element);

	public void remove(T element);

	public List<T> getContent();
}
