package observer;
import java.util.ArrayList;
import java.util.List;


public class App {

	public static void main(String[] args) {
		Collection<Integer> a = new Collection<Integer>(new ArrayList<Integer>() {{
		    add(1);
		    add(2);
		}});
		
		Collection<Integer> b = new Collection<Integer>(new ArrayList<Integer>() {{
		    add(3);
		    add(4);
		}});
		
		List<Integer> c = a.union(b);
		System.out.println(c);
		a.add(9);
		System.out.println(c);
		b.add(10);
		System.out.println(c);

	}
}
