package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Objects;

public class SortedBagSolutionMichael<E extends Comparable<? super E>> extends AbstractArrayCollection<E> {

	public static final int DEFAULT_CAPACITY = 100;

	private int size = 0;
	private final E[] data;

	public SortedBagSolutionMichael() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SortedBagSolutionMichael(int capacity) {
		data = (E[]) new Comparable[capacity];
	}

	@Override
	public boolean add(E e) {
		Objects.requireNonNull(e);
		checkRemainingCapacity();
		insertElement(e);
		size++;
		return true;
	}

	private void insertElement(E e) {
		int current = size;
		while (current > 0 && data[current - 1].compareTo(e) > 0) {
			data[current] = data[current - 1];
			current--;
		}
		data[current] = e;
	}

	private void checkRemainingCapacity() {
		if (size == data.length) {
			throw new IllegalStateException(
					"Can not add more elements than capacity");
		}
	}

	@Override
	public boolean remove(Object o) {
		Objects.requireNonNull(o);
		int index = indexOf(o);
		if (index < 0) {
			return false;
		}
		moveElementsLeft(index);
		// remove last Element (avoiding memory leak)
		data[size - 1] = null;
		--size;
		return true;
	}

	private void moveElementsLeft(int fromIndex) {
		for (int i = fromIndex; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
	}

	@Override
	public boolean contains(Object o) {
		Objects.requireNonNull(o);
		return indexOf(o) >= 0;
	}

	/**
	 * Looks for given object in the data
	 * 
	 * @param o
	 *            Object to look for
	 * @return position of element (>= 0), otherwise (-(insertion point) - 1).
	 */
	public int indexOf(Object o) {
		return Arrays.binarySearch(data, 0, size, o);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		SortedBagSolutionMichael<Integer> bag = new SortedBagSolutionMichael<Integer>();
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}

}
