package test.emn.fil.collection.bag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

	}

	@Test
	public void testUnion() {
		this.c = a.union(b);
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
		this.c = a.union(b);
		final OInteger element = new OInteger(1);
		a.add(element);
		final List<OInteger> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(2), new OInteger(3), new OInteger(2),
				new OInteger(3), new OInteger(4), new OInteger(1) }, this.c.getContent().toArray());
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		this.c = a.union(b);
		final OInteger element = new OInteger(1);
		a.remove(element);
		final List<OInteger> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(new OInteger[] { new OInteger(2), new OInteger(2), new OInteger(3), new OInteger(2), new OInteger(3),
				new OInteger(4) }, this.c.getContent().toArray());
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

	@Test
	public void testGetContent() {
		Assert.assertArrayEquals(this.a.getContent().toArray(), new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(2), new OInteger(3) });
	}

	@Test
	public void testReject() {
		AbstractImmutableCollection<OInteger> tmp = this.a.reject(b);
		Assert.assertArrayEquals(tmp.getContent().toArray(), new OInteger[] { new OInteger(1), new OInteger(2) });
	}

	@Test
	public void testExists() {
		Bag<OInteger> tmp = new Bag<OInteger>();
		tmp.add(new OInteger(2));
		tmp.add(new OInteger(3));

		boolean result = this.a.exists(tmp);

		Assert.assertTrue(result);
	}

	@Test
	public void testToUnique() {

		AbstractImmutableCollection<OInteger> tmp = this.a.toUnique();

		Assert.assertArrayEquals(tmp.getContent().toArray(), new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(3) });
	}

	@Test
	public void testIsEmpty() {

		Bag<OInteger> tmp = new Bag<OInteger>();

		boolean result = tmp.isEmpty();
		Assert.assertTrue(result);

		tmp.add(new OInteger(2));
		tmp.add(new OInteger(3));
		result = tmp.isEmpty();
		Assert.assertFalse(result);
	}

	@Test
	public void testApply() {
		// Test Apply
		Function<OInteger, OInteger> func = (element) -> {
			return new OInteger(element.getValue() * 2);
		};
		AbstractImmutableCollection<OInteger> e = this.a.apply(func);

		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[] { new OInteger(2), new OInteger(4), new OInteger(4), new OInteger(6) });
	}

	@Test
	public void testSelection() {
		// Test Selec
		Predicate<OInteger> func2 = (element) -> {
			return element.getValue() > 1;
		};
		AbstractImmutableCollection<OInteger> e = this.a.selection(func2);

		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[] { new OInteger(2), new OInteger(2), new OInteger(3) });
	}

	@Test
	public void testSort() {
		Bag<OInteger> tmp = new Bag<OInteger>();
		tmp.add(new OInteger(2));
		tmp.add(new OInteger(3));
		tmp.add(new OInteger(1));
		tmp.add(new OInteger(2));
		tmp.add(new OInteger(7));
		tmp.add(new OInteger(5));

		AbstractImmutableCollection<OInteger> e = tmp.sort();

		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(2), new OInteger(3),
				new OInteger(5), new OInteger(7) });
		tmp.add(new OInteger(4));
		Assert.assertArrayEquals(e.getContent().toArray(), new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(2), new OInteger(3),
				new OInteger(4), new OInteger(5), new OInteger(7) });
	}

	@Test
	public void testSortWithFunction() {
		final AbstractCollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(10));
				add(new OInteger(2));
				add(new OInteger(1));
			}
		});
		final AbstractImmutableCollection<OInteger> b = a.sort((x, y) -> y.getValue() - x.getValue());
		Assert.assertArrayEquals(new OInteger[] { new OInteger(10), new OInteger(2), new OInteger(1) }, b.getContent().toArray());
		a.add(new OInteger(9));
		Assert.assertArrayEquals(new OInteger[] { new OInteger(10), new OInteger(9), new OInteger(2), new OInteger(1) }, b.getContent().toArray());
	}

}
