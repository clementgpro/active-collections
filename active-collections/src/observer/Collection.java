package observer;

import java.util.ArrayList;
import java.util.List;

public class Collection<T> extends Subject<T> {
	private List<T> content;

	public Collection(List<T> content) {
		this.content = content;
	}

	public List<T> union(Collection<T> other) {
		// c instanciation
		List<T> newList = new ArrayList<T>();
		newList.addAll(content);
		newList.addAll(other.getContent());

		// link
		C<T> c = new C<T>(newList);
		this.addObserver(c);
		other.addObserver(c);
		return newList;
	}

	public void add(T element) {
		this.content.add(element);
		this.notifyC(element);
	}

	public List<T> getContent() {
		return this.content;
	}
}
