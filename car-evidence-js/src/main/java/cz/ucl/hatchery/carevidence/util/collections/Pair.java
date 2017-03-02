package cz.ucl.hatchery.carevidence.util.collections;

/**
 * @author Filip Buchta
 */
public class Pair<T1, T2> {

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

	public Pair(final T1 item1, final T2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public static <T1, T2> Pair<T1, T2> valueOf(final T1 item1, final T2 item2) {
		return new Pair<T1, T2>(item1, item2);
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
		final Pair pair = (Pair) o;

		if (item1 != null ? !item1.equals(pair.item1) : pair.item1 != null) {
			return false;
		}
		if (item2 != null ? !item2.equals(pair.item2) : pair.item2 != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = item1 != null ? item1.hashCode() : 0;
		result = 31 * result + (item2 != null ? item2.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Pair{" + "item1=" + item1 + ", item2=" + item2 + '}';
	}
}
