package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class MyLinkedList<E> extends MyAbstractList<E> {
	private int size = 0;
	private Node<E> first;
	private Node<E> last;

	@Override
	public boolean add(E e) {
		// TODO implement this operation (part A)

		Node<E> addNode = new Node<>(e);
		Node<E> current = first;

		// if the list is empty
		if (current == null) {
			first = addNode;
			last = addNode;
		} else {
			while (current.next != null) {
				current = current.next;
			}
			current.next = addNode;
			last = addNode;
		}
		size++;
		return true;

		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		// TODO implement this operation (part B)

		Node<E> current = first;

		while (current != null && !current.elem.equals(o)) {
			current = current.next;
		}

		return current != null;

		// throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		// TODO implement this operation (part C)

		if (!contains(o)) {
			return false;
		}

		Node<E> current = first;
		Node<E> previous = new Node<E>(null, current);

		while (current != null && !current.elem.equals(o)) {
			previous = current;
			current = current.next;
		}

		if (current != null) {
			if (previous != null) {
				previous.next = current.next;
				if (current == last) {
					last = previous;
				}
			} else {
				first = current.next;

			}
		}

		return true;
		// throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		// TODO implement this operation (part D)
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	private static class Node<E> {
		private final E elem;
		private Node<E> next;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(Integer.valueOf(1));
		System.out.println(Arrays.toString(list.toArray()));
	}
}
