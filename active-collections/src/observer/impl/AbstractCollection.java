package observer.impl;

import java.util.ArrayList;
import java.util.List;

import observer.C;
import observer.Subject;
import observer.interfaces.ICollection;

public abstract class AbstractCollection<T> extends Subject<T> implements
		ICollection<T> {

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
	 * 
	 * @param b
	 * @return
	 */
	public C<T> intersection(AbstractCollection<T> b) {
		// on cree C
		final List<T> newList = new ArrayList<T>();;
		final List<T> bList = b.getContent();

		for (int i = 0; i < bList.size(); i++) {
			if (!content.contains(bList.get(i))) {
				add(bList.get(i));
			}
		}

		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * Union with another collection
	 * 
	 * @param b
	 * @return
	 */
	public C<T> union(AbstractCollection<T> b) {
		// on cree C
		final List<T> newList = new ArrayList<T>(content);
		final List<T> bList = b.getContent();

		// evite les doublons
		for (int i = 0; i < bList.size(); i++) {
			System.out.println("-" + bList.get(i) + "-");
			add(bList.get(i));
		}

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
}
