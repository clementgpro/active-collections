package test.emn.fil.collection.bag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.emn.fil.collection.object.OPersonne;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.mutable.interfaces.ICollection;

public class BagWithPersonneTest {

	private final ICollection<OPersonne> a;
	private final ICollection<OPersonne> b;
	private final ICollection<OPersonne> d;

	// C
	private ICollection<OPersonne> c;

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
		OPersonne[] tab =
		{
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(30, "Massimo", 3),
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(10, "Julien", 3)
		};
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
	}

	@Test
	public void testIntersection() {
		ICollection<OPersonne> tmp = this.a.intersection(b);
		OPersonne[] tab =
		{
				new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2)
		};
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testDifference() {
		ICollection<OPersonne> tmp = this.a.difference(b);
		OPersonne[] tab =
		{
			new OPersonne(30, "Massimo", 3),
		};
		Assert.assertArrayEquals(tab, tmp.getContent().toArray());
	}

	@Test
	public void testCIsUpdatedWhenAnElementIsAddToA() {
		this.c = a.union(b);
		final OPersonne element = new OPersonne(15, "Test", 3);
		a.add(element);

		OPersonne[] tab =
		{
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(30, "Massimo", 3),
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(10, "Julien", 3),
				new OPersonne(15, "Test", 3)
		};

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

		OPersonne[] tab =
		{
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(30, "Massimo", 3),
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(10, "Julien", 3)
		};

		final List<OPersonne> cContent = this.c.getContent();
		Assert.assertEquals(this.a.size() + this.b.size(), this.c.size());
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		Assert.assertEquals(this.a.getContent().get(0), cContent.get(0));
	}

	@Test
	public void testGetContent() {

		OPersonne[] tab =
		{
				new OPersonne(18, "Clement", 1), new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3)
		};

		Assert.assertArrayEquals(this.a.getContent().toArray(), tab);
	}

	@Test
	public void testReject() {
		ICollection<OPersonne> tmp = this.a.reject(b);

		OPersonne[] tab =
		{
			new OPersonne(30, "Massimo", 3)
		};

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

		ICollection<OPersonne> tmp = this.d.toUnique();

		OPersonne[] tab =
		{
				new OPersonne(18, "Bertrand", 1), new OPersonne(22, "Camille", 2)
		};

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
		ICollection<OPersonne> e = this.a.apply(func);

		OPersonne[] tab =
		{
				new OPersonne(18 * 2, "Clement", 1), new OPersonne(22 * 2, "Benjamin", 2), new OPersonne(30 * 2, "Massimo", 3)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab);

		a.add(new OPersonne(14, "Jeremy", 4));

		OPersonne[] tab2 =
		{
				new OPersonne(18 * 2, "Clement", 1),
				new OPersonne(22 * 2, "Benjamin", 2),
				new OPersonne(30 * 2, "Massimo", 3),
				new OPersonne(14 * 2, "Jeremy", 4)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab2);
	}

	@Test
	public void testSelection() {
		// Test Selec
		Predicate<OPersonne> func2 = (OPersonne element) -> {
			return element.getAge() > 18;
		};
		ICollection<OPersonne> e = this.a.selection(func2);

		OPersonne[] tab =
		{
				new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab);

		a.add(new OPersonne(20, "Jeremy", 4));

		OPersonne[] tab2 =
		{
				new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(20, "Jeremy", 4)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab2);

		a.add(new OPersonne(12, "Test", 5));

		OPersonne[] tab3 =
		{
				new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(20, "Jeremy", 4)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab3);
	}

	@Test
	public void testSortWithFunction() {
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
		Assert.assertArrayEquals(new OPersonne[]
		{
				clement, benjamin, mamadou
		}, b.getContent().toArray());
		a.add(jose);
		Assert.assertArrayEquals(new OPersonne[]
		{
				clement, benjamin, jose, mamadou
		}, b.getContent().toArray());
	}

	@Test
	public void testReificationClassic() {
		ICollection<OPersonne> c = a.union(b);
		Assert.assertEquals(18, c.getContent().get(0).getAge());
		a.getContent().get(0).setAge(20);
		Assert.assertEquals(20, c.getContent().get(0).getAge());
	}

	@Test
	public void testReificationOnSelec() {
		// Test Selec
		Predicate<OPersonne> func2 = (OPersonne element) -> {
			return element.getAge() > 18;
		};
		ICollection<OPersonne> e = this.a.selection(func2);

		OPersonne[] tab =
		{
				new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3)
		};
		Assert.assertArrayEquals(e.getContent().toArray(), tab);

		a.getContent().get(0).setAge(19);
		OPersonne[] tab2 =
		{
				new OPersonne(22, "Benjamin", 2), new OPersonne(30, "Massimo", 3), new OPersonne(19, "Clement", 1)
		};
		Assert.assertArrayEquals(e.getContent().toArray(), tab2);

		a.getContent().get(0).setAge(17);
		Assert.assertArrayEquals(e.getContent().toArray(), tab);
	}

	@Test
	public void testReificationOnApply() {
		// Test Apply
		Function<OPersonne, OPersonne> func = (element) -> {
			return new OPersonne(element.getAge() * 2, element.getName(), element.getNumero());
		};
		ICollection<OPersonne> e = this.a.apply(func);

		OPersonne[] tab =
		{
				new OPersonne(18 * 2, "Clement", 1), new OPersonne(22 * 2, "Benjamin", 2), new OPersonne(30 * 2, "Massimo", 3)
		};

		Assert.assertArrayEquals(e.getContent().toArray(), tab);

		a.getContent().get(0).setAge(40);
		OPersonne[] tab2 =
		{
				new OPersonne(40 * 2, "Clement", 1), new OPersonne(22 * 2, "Benjamin", 2), new OPersonne(30 * 2, "Massimo", 3),
		};
		Assert.assertArrayEquals(e.getContent().toArray(), tab2);
	}

	@Test
	public void testReificationOnSort() {
		// TODO
		// cf implementation
	}
	
	@Test
	public void test() {
		this.c = a.union(b);
		OPersonne[] tab =
		{
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(30, "Massimo", 3),
				new OPersonne(18, "Clement", 1),
				new OPersonne(22, "Benjamin", 2),
				new OPersonne(10, "Julien", 3)
		};
		Assert.assertArrayEquals(tab, this.c.getContent().toArray());
		
		ICollection<OPersonne> test = d.union(c);
		System.out.println(c);
		System.out.println(d);
		System.out.println(test);
		d.getContent().get(0).setAge(100);
		System.out.println(c);
		System.out.println(d);
		System.out.println(test);
	}

}
