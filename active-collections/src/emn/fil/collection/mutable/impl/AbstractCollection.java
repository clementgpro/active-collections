package emn.fil.collection.mutable.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.observer.Observer;
import emn.fil.collection.obs.observer.ObserverAttribute;
import emn.fil.collection.obs.subject.Subject;
import emn.fil.collection.obs.type.OAbstract;

public abstract class AbstractCollection<T extends OAbstract> extends Subject<T> implements ICollection<T>, Observer<T>, ObserverAttribute<OAbstract> {

	/** Content of this collection. */
	protected List<T> content;

	/** The Function used to create this collection */
	private Function<T, T> functionApply;
	private Predicate<T> functionSelec;
	protected Comparator<T> functionSort;

	public AbstractCollection(List<T> content) {
		for (T element : content)
		{
			element.addObserver(this);
		}
		this.content = content;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionApply
	 *            function used to create this collection
	 */
	public AbstractCollection(final List<T> content, final Function<T, T> functionApply) {
		super();
		this.content = content;
		this.functionApply = functionApply;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionSelec
	 *            function used to create this collection
	 */
	public AbstractCollection(final List<T> content, final Predicate<T> functionSelec) {
		super();
		this.content = content;
		this.functionSelec = functionSelec;
	}

	/**
	 * Constructor for AbstractImmutableCollection
	 * 
	 * @param content
	 *            the content of the collection
	 * @param functionSort
	 *            function used to create this collection
	 */
	public AbstractCollection(final List<T> content, final Comparator<T> functionSort) {
		super();
		this.content = content;
		this.functionSort = functionSort;
	}

	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	/**
	 * Update Method of Observer Pattern
	 */

	public void updateToCollectThatAttrChanged(EventCollectionAttribute<OAbstract> event) {
		this.notifyAttributeChanged(event);
	}

	@Override
	public void updateCollection(EventCollectionMessage<T> event) {
		switch (event.getEventCollection()) {
		case ADD:

			if (functionSelec != null && !functionSelec.test(event.getElement()))
			{
				// Add element only if it's matching predicate
				break;
			}
			else if (functionApply != null)
			{
				// Modify element to match the function before adding
				event.setElement(functionApply.apply(event.getElement()));
			}
			this.add(event);
			break;
		case REMOVE:
			this.remove(event);
			break;
		default:
			break;
		}
	}

	@Override
	public void updateAttributeChanged(EventCollectionAttribute<? extends OAbstract> event) {

		T element = (functionApply != null) ? functionApply.apply(event.getElementBefore()) : event.getElementBefore();

		// Check if the element before modification was in this collection
		if (this.content.contains(element))
		{
			if (functionSelec != null)
			{
				// Then if the element still check the predicate, we update it
				if (functionSelec.test(event.getElementAfter()))
				{
					this.content.set(this.content.indexOf(event.getElementBefore()), event.getElementAfter());
				}
				// else we delete it
				else
				{
					this.getContent().remove(this.content.indexOf(event.getElementBefore()));
				}
			}
			// Modify element to match the function before updating
			else if (functionApply != null)
			{
				this.content.set(this.content.indexOf(element), functionApply.apply(event.getElementAfter()));
			}
			else if (functionSort != null)
			{
				// TODO
				// change order if needed
			}
		}
		// If it is a new element we add it
		else
		{
			// The selection is the only one possible case for an add
			if (functionSelec != null && functionSelec.test(event.getElementAfter()))
			{
				// Then if the element check the predicate, we add it
				this.content.add(event.getElementAfter());
			}
		}
	}

	/**
	 * Basic functions of collection
	 */

	public Function<T, T> getFunctionApply() {
		return functionApply;
	}

	public Predicate<T> getFunctionSelec() {
		return functionSelec;
	}

	public Comparator<T> getFunctionSort() {
		return functionSort;
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

	public List<T> getContent() {
		return content;
	}
	
	/**
	 * Basic operation on Collection
	 */

	public AbstractCollection<T> intersection(AbstractCollection<T> b) {

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
		AbstractCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	public AbstractCollection<T> union(AbstractCollection<T> b) {

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
		AbstractCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	public AbstractCollection<T> difference(AbstractCollection<T> b) {

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
		AbstractCollection<T> c = this.createCollectionType(newList, b);

		return c;
	}

	/**
	 * Function linking this collection and B with C
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	protected void link(AbstractCollection<T> c, AbstractCollection<T> b) {
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
	protected void link(AbstractCollection<T> b) {
		// link
		this.addObserver(b);
	}
	
	/**
	 * Complex operation on Collection
	 */

	public AbstractCollection<T> apply(Function<T, T> func) {
		final List<T> newList = this.content.stream().map(func).collect(Collectors.toList());
		AbstractCollection<T> b = this.createCollectionTypeWhenApply(newList, func);

		return b;
	}

	public AbstractCollection<T> selection(Predicate<T> func) {

		final List<T> newList = new ArrayList<T>();

		for (T element : this.content)
		{
			if (func.test(element))
			{
				newList.add(element.copy());
			}
		}
		AbstractCollection<T> b = this.createCollectionTypeWhenSelec(newList, func);

		return b;
	}
	
	/**
	 * Operations using Selection function of the collection
	 */

	public boolean exists(AbstractCollection<T> b) {
		List<T> tmpList = new ArrayList<T>(b.getContent());
		Predicate<T> func = (T e) -> {
			boolean res = tmpList.contains(e);
			tmpList.remove(e);
			return res;
		};
		return this.selection(func).size() == b.size();
	}

	public AbstractCollection<T> toUnique() {
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

		return this.selection(func);
	}

	public AbstractCollection<T> reject(AbstractCollection<T> b) {
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

		return this.selection(func);
	}
	
	/**
	 * Sort functions
	 */

	public AbstractCollection<T> sort() {
		return this.createCollectionTypeWhenSort(this.content.stream().sorted().collect(Collectors.toList()), null);
	}

	public AbstractCollection<T> sort(final Comparator<T> functionSort) {
		final List<T> newList = this.content.stream().sorted(new Comparator<T>() {
			public int compare(T element1, T element2) {
				return functionSort.compare(element1, element2);
			}
		}).collect(Collectors.toList());
		return this.createCollectionTypeWhenSort(newList, functionSort);
	}

	public void add(T element) {
		if (this.add(this.content, element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
		}
	}

	/**
	 * Function used inside the collection classes They are implemented by
	 * subclasses to match the requirement of the collection type
	 */
	protected abstract boolean add(List<T> newList, T element);

	protected abstract AbstractCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b);

	protected abstract AbstractCollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func);

	protected abstract AbstractCollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func);

	protected abstract AbstractCollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort);

	/**
	 * Add the element in the list following the type of the collection itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	protected abstract void add(EventCollectionMessage<T> event);

	/**
	 * Remove the element in the list following the type of the collection
	 * itself.
	 * 
	 * @param element
	 *            the element to add
	 */
	protected abstract void remove(EventCollectionMessage<T> event);

}
