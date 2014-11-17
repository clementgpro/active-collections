package emn.fil.collection.mutable.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.utils.BasicOperationUtils;
import emn.fil.collection.mutable.utils.ComplexOperationUtils;
import emn.fil.collection.mutable.utils.SelectionFunctionsUtils;
import emn.fil.collection.mutable.utils.UpdateUtils;
import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.subject.Subject;
import emn.fil.collection.obs.type.OAbstract;

public abstract class AbstractCollection<T extends OAbstract> extends Subject<T> implements ICollection<T> {

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
		event.setIndex(this.content.indexOf(event.getElementBefore()));
		this.notifyAttributeChanged(event);
	}

	@Override
	public void updateCollection(EventCollectionMessage<T> event) {
		UpdateUtils.updateCollection(this, event);
	}

	@Override
	public void updateAttributeChanged(EventCollectionAttribute<? extends OAbstract> event) {
		UpdateUtils.updateAttributeChanged(this, event);
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

	public ICollection<T> intersection(ICollection<T> b) {
		return BasicOperationUtils.intersection(this, b);
	}

	public ICollection<T> union(ICollection<T> b) {
		return BasicOperationUtils.union(this, b);
	}

	public ICollection<T> difference(ICollection<T> b) {
		return BasicOperationUtils.difference(this, b);
	}

	/**
	 * Function linking this collection and B with C
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	protected void link(ICollection<T> c, ICollection<T> b) {
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
	protected void link(ICollection<T> b) {
		// link
		this.addObserver(b);
	}

	/**
	 * Operations using Selection function of the collection
	 */

	public ICollection<T> selection(Predicate<T> func) {
		return SelectionFunctionsUtils.selection(this, func);
	}

	public boolean exists(ICollection<T> b) {
		return SelectionFunctionsUtils.exists(this, b);
	}

	public ICollection<T> toUnique() {
		return SelectionFunctionsUtils.toUnique(this);
	}

	public ICollection<T> reject(ICollection<T> b) {
		return SelectionFunctionsUtils.reject(this, b);
	}

	/**
	 * Complex operation on Collection
	 */

	public ICollection<T> apply(Function<T, T> func) {
		return ComplexOperationUtils.apply(this, func);
	}

	public ICollection<T> sort() {
		return ComplexOperationUtils.sort(this);
	}

	public ICollection<T> sort(final Comparator<T> functionSort) {
		return ComplexOperationUtils.sort(this, functionSort);
	}

	public void add(T element) {
		if (this.add(this.content, element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
		}
	}
}
