package test.emn.fil.collection.sequence;

import org.junit.Test;

import emn.fil.collection.mutable.impl.Sequence;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.type.OInteger;

public class SequenceTest {

	private IOrdered<OInteger> a;
	private IOrdered<OInteger> b;
	private ICollection<OInteger> c;

	public SequenceTest() {
		this.a = new Sequence<OInteger>();
		this.b = new Sequence<OInteger>();

		this.a.add(0, new OInteger(1));
		this.a.add(1, new OInteger(2));
		this.a.add(2, new OInteger(2));

		this.b.add(0, new OInteger(4));
		this.b.add(1, new OInteger(5));
		this.b.add(2, new OInteger(6));
	}

	@Test
	public void testUnion() {
		this.c = a.union(b);
	}

}