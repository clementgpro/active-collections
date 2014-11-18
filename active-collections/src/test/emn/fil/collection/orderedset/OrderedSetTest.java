package test.emn.fil.collection.orderedset;

import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

import emn.fil.collection.mutable.impl.OrderedSet;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.type.OInteger;

public class OrderedSetTest {

	private final IOrdered<OInteger> a;
	private final IOrdered<OInteger> b;
	private final IOrdered<OInteger> d;
	
	private ICollection<OInteger> c;
	
	public OrderedSetTest(){
		this.a = new OrderedSet<OInteger>();
		this.b = new OrderedSet<OInteger>();
		this.d = new OrderedSet<OInteger>();
		
		this.a.add(0, new OInteger(1));
		this.a.add(1, new OInteger(2));
		this.a.add(2, new OInteger(2));
		this.a.add(3, new OInteger(3));
		
		this.b.add(0, new OInteger(2));
		this.b.add(1, new OInteger(3));
		this.b.add(2, new OInteger(4));
		
		this.d.add(new OInteger(2));
		this.d.add(new OInteger(5));
	}
	
	@Test
	public void union(){
		this.c = a.union(b);
		Assert.assertArrayEquals(new OInteger[]
		{
				new OInteger(1), new OInteger(2), new OInteger(3), new OInteger(4)
		}, this.c.getContent().toArray());
	}
	
	@Test
	public void testIntersection() {
		ICollection<OInteger> tmp = this.a.intersection(d);
		Assert.assertArrayEquals(new OInteger[]
		{
			new OInteger(2)
		}, tmp.getContent().toArray());
	}
	
	@Test
	public void testDifference() {
		ICollection<OInteger> tmp = this.a.difference(d);
		Assert.assertArrayEquals(new OInteger[]
		{
				new OInteger(1), new OInteger(3)
		}, tmp.getContent().toArray());
	}
	
	@Test
	public void testApply() {
		// Test Apply
		Function<OInteger, OInteger> func = (element) -> {
			return new OInteger(element.getValue() * 2);
		};
		ICollection<OInteger> e = this.a.apply(func);

		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[]
		{
				new OInteger(2), new OInteger(4), new OInteger(6)
		});
	}
	
	@Test
	public void testSelection() {
		Predicate<OInteger> func2 = (element) -> {
			return element.getValue() > 1;
		};
		ICollection<OInteger> e = this.a.selection(func2);

		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[]
		{
				new OInteger(2), new OInteger(3)
		});
	}

}
