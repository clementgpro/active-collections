package observer;
import java.util.List;


public class C<T> implements Observer<T>{
	private List<T> content;
	
	public C(List<T> content) {
		super();
		this.content = content;
	}

	@Override
	public void update(T element) {
		this.getContent().add(element);
	}

	private List<T> getContent() {
		return content;
	}		
	
	public String toString() {
		StringBuilder sb = new StringBuilder("content : { ");
		for ( T element : content) {
			sb.append(element + " ; ");
		}
		sb.append(" } ");
		return sb.toString();
	}
}
