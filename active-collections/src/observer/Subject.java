package observer;

public abstract class Subject<T> {
	protected Observer<T> c;

	public void link(Observer<T> c) {
		this.c = c;
	}

	public void notifyC(T element) {
		this.c.update(element);
	}
}
