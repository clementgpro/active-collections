active-collections
==================

Implementation of active collections.
This project is based on the article: Active Operations on Collections. Olivier Beaudoux, Arnaud Blouin, Olivier Barais, Jean-Marc Jezequel. MoDELS 2010. Springer. 2010. http://hal.archives-ouvertes.fr/docs/00/54/27/63/PDF/Beaudoux10a.pdf

Some examples about what you can do using active-collections :
	ICollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
		{
			add(new OInteger(1));
			add(new OInteger(2));
			add(new OInteger(3));
		}
	});
			
	ICollection<OInteger> b = new Bag<OInteger>(new ArrayList<OInteger>() {
		{
			add(new OInteger(4));
			add(new OInteger(5));
			add(new OInteger(6));
		}
	});
	ICollection<OInteger> c = a.union(b); // returns Bag [1,2,3,4,5,6]
	ICollection<OInteger> d = a.union(c); // returns Bag [1,2,3,1,2,3,4,5,6]
