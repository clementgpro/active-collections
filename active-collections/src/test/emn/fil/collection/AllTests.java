package test.emn.fil.collection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.emn.fil.collection.bag.BagAddTest;
import test.emn.fil.collection.bag.BagRemoveTest;
import test.emn.fil.collection.bag.BagTest;
import test.emn.fil.collection.bag.BagWithPersonneTest;
import test.emn.fil.collection.orderedset.OrderedSetTest;
import test.emn.fil.collection.sequence.SequenceTest;
import test.emn.fil.collection.set.SetTest;

@RunWith(Suite.class)
@SuiteClasses({ BagAddTest.class, BagRemoveTest.class, BagTest.class, BagWithPersonneTest.class,
	OrderedSetTest.class, SequenceTest.class, SetTest.class })
public class AllTests {

}