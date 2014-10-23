import java.util.ArrayList;
import java.util.List;

public class Set<T> extends AbstractCollection<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	public ICollection<T> union(ICollection<T> b) {
		// on cree C
		List<T> newC = contentUnion(b.getContent());

		// on lie a et b a C
		linkedTo(new Set<T>(newC));
		b.linkedTo(link);

		// Caster sur un Set<T> et changer le type de retour ?
		// a voir lorsqu'on appelera cette méthode
		return getLinked();
	}

	public ICollection<T> intersection(ICollection<T> b) {

		// on cree C
		List<T> newC = contentIntersection(b.getContent());

		// on lie a et b a C
		linkedTo(new Set<T>(newC));
		b.linkedTo(link);

		// Caster sur un Set<T> et changer le type de retour ?
		// a voir lorsqu'on appelera cette méthode
		return getLinked();
	}

	public void add(T element) {
		if (!content.contains(element)) {
			content.add(element);
		}
	}

}
