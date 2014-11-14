package test.emn.fil.collection.bag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.emn.fil.collection.object.Personne;
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

	}

	@Test
	public void testUnion() {
		this.c = a.union(b);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 2, 3, 2, 3, 4 }, this.c.getContent().toArray());
	}

	@Test
	public void testIntersection() {
		AbstractImmutableCollection<Integer> tmp = this.a.intersection(d);
		Assert.assertArrayEquals(new Integer[] { 2 }, tmp.getContent().toArray());
	}

	@Test
	public void testDifference() {
		AbstractImmutableCollection<Integer> tmp = this.a.difference(d);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		this.c = a.union(b);
		final Integer element = 1;
		a.add(element);
		final List<Integer> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(new Integer[] { 1, 2, 2, 3, 2, 3, 4, 1 }, this.c.getContent().toArray());
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		this.c = a.union(b);
		final Integer element = 1;
		a.remove(element);
		final List<Integer> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(new Integer[] { 2, 2, 3, 2, 3, 4 }, this.c.getContent().toArray());
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

	@Test
	public void testGetContent() {
		Assert.assertArrayEquals(this.a.getContent().toArray(), new Integer[] { 1, 2, 2, 3 });
	}

	@Test
	public void testReject() {
		AbstractImmutableCollection<Integer> tmp = this.a.reject(b);
		Assert.assertArrayEquals(tmp.getContent().toArray(), new Integer[] { 1, 2 });
	}

	@Test
	public void testExists() {
		Bag<Integer> tmp = new Bag<Integer>();
		tmp.add(2);
		tmp.add(3);

		boolean result = this.a.exists(tmp);

		Assert.assertTrue(result);
	}

	@Test
	public void testToUnique() {

		AbstractImmutableCollection<Integer> tmp = this.a.toUnique();

		Assert.assertArrayEquals(tmp.getContent().toArray(), new Integer[] { 1, 2, 3 });
	}

	@Test
	public void testIsEmpty() {

		Bag<Integer> tmp = new Bag<Integer>();

		boolean result = tmp.isEmpty();
		Assert.assertTrue(result);

		tmp.add(2);
		tmp.add(3);
		result = tmp.isEmpty();
		Assert.assertFalse(result);
	}

	@Test
	public void testApply() {
		// Test Apply
		Function<Integer, Integer> func = (Integer element) -> {
			return element * 2;
		};
		AbstractImmutableCollection<Integer> e = this.a.apply(func);

		Assert.assertArrayEquals(e.getContent().toArray(), new Integer[] { 2, 4, 4, 6 });
	}

	@Test
	public void testSelection() {
		// Test Selec
		Predicate<Integer> func2 = (Integer element) -> {
			return element > 1;
		};
		AbstractImmutableCollection<Integer> e = this.a.selection(func2);

		Assert.assertArrayEquals(e.getContent().toArray(), new Integer[] { 2, 2, 3 });
	}
	
	@Test
	public void testSort() {
		Bag<Integer> tmp = new Bag<Integer>();
		tmp.add(2);
		tmp.add(3);
		tmp.add(1);
		tmp.add(2);
		tmp.add(7);
		tmp.add(5);
		
		AbstractImmutableCollection<Integer> e = tmp.sort();
		
		Assert.assertArrayEquals(e.getContent().toArray(), new Integer[] { 1, 2, 2, 3, 5, 7 });
		tmp.add(4);
		Assert.assertArrayEquals(e.getContent().toArray(), new Integer[] { 1, 2, 2, 3, 4, 5, 7 });
	}
	
	@Test
	public void testSortWithFunction() {
		final AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(10);
				add(2);
				add(1);
			}
		});
		final AbstractImmutableCollection<Integer> b = a.sort((x, y) -> y - x);
		Assert.assertArrayEquals(new Integer[] { 10, 2, 1 }, b.getContent().toArray());
		a.add(9);
		Assert.assertArrayEquals(new Integer[] { 10, 9, 2, 1 }, b.getContent().toArray());
	}

}
