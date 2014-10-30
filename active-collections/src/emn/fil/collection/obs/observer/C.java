package emn.fil.collection.obs.observer;

import java.util.List;

import emn.fil.collection.obs.event.EventCollection;

public class C<T> implements Observer<T> {
	private List<T> content;

	public C(List<T> content) {
		super();
		this.content = content;
	}

	@Override
	public void update(T element, EventCollection event) {
		switch (event) {
		case ADD:
			this.getContent().add(element);
			break;
		case REMOVE:
			// TODO we need to verify the performance for this;
			this.getContent().remove(element);
			break;
		default:
			break;
		}

	}

	private List<T> getContent() {
		return content;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("content : { ");
		for (T element : content) {
			sb.append(element + " ; ");
		}
		sb.append(" } ");
		return sb.toString();
	}
}
