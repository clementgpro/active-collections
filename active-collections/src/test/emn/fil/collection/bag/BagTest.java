package test.emn.fil.collection.bag;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class BagTest {

	private final AbstractCollection<Integer> a;
	private final AbstractCollection<Integer> b;
	private final AbstractCollection<Integer> d;
	
	// C
	private AbstractImmutableCollection<Integer> c;

	public BagTest() {
		this.a = new Bag<Integer>();

		this.a.add(1);
		this.a.add(2);
		this.a.add(2);
		this.a.add(3);
		this.b = new Bag<Integer>();
		this.b.add(2);
		this.b.add(3);
		this.b.add(4);
		this.d = new Bag<Integer>();
		this.d.add(2);
		this.d.add(5);
	}

	@Before
	public void setUp() throws Exception {
		this.c = a.union(b);
	}

	@Test
	public void testUnion() {
		Assert.assertArrayEquals(new Integer[]{1,2,2,3,2,3,4}, this.c.getContent().toArray());
	}
	
	@Test
	public void testIntersection() {
		AbstractImmutableCollection<Integer> tmp = this.a.intersection(d);
		Assert.assertArrayEquals(new Integer[]{2}, tmp.getContent().toArray());
	}
	
	@Test
	public void testDifference() {
		AbstractImmutableCollection<Integer> tmp = this.a.difference(d);
		Assert.assertArrayEquals(new Integer[]{1,2,3}, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		final Integer element = 1;
		a.add(element);
		final List<Integer> cContent = this.c.getContent();
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}
	
	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		final Integer element = 1;
		a.remove(element);
		final List<Integer> cContent = this.c.getContent();
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

}
