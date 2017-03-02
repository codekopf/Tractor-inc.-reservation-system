package cz.ucl.hatchery.carevidence.util.collections;

/**
 * @author Filip Buchta
 */
public class Triplet<T1, T2, T3> {

	public T3 getItem3() {
		return item3;
	}

	public void setItem3(final T3 item3) {
		this.item3 = item3;
	}

	public T2 getItem2() {
		return item2;
	}

	public void setItem2(final T2 item2) {
		this.item2 = item2;
	}

	public T1 getItem1() {
		return item1;
	}

	public void setItem1(final T1 item1) {
		this.item1 = item1;
	}

	private T1 item1;
	private T2 item2;
	private T3 item3;

	public Triplet(final T1 item1, final T2 item2, final T3 item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		final Triplet triplet = (Triplet) o;

		if (item1 != null ? !item1.equals(triplet.item1) : triplet.item1 != null) {
			return false;
		}
		if (item2 != null ? !item2.equals(triplet.item2) : triplet.item2 != null) {
			return false;
		}
		if (item3 != null ? !item3.equals(triplet.item3) : triplet.item3 != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = item1 != null ? item1.hashCode() : 0;
		result = 31 * result + (item2 != null ? item2.hashCode() : 0);
		result = 31 * result + (item3 != null ? item3.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Triplet{" + "item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + '}';
	}
}
