package emn.fil.collection.immutable.interfaces;

public interface IImmutableCollection<T> {
	
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