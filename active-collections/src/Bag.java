import java.util.ArrayList;
import java.util.List;


public class Bag<T> implements ICollection {
	private Bag c;
	private List<T> content;
	
	public Bag(){
		
	}
	
	public Bag(List<T> content) {
		super();
		this.content = content;
	}
	
	private Bag union(Bag b){
		// on cree C
		List newC = new ArrayList(content);
		newC.addAll(b.getContent());
		
		// on lie a et b a C
		this.c = new Bag(newC);
		b.linkedTo(c);
		
		return this.c;
	}
	
	public void add(Object objet){
		
	}
	
	private void linkedTo(Bag c) {
		this.c = c;
	}
	
	private List<T> getContent() {
		return this.content;
	}
	
	public String toString(){
		return this.content.toString();
	}
	
	public static void main(String[] args){
		
		Bag a = new Bag(new ArrayList<Integer>() {{
		    add(1);
		    add(2);
		    add(3);
		}});
		Bag b = new Bag(new ArrayList<Integer>() {{
		    add(4);
		    add(5);
		    add(6);
		}});
		Bag d = new Bag(new ArrayList<Integer>() {{
		    add(7);
		}});
		Bag c = a.union(b);
		a.add(1);
//		a.getC();
		System.out.println(c);
	}
}
