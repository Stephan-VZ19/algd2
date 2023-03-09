package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class SortedSet<E extends Comparable<? super E>> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;

	private int size = 0;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedSet(int capacity) {
		data = (E[])new Comparable[capacity];
	}

	@Override
	public boolean add(E e) {
		// TODO implement unless collection shall be immutable

		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			return false;
		}

		if (size == data.length) {
			throw new IllegalStateException();
		}
		// Index i, where the element should be added, sorted
		int i = 0;
		while (i < size && e.compareTo(data[i]) > 0) i++;

		if (i == size && size < data.length) {
			data[size] = e;
			size++;
			return true;
		} else if (i == size && size == data.length) {
			if (contains(e)) {
				return false;
			}
			throw new UnsupportedOperationException();
		} else if (i < size) {
			E[] dataTemp = (E[])new Comparable[data.length];
			// everything before index i
			for (int j=0; j<i; j++) {
				dataTemp[j] = data[j];
			}
			dataTemp[i] = e;
			// everything after index i
			for (int k=i+1; k<data.length; k++) {
				dataTemp[k] = data[k-1];
			}
			data = dataTemp;
			size++;
			return true;
		}

		return false;

		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		// TODO implement unless collection shall be immutable


		if (o == null) {
			throw new NullPointerException();
		}
		if (!(contains(o))) {
			return false;
		}
		int i = 0;
		while (i < size && ((E)o).compareTo(data[i]) > 0 ) i++;

		if (i == size-1) {
			data[i] = null;
			size--;
			return true;
		} else if (i < size-1) {
			E[] dataTemp = (E[])new Comparable[data.length];
			for (int j=0; j<i; j++) {
				dataTemp[j] = data[j];
			}
			for (int k=i; k+1<size; k++) {
				dataTemp[k] = data[k+1];
			}
			data = dataTemp;
			size--;
			return true;
		}
		return false;
		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		// TODO must be implemented

		if (o == null) {
			throw new NullPointerException();
		}

		int i = 0;
		while (i < size && !o.equals(data[i])) i++;

		return i < size;

		// throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		// TODO must be implemented

		return size;
	}

	public static void main(String[] args) {
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		bag.add(3);
		bag.remove(2);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
