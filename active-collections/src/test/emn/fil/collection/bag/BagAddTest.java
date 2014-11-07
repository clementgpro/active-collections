package test.emn.fil.collection.bag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class BagAddTest {

	private final AbstractCollection<Integer> a;

	public BagAddTest() {
		this.a = new Bag<Integer>();
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAdd1() {
		this.a.add(1);
		Assert.assertArrayEquals(new Integer[]{1}, this.a.getContent().toArray());
	}
	
	@Test
	public void testAddN() {
		this.a.add(1);
		this.a.add(2);
		this.a.add(3);
		this.a.add(4);
		this.a.add(5);
		Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, this.a.getContent().toArray());
	}
	
	@Test
	public void testAddBag() {
		this.a.add(1);
		this.a.add(2);
		this.a.add(3);
		this.a.add(3);
		this.a.add(2);
		this.a.add(4);
		this.a.add(2);
		this.a.add(5);
		this.a.add(1);
		this.a.add(5);
		this.a.add(5);
		Assert.assertArrayEquals(new Integer[]{1, 2, 3, 3, 2, 4, 2, 5, 1 ,5 ,5}, this.a.getContent().toArray());
	}

}
