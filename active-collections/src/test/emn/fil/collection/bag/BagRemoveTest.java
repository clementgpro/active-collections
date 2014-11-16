package test.emn.fil.collection.bag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;
import test.emn.fil.collection.object.OInteger;

public class BagRemoveTest {

	private AbstractCollection<OInteger> a;

	public BagRemoveTest() {
		this.a = new Bag<OInteger>();
	}

	@Before
	public void setUp() throws Exception {
		this.a.add(new OInteger(1));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(3));
		this.a.add(new OInteger(4));
		this.a.add(new OInteger(5));
	}

	@Test
	public void testRemove1() {
		this.a.remove(new OInteger(1));
		Assert.assertArrayEquals(new OInteger[] { new OInteger(2), new OInteger(3), new OInteger(4), new OInteger(5) }, this.a.getContent().toArray());
	}

	@Test
	public void testRemoveAll() {
		this.a.remove(new OInteger(1));
		this.a.remove(new OInteger(2));
		this.a.remove(new OInteger(3));
		this.a.remove(new OInteger(4));
		this.a.remove(new OInteger(5));
		Assert.assertArrayEquals(new OInteger[] {}, this.a.getContent().toArray());
	}

}
