package emn.fil.collection.immutable.interfaces;

import emn.fil.collection.obs.type.OAbstract;

public interface IImmutableCollection<T extends OAbstract>  {
	
	/**
	 * Check if the collection is empty
	 * @return true or false
	 */
	public boolean isEmpty();
	
	/**
	 * Return the number of element T contain in this collection
	 * @return
	 */
	public int size();

}