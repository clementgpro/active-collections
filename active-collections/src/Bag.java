import java.util.ArrayList;
import java.util.List;


public class Bag<T> extends AbstractCollection<T> implements ICollection<T> {
	private Bag<T> c;
	
	public Bag(List<T> content) {
		super(content);
	}
	
	public ICollection<T> union(ICollection<T> b){
		// on cree C
		List<T> newC = contentUnion(b.getContent());
		
		// on lie a et b a C
		linkedTo(new Bag<T>(newC));	
		b.linkedTo(c);
		
		return getLinked();
	}
	
	public void add(T element) {
		content.add(element);
	}
	
	public String toString(){
		return this.content.toString();
	}
	
	public static void main(String[] args){
		
		ICollection a = new Bag(new ArrayList<Integer>() {{
		    add(1);
		    add(2);
		    add(3);
		}});
		ICollection b = new Bag(new ArrayList<Integer>() {{
		    add(4);
		    add(5);
		    add(6);
		}});
		ICollection d = new Bag(new ArrayList<Integer>() {{
		    add(7);
		}});
		ICollection c = a.union(b);
		a.add(1);
//		a.getC();
		System.out.println(c);
	}
}
