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
	 * Intersection of the content of the Collection (List<T>)
	 * 
	 * @param b
	 * @return content intersected
	 */
	protected List<T> contentIntersection(List<T> b) {
		// on cree C
		List<T> newC = new ArrayList<T>();

		// on ajoute
		List<T> contentOfB = b;
		int sizeOfB = contentOfB.size();
		T tmpContent = null;
		for (int i = 0; i < sizeOfB; i++) {
			tmpContent = contentOfB.get(i);
			if (!content.contains(tmpContent)) {
				add(newC, tmpContent);
			}
		}

		return newC;
		
	}
	
	/**
	 * Intersection with another collection
	 * @param b
	 * @return
	 */
	public C<T> intersection(AbstractCollection<T> b) {

		// on cree C
		List<T> newList = contentIntersection(b.getContent());

		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * Union of the content of the Collection (List<T>)
	 * @param b
	 * @return
	 */
	protected List<T> contentUnion(List<T> b) {
		// on cree C
		List<T> newC = new ArrayList<T>(content);

		// evite les doublons
		for (int i = 0; i < b.size(); i++) {
			System.out.println("-" + b.get(i) + "-");
			add(newC, b.get(i));
		}

		return newC;
	}
	
	/**
	 * Union with another collection
	 * @param b
	 * @return
	 */
	public C<T> union(AbstractCollection<T> b) {
		// on cree C
		List<T> newList = contentUnion(b.getContent());
		
		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}
	
	/**
	 * Function linking this collection and B with C
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
