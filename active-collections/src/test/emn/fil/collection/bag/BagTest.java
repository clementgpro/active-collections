package test.emn.fil.collection.bag;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.obs.type.OInteger;

public class BagTest {

	private final AbstractCollection<OInteger> a;
	private final AbstractCollection<OInteger> b;
	private final AbstractCollection<OInteger> d;

	private AbstractImmutableCollection<OInteger> c;

	public BagTest() {
		this.a = new Bag<OInteger>();

		this.a.add(new OInteger(1));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(3));
		this.b = new Bag<OInteger>();
		this.b.add(new OInteger(2));
		this.b.add(new OInteger(3));
		this.b.add(new OInteger(4));
		this.d = new Bag<OInteger>();
		this.d.add(new OInteger(2));
		this.d.add(new OInteger(5));
	}

	@Before
	public void setUp() throws Exception {
		this.c = a.union(b);
	}

	@Test
	public void testUnion() {
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(2), new OInteger(3), new OInteger(2),
				new OInteger(3), new OInteger(4) }, this.c.getContent().toArray());
	}

	@Test
	public void testIntersection() {
		AbstractImmutableCollection<OInteger> tmp = this.a.intersection(d);
		Assert.assertArrayEquals(new OInteger[] { new OInteger(2) }, tmp.getContent().toArray());
	}

	@Test
	public void testDifference() {
		AbstractImmutableCollection<OInteger> tmp = this.a.difference(d);
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(3) }, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		final OInteger element = new OInteger(1);
		a.add(element);
		final List<OInteger> cContent = this.c.getContent();
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		final OInteger element = new OInteger(1);
		a.remove(element);
		final List<OInteger> cContent = this.c.getContent();
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

}
