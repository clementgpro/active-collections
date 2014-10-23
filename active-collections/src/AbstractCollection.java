import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCollection<T> implements ICollection<T> {

	// Content of this collection
	protected List<T> content;

	// Collection linked
	protected ICollection<T> link;

	public AbstractCollection(List<T> content) {
		this.content = content;
	}
	
	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	protected List<T> getContent() {
		return content;
	}

	protected void linkedTo(ICollection<T> link) {
		this.link = link;
	}

	protected ICollection<T> getLinked() {
		return this.link;
	}

	/**
	 * Intersection seulement des content (List<T>)
	 * 
	 * @param b
	 * @param noDoublon
	 * @param noUnique
	 * @return
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
				add(tmpContent);
			}
		}

		return newC;
	}

	protected List<T> contentUnion(List<T> b) {
		// on cree C
		List<T> newC = new ArrayList<T>(content);

		// evite les doublons
		List<T> contentOfB = b;
		int sizeOfB = contentOfB.size();
		T tmpContent = null;
		for (int i = 0; i < sizeOfB; i++) {
			tmpContent = contentOfB.get(i);
			add(tmpContent);
		}

		return newC;
	}
}
