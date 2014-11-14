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

public class BagWithPersonneTest {

	private final AbstractCollection<Personne> a;
	private final AbstractCollection<Personne> b;
	private final AbstractCollection<Personne> d;

	// C
	private AbstractImmutableCollection<Personne> c;

	public BagWithPersonneTest() {
		this.a = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Clement", 1));
				add(new Personne(22, "Benjamin", 2));
				add(new Personne(30, "Massimo", 3));
			}
		});

		this.b = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Clement", 1));
				add(new Personne(22, "Benjamin", 2));
				add(new Personne(10, "Julien", 3));
			}
		});

		this.d = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(18, "Bertrand", 1));
				add(new Personne(18, "Bertrand", 1));
				add(new Personne(18, "Bertrand", 1));
				add(new Personne(22, "Camille", 2));
			}
		});
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testUnion() {
		this.c = a.union(b);
		Personne[] tab = { new Personne(18, "Clement", 1), new Personne(22, "Benjamin", 2), new Personne(30, "Massimo", 3), new Personne(18, "Clement", 1),
				new Personne(22, "Benjamin", 2), new Personne(10, "Julien", 3) };
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
	}

	@Test
	public void testIntersection() {
		AbstractImmutableCollection<Personne> tmp = this.a.intersection(b);
		Personne[] tab = { new Personne(18, "Clement", 1), new Personne(22, "Benjamin", 2) };
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testDifference() {
		AbstractImmutableCollection<Personne> tmp = this.a.difference(b);
		Personne[] tab = { new Personne(30, "Massimo", 3), };
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		this.c = a.union(b);
		final Personne element = new Personne(15, "Test", 3);
		a.add(element);

		Personne[] tab = { new Personne(18, "Clement", 1), new Personne(22, "Benjamin", 2), new Personne(30, "Massimo", 3), new Personne(18, "Clement", 1),
				new Personne(22, "Benjamin", 2), new Personne(10, "Julien", 3), new Personne(15, "Test", 3) };

		final List<Personne> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		Assert.assertEquals(element, cContent.get(cContent.size() - 1));
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsRemovedFromA() {
		this.c = a.union(b);

		final Personne element = new Personne(18, "Clement", 1);
		a.remove(element);

		Personne[] tab = { new Personne(22, "Benjamin", 2), new Personne(30, "Massimo", 3), new Personne(18, "Clement", 1), new Personne(22, "Benjamin", 2),
				new Personne(10, "Julien", 3) };

		final List<Personne> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

	@Test
	public void testGetContent() {

		Personne[] tab = { new Personne(18, "Clement", 1), new Personne(22, "Benjamin", 2), new Personne(30, "Massimo", 3) };

		Assert.assertArrayEquals(this.a.getContent().toArray(), tab);
	}

	@Test
	public void testReject() {
		AbstractImmutableCollection<Personne> tmp = this.a.reject(b);

		Personne[] tab = { new Personne(30, "Massimo", 3) };

		Assert.assertArrayEquals(tmp.getContent().toArray(), tab);
	}

	@Test
	public void testExists() {
		Bag<Personne> tmp = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(new Personne(22, "Benjamin", 2));
				add(new Personne(30, "Massimo", 3));
			}
		});

		boolean result = this.a.exists(tmp);

		Assert.assertTrue(result);
	}

	@Test
	public void testToUnique() {

		AbstractImmutableCollection<Personne> tmp = this.d.toUnique();

		Personne[] tab = { new Personne(18, "Bertrand", 1), new Personne(22, "Camille", 2) };

		Assert.assertArrayEquals(tmp.getContent().toArray(), tab);
	}

	@Test
	public void testIsEmpty() {

		Bag<Personne> tmp = new Bag<Personne>();

		boolean result = tmp.isEmpty();
		Assert.assertTrue(result);

		tmp.add(new Personne(18, "Bertrand", 1));
		tmp.add(new Personne(18, "Bertrand", 1));
		result = tmp.isEmpty();
		Assert.assertFalse(result);
	}

	@Test
	public void testApply() {
		// Test Apply
		Function<Personne, Personne> func = (Personne element) -> {
			return new Personne(element.getAge() * 2, element.getName(), element.getNumero());
		};
		AbstractImmutableCollection<Personne> e = this.a.apply(func);

		Personne[] tab = { new Personne(18 * 2, "Clement", 1), new Personne(22 * 2, "Benjamin", 2), new Personne(30 * 2, "Massimo", 3) };

		Assert.assertArrayEquals(e.getContent().toArray(), tab);
	}

	@Test
	public void testSelection() {
		// Test Selec
		Predicate<Personne> func2 = (Personne element) -> {
			return element.getAge() > 18;
		};
		AbstractImmutableCollection<Personne> e = this.a.selection(func2);

		Personne[] tab = { new Personne(22, "Benjamin", 2), new Personne(30, "Massimo", 3) };

		Assert.assertArrayEquals(e.getContent().toArray(), tab);
	}

	@Test
	public void testSortWithFunction() {
		// tests sur les personnes
		final Personne mamadou = new Personne(53, "Mamadou", 69);
		final Personne benjamin = new Personne(22, "Benjamin", 666);
		final Personne clement = new Personne(18, "Clement", 12345);
		final Personne jose = new Personne(24, "José", 9393);
		final AbstractCollection<Personne> a = new Bag<Personne>(new ArrayList<Personne>() {
			{
				add(mamadou);
				add(benjamin);
				add(clement);
			}
		});
		final AbstractImmutableCollection<Personne> b = a.sort((Personne p1, Personne p2) -> p1.getAge() - p2.getAge());
		Assert.assertArrayEquals(new Personne[] { clement, benjamin, mamadou }, b.getContent().toArray());
		a.add(jose);
		Assert.assertArrayEquals(new Personne[] { clement, benjamin, jose, mamadou }, b.getContent().toArray());
	}

}
