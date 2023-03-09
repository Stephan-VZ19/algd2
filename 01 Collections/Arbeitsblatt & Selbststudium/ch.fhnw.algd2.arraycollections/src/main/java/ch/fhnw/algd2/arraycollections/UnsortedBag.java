package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class UnsortedBag<E> extends AbstractArrayCollection<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public UnsortedBag() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedBag(int capacity) {
		data = (E[])new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		// TODO implement unless collection shall be immutable

		if (e == null) {
			throw new NullPointerException();
		}

		if (size == data.length) {
			throw new IllegalStateException();
		}

		int i = 0;
		while (i < data.length && data[i] != null) i++;

		data[i] = e;
		size++;
		return true;

		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		// TODO implement unless collection shall be immutable

		if (o == null) {
			throw new NullPointerException();
		}

		if (!(contains(o) || size == 0)) {
			return false;
		}

		// find first index where the element is
		int i = 0;
		while (i < data.length && !o.equals(data[i])) i++;

		if (i == size-1) {
			data[i] = null;
			size--;
		} else {
			while (i < size-1) {
				data[i] = data[i+1];
				i++;
			}
			data[i] = null;
			size--;
		}
		return true;

		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		// TODO must be implemented

		if (o == null) {
			throw new NullPointerException();
		}

		int i = 0;
		while (i < data.length && !o.equals(data[i])) i++;

		return i < data.length;

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
		UnsortedBag<Integer> bag = new UnsortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		bag.add(3);
		bag.add(4);
		bag.add(5);
		bag.remove(3);
		bag.remove(4);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
