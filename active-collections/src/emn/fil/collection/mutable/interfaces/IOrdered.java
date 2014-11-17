package emn.fil.collection.mutable.interfaces;

import emn.fil.collection.obs.type.OAbstract;


public interface IOrdered<T extends OAbstract> extends ICollection<T> {
	public void add(int index, T element);
	public void remove(final int index);
}