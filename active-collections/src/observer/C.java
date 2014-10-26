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

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	
	

}
