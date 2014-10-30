package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject<T> {
	protected List<Observer<T>> observers;

	public Subject() {
		this.observers = new ArrayList<Observer<T>>();
	}

	public void addObserver(Observer<T> c) {
		this.observers.add(c);
	}

	public void notify(T element, EventCollection event) {
		for (Observer<T> o : observers) {
			o.update(element, event);
		}
	}
}
