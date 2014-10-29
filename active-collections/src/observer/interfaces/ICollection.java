package observer.interfaces;
import java.util.List;

import observer.C;
import observer.impl.AbstractCollection;

public interface ICollection<T> {
	public C<T> union(AbstractCollection<T> b);
	public void add(T element);
	public List<T> getContent();
}
