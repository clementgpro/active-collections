import java.util.List;


public class Set implements IUniqueness {
	
	// Content of this collection
	private List<Object> content;
	
	// Collection linked
	private Set link;
	
	public Set(List<Object> content) {
		this.content = content;
	}
}
