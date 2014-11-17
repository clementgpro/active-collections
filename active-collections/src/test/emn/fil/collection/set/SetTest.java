package test.emn.fil.collection.set;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

import test.emn.fil.collection.object.OPersonne;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.mutable.impl.Set;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.type.OInteger;

public class SetTest {

	private final ICollection<OInteger> a;
	private final ICollection<OInteger> b;
	private final ICollection<OInteger> d;
	
	private ICollection<OInteger> c;
	
	public SetTest() {
		this.a = new Set<OInteger>();
		this.b = new Set<OInteger>();
		this.d = new Set<OInteger>();
		
		this.a.add(new OInteger(1));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(3));
		
		this.b.add(new OInteger(2));
		this.b.add(new OInteger(3));
		this.b.add(new OInteger(4));
		
		this.d.add(new OInteger(2));
		this.d.add(new OInteger(5));
	}
	
	@Test
	public void testUnion() {
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
	
	@Test
	public void testSortWithoutFunction() {
		final ICollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(10));
				add(new OInteger(2));
				add(new OInteger(1));
			}
		});
		final ICollection<OInteger> b = a.sort();
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(10) }, b.getContent().toArray());
	}

	@Test
	public void testSortOInteger() {
		final ICollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(10));
				add(new OInteger(2));
				add(new OInteger(1));
			}
		});
		final ICollection<OInteger> b = a.sort((x, y) -> x.getValue() - y.getValue());
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(10) }, b.getContent().toArray());
		a.add(new OInteger(9));
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(9), new OInteger(10) }, b.getContent().toArray());
	}

	@Test
	public void testSortOPersonne() {
		// tests sur les OPersonnes
		final OPersonne mamadou = new OPersonne(53, "Mamadou", 69);
		final OPersonne benjamin = new OPersonne(22, "Benjamin", 666);
		final OPersonne clement = new OPersonne(18, "Clement", 12345);
		final OPersonne jose = new OPersonne(24, "José", 9393);
		final ICollection<OPersonne> a = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(mamadou);
				add(benjamin);
				add(clement);
			}
		});
		final ICollection<OPersonne> b = a.sort((p1, p2) -> p1.getAge() - p2.getAge());
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, mamadou }, b.getContent().toArray());
		a.add(jose);
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, jose, mamadou }, b.getContent().toArray());
	}

}
