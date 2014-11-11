package test.emn.fil.collection.set;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import test.emn.fil.collection.object.Personne;
import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class SetTest {

	@Test
	public void testSortWithoutFunction() {
		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(10);
				add(2);
				add(1);
			}
		});

		AbstractImmutableCollection<Integer> b = a.sort();
		Assert.assertArrayEquals(new Integer[] { 1, 2, 10 }, b.getContent().toArray());
	}

	@Test
	public void testSortInteger() {
		final AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(10);
				add(2);
				add(1);
			}
		});
		final AbstractImmutableCollection<Integer> b = a.sort((x, y) -> x - y);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 10 }, b.getContent().toArray());
		a.add(9);
		Assert.assertArrayEquals(new Integer[] { 1, 2, 9, 10 }, b.getContent().toArray());
	}

	@Test
	public void testSortPersonne() {
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
