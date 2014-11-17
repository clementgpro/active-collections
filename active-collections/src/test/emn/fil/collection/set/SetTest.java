package test.emn.fil.collection.set;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.obs.type.OInteger;
import test.emn.fil.collection.object.OPersonne;

public class SetTest {

	@Test
	public void testSortWithoutFunction() {
		final AbstractCollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(10));
				add(new OInteger(2));
				add(new OInteger(1));
			}
		});
		final AbstractCollection<OInteger> b = a.sort();
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(10) }, b.getContent().toArray());
	}

	@Test
	public void testSortOInteger() {
		final AbstractCollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(10));
				add(new OInteger(2));
				add(new OInteger(1));
			}
		});
		final AbstractCollection<OInteger> b = a.sort((x, y) -> x.getValue() - y.getValue());
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
		final AbstractCollection<OPersonne> a = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(mamadou);
				add(benjamin);
				add(clement);
			}
		});
		final AbstractCollection<OPersonne> b = a.sort((p1, p2) -> p1.getAge() - p2.getAge());
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, mamadou }, b.getContent().toArray());
		a.add(jose);
		Assert.assertArrayEquals(new OPersonne[] { clement, benjamin, jose, mamadou }, b.getContent().toArray());
	}

}
