package test.emn.fil.collection.object;

public class Personne {
	private int age;
	private String name;
	private int numero;
	
	public Personne(int age, String name, int numero) {
		super();
		this.age = age;
		this.name = name;
		this.numero = numero;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return age + " " + name + " " + numero;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Personne //
				&& ((Personne)o).getAge() == this.age //
				&& ((Personne)o).getName().equals(this.name) //
				&& ((Personne)o).getNumero() == this.numero) {
			return true;
		} else {
			return false;
		}
	}
}
