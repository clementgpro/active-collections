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
import emn.fil.collection.obs.type.OPersonne;

public class BagWithPersonneTest {

	private final AbstractCollection<OPersonne> a;
	private final AbstractCollection<OPersonne> b;
	private final AbstractCollection<OPersonne> d;

	// C
	private AbstractImmutableCollection<OPersonne> c;

	public BagWithPersonneTest() {
		this.a = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Clement", 1));
				add(new OPersonne(22, "Benjamin", 2));
				add(new OPersonne(30, "Massimo", 3));
			}
		});

		this.b = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Clement", 1));
				add(new OPersonne(22, "Benjamin", 2));
				add(new OPersonne(10, "Julien", 3));
			}
		});

		this.d = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Bertrand", 1));
				add(new OPersonne(18, "Bertrand", 1));
				add(new OPersonne(18, "Bertrand", 1));
				add(new OPersonne(22, "Camille", 2));
			}
		});
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testUnion() {
		this.c = a.union(b);
		OPersonne[] tab = { new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2), new OPersonne(10, "Julien", 3) };
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
	}

	@Test
	public void testIntersection() {
		AbstractImmutableCollection<OPersonne> tmp = this.a.intersection(b);
		OPersonne[] tab = { new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2) };
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testDifference() {
		AbstractImmutableCollection<OPersonne> tmp = this.a.difference(b);
		OPersonne[] tab = { new OPersonne(30, "Massimo", 3), };
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		this.c = a.union(b);
		final OPersonne element = new OPersonne(15, "Test", 3);
		a.add(element);

		OPersonne[] tab = { new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2), new OPersonne(10, "Julien", 3), new OPersonne(15, "Test", 3) };

		final List<OPersonne> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		this.c = a.union(b);

		final OPersonne element = new OPersonne(18, "Clement", 1);
		a.remove(element);

		OPersonne[] tab = { new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2),
				new OPersonne(10, "Julien", 3) };

		final List<OPersonne> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

	@Test
	public void testGetContent() {

		OPersonne[] tab = { new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3) };

		Assert.assertArrayEquals(this.a.getContent().toArray(), tab);
	}

	@Test
	public void testReject() {
		AbstractImmutableCollection<OPersonne> tmp = this.a.reject(b);

		OPersonne[] tab = { new OPersonne(30, "Massimo", 3) };

		Assert.assertArrayEquals(tmp.getContent().toArray(), tab);
	}

	@Test
	public void testExists() {
		Bag<OPersonne> tmp = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(22, "Benjamin", 2));
				add(new OPersonne(30, "Massimo", 3));
			}
		});

		boolean result = this.a.exists(tmp);

		Assert.assertTrue(result);
	}

	@Test
	public void testToUnique() {

		AbstractImmutableCollection<OPersonne> tmp = this.d.toUnique();

		OPersonne[] tab = { new OPersonne(18, "Bertrand", 1), new OPersonne(22, "Camille", 2) };

		Assert.assertArrayEquals(tmp.getContent().toArray(), tab);
	}

	@Test
	public void testIsEmpty() {

		Bag<OPersonne> tmp = new Bag<OPersonne>();

		boolean result = tmp.isEmpty();
		Assert.assertTrue(result);

		tmp.add(new OPersonne(18, "Bertrand", 1));
		tmp.add(new OPersonne(18, "Bertrand", 1));
		result = tmp.isEmpty();
		Assert.assertFalse(result);
	}

	@Test
	public void testApply() {
		// Test Apply
		Function<OPersonne, OPersonne> func = (element) -> {
			return new OPersonne(element.getAge() * 2, element.getName(), element.getNumero());
		};
		AbstractImmutableCollection<OPersonne> e = this.a.apply(func);

		OPersonne[] tab = { new OPersonne(18 * 2, "Clement", 1), new OPersonne(22 * 2, "Benjamin", 2), new OPersonne(30 * 2, "Massimo", 3) };

		Assert.assertArrayEquals(e.getContent().toArray(), tab);
	}

	@Test
	public void testSelection() {
		// Test Selec
		Predicate<OPersonne> func2 = (OPersonne element) -> {
			return element.getAge() > 18;
		};
		AbstractImmutableCollection<OPersonne> e = this.a.selection(func2);

		OPersonne[] tab = { new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3) };

		Assert.assertArrayEquals(e.getContent().toArray(), tab);
	}

	@Test
	public void testSortWithFunction() {
		// tests sur les OPersonnes
		final OPersonne mamadou = new OPersonne(53, "Mamadou", 69);
		final OPersonne benjamin = new OPersonne(22, "Benjamin", 666);
		final OPersonne clement = new OPersonne(18, "Clement", 12345);
		final OPersonne jose = new OPersonne(24, "José", 9393);
		final AbstractCollection<OPersonne> a = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(mamadou);
				add(benjamin);
				add(clement);
			}
		});
		final AbstractImmutableCollection<OPersonne> b = a.sort((p1, p2) -> p1.getAge() - p2.getAge());
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, mamadou }, b.getContent().toArray());
		a.add(jose);
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, jose, mamadou }, b.getContent().toArray());
	}

}
