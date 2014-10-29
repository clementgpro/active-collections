package observer.impl;
import java.util.List;

import observer.interfaces.IUniqueness;

public class Set<T> extends AbstractCollection<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	public void add(T element) {
		if (!content.contains(element)) {
			content.add(element);
		}
	}

}
