package emn.fil.collection.mutable.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.subject.Subject;

public abstract class AbstractCollection<T> extends Subject<T> implements ICollection<T> {

	/** Content of this collection. */
	protected List<T> content;

	public AbstractCollection(List<T> content) {
		this.content = content;
	}

	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	public void remove(T element) {
		this.getContent().remove(element);
		this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.REMOVE));
	}
	
	public boolean isEmpty() {
		return this.content.isEmpty();
	}
	
	public int size() {
		return this.content.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> intersection(AbstractCollection<T> b) {

		// we create C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do
		{
			T bListElement = bList.get(i);
			if (this.content.contains(bListElement))
			{
				add(newList, bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		AbstractImmutableCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> union(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(content);
		final List<T> bList = b.getContent();

		// evite les doublons
		final int bListSize = bList.size();
		int i = 0;
		do
		{
			add(newList, bList.get(i));
			i++;
		} while (i < bListSize);

		// link
		AbstractImmutableCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> difference(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(this.getContent());
		final List<T> bList = b.getContent();
		
		final int bListSize = bList.size();
		int i = 0;
		do
		{
			T bListElement = bList.get(i);
			if (this.content.contains(bListElement))
			{
				newList.remove(bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		AbstractImmutableCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	/**
	 * Function linking this collection and B with C
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	protected void link(AbstractImmutableCollection<T> c, AbstractCollection<T> b) {
		// link
		this.addObserver(c);
		b.addObserver(c);
	}

	/**
	 * Function linking this collection with B
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	protected void link(AbstractImmutableCollection<T> b) {
		// link
		this.addObserver(b);
	}

	public List<T> getContent() {
		return content;
	}
		
	public AbstractImmutableCollection<T> apply(Function<T, T> func) {
		final List<T> newList = this.content.stream()
				.map(func)
				.collect(Collectors.toList());
		AbstractImmutableCollection<T> b = this.createCollectionTypeWhenApply(newList, func);
		
		return b;
	}
		
	public AbstractImmutableCollection<T> selection(Predicate<T> func) {
		
		final List<T> newList = this.content.stream()
				.filter(func)
				.collect(Collectors.toList());
		AbstractImmutableCollection<T> b = this.createCollectionTypeWhenSelec(newList, func);
		
		return b;
	}
	
	public boolean exists(AbstractCollection<T> b) {
		Predicate<T> func = (T e) -> {
			return b.getContent().contains(e);
		};
		return this.selection(func).size() == b.size();
	}
	
	public AbstractImmutableCollection<T> toUnique() {
		List<T> tmpList = new ArrayList<T>();
		Predicate<T> func = (T e) -> {
			if (tmpList.contains(e)) {
				return false;
			} else {
				tmpList.add(e);
				return true;
			}
		};
		
		return this.selection(func);
	}
	
	public AbstractImmutableCollection<T> reject(AbstractCollection<T> b) {
		List<T> tmpList = new ArrayList<T>(b.getContent());
		Predicate<T> func = (T e) -> {
			if (tmpList.contains(e)) {
				tmpList.remove(e);
				return false;
			} else {
				return true;
			}
		};
		
		return this.selection(func);
	}
	
	public AbstractImmutableCollection<T> sort() {
		return this.createCollectionTypeWhenSort(this.content.stream().sorted().collect(Collectors.toList()), null);
	}
	
	public AbstractImmutableCollection<T> sort(final Comparator<T> functionSort) {
		final List<T> newList = 
				this.content
				.stream()
				.sorted(
						new Comparator<T>() {
							public int compare(T element1, T element2){
								return functionSort.compare(element1, element2);
							}
						})
				.collect(Collectors.toList()); 
		return this.createCollectionTypeWhenSort(newList, functionSort);
	}

	/**
	 * Function used inside the collection classes
	 * They are implemented by subclasses to match the requirement of the collection type
	 */
	protected abstract boolean add(List<T> newList, T element);

	protected abstract AbstractImmutableCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b);
	
	protected abstract AbstractImmutableCollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func);

	protected abstract AbstractImmutableCollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func);
	
	protected abstract AbstractImmutableCollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort);
}
