package observer.impl;

import java.util.ArrayList;
import java.util.List;

import observer.C;
import observer.Subject;
import observer.interfaces.ICollection;

public abstract class AbstractCollection<T> extends Subject<T> implements ICollection<T> {

	// Content of this collection
	protected List<T> content;

	public AbstractCollection(List<T> content) {
		this.content = content;
	}

	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	public List<T> getContent() {
		return content;
	}
	
	/**
	 * Intersection with another collection
	 * @param b
	 * @return
	 */
	public C<T> intersection(AbstractCollection<T> b) {
		
		// on cree C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do {
			T bListElement = bList.get(i);
			if (content.contains(bListElement)) {
				add(newList, bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}
	
	/**
	 * Union with another collection
	 * @param b
	 * @return
	 */
	public C<T> union(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(content);
		final List<T> bList = b.getContent();

		// evite les doublons
		final int bListSize = bList.size();
		int i = 0;
		do {
			add(newList, bList.get(i));
			i++;
		} while (i < bListSize);
		
		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * Function linking this collection and B with C
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	private void link(C<T> c, AbstractCollection<T> b) {
		// link
		this.addObserver(c);
		b.addObserver(c);
	}
	
	protected abstract void add(List<T> newList, T element);
}