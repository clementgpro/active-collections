package emn.fil.collection.obs.type;

import emn.fil.collection.obs.type.OAbstract;

public class OInteger extends OAbstract {

	private int value;

	public OInteger(final int number) {
		this.value = number;
	}

	public int getValue() {
		return value;
	}

	public void setNumber(int value) {
		super.beforeSet();
		this.value = value;
		super.afterSet();
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public OInteger copy() {
		return new OInteger(this.value);
	}

	public boolean equals(Object o) {
		if (o instanceof OInteger && ((OInteger) o).getValue() == this.value)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int compareTo(OAbstract arg0) {
		return this.value - ((OInteger)arg0).getValue();
	}
}
