package observer;
import java.util.ArrayList;
import java.util.List;

public class Collection<T> extends Subject<T> {
	private List<T> content;

	public Collection(List<T> content) {
		this.content = content;
	}

	public List<T> union(Collection<T> other) {
		List<T> newList = new ArrayList<T>();
		newList.addAll(content);
		newList.addAll(other.getContent());
		this.link(new C<T>(newList));
		return newList;
	}

	public void add(T element){
		this.content.add(element);
		this.notifyC(element);
	}
	
	public List<T> getContent() {
		return this.content;
	}

}
