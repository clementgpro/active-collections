package test.emn.fil.collection.object;

public class TestingClass {
	private int age;
	private String name;
	private int numero;
	
	public TestingClass(int age, String name, int numero) {
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
		if (o instanceof TestingClass //
				&& ((TestingClass)o).getAge() == this.age //
				&& ((TestingClass)o).getName().equals(this.name) //
				&& ((TestingClass)o).getNumero() == this.numero) {
			return true;
		} else {
			return false;
		}
	}
}
