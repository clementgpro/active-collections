package emn.fil.collection.obs.type;

public class OPersonne extends OAbstract {
	private int age;
	private String name;
	private int numero;

	public OPersonne() {
	}

	public OPersonne(int age, String name, int numero) {
		super();
		this.age = age;
		this.name = name;
		this.numero = numero;
	}

	public OPersonne copy() {
		final OPersonne newPersonne = new OPersonne(this.getAge(), this.getName(), this.getNumero());
		return newPersonne;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		super.beforeSet();
		this.age = age;
		super.afterSet();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		super.beforeSet();
		this.name = name;
		super.afterSet();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		super.beforeSet();
		this.numero = numero;
		super.afterSet();
	}

	public boolean equals(Object o) {
		if (o instanceof OPersonne //
				&& ((OPersonne) o).getAge() == this.age //
				&& ((OPersonne) o).getName().equals(this.name) //
				&& ((OPersonne) o).getNumero() == this.numero)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String toString() {
		return age + " " + name + " " + numero;
	}

	@Override
	public int compareTo(OAbstract arg0) {
		return this.getAge() - ((OPersonne) arg0).getAge();
	}

}
