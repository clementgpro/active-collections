package test.emn.fil.collection.bag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;

public class BagRemoveTest {

	private AbstractCollection<Integer> a;

	public BagRemoveTest() {
		this.a = new Bag<Integer>();
	}

	@Before
	public void setUp() throws Exception {
		this.a.add(1);
		this.a.add(2);
		this.a.add(3);
		this.a.add(4);
		this.a.add(5);
	}

	@Test
	public void testRemove1() {
		this.a.remove(1);
		Assert.assertArrayEquals(new Integer[]{2, 3, 4, 5}, this.a.getContent().toArray());
	}
	
	@Test
	public void testRemoveAll() {
		this.a.remove(1);
		this.a.remove(2);
		this.a.remove(3);
		this.a.remove(4);
		this.a.remove(5);
		Assert.assertArrayEquals(new Integer[]{}, this.a.getContent().toArray());
	}

}
