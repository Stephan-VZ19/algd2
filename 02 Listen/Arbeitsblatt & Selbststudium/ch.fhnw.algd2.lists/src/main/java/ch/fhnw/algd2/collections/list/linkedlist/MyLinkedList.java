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
		Node<E> previous = null;

		while (current != null && !current.elem.equals(o)) {
			previous = current;
			current = current.next;
		}

		if (current != null) {
			if (previous != null) {  	// element is at the first place
				previous.next = current.next;
				if (current == last) {
					last = previous;
				}
			} else {     // element is at the last place
				if (current.next != null) {
					first = current.next;
					if (current == last) {
						last = previous;
					}
				} else {
					first = null;
					last = null;
				}
			}
		}

		size--;
		return true;
		// throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		// TODO implement this operation (part D)

		int counter = 0;
		Node<E> current = first;

		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		while (current.next != null && counter < index) {
			current = current.next;
			counter++;
		}

		return current.elem;

		// throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		// TODO implement this operation (part D)

		int counter = 0;
		Node<E> addNode = new Node<>(element);
		Node<E> current = first;
		Node<E> previous = null;

		if (index >= size+1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if ((size == 0 && index == 0) || index == size) {
			add(element);
		} else {
			while (current != null && counter < index) {
				previous = current;
				current = current.next;
				counter++;
			}

			if (current == null) {
				add(element);
			} else {
				if (!(previous == null)) {
					addNode.next = current;
					previous.next = addNode;
				} else { // list has only 1 element and dex is 0 or 1
					if (index == 0) {
						addNode.next = first;
						first = addNode;
					} else if (index == 1) {
						add(element);
					} else {
						throw new IndexOutOfBoundsException();
					}
				}
			}
		}

		size++;
		// throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		// TODO implement this operation (part D)

		int counter = 0;
		Node<E> current = first;
		Node<E> previous = null;

		if (index >= size+1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		while (current != null && counter < index) {
			previous = current;
			current = current.next;
			counter++;
		}

		if (!(previous == null) && current != null) {
			previous.next = current.next;
		} else if ((previous == null) && current != null) { // list has only 1 element and index is 0 or 1
			if (index == 0) {
				first = null;
				last = null;
			} else {
				throw new IndexOutOfBoundsException();
			}
		} else if (current == null) { // list is empty
			throw new IndexOutOfBoundsException();
		}

		size--;

		return current.elem;

		// throw new UnsupportedOperationException();
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
		list.add(1,Integer.valueOf(5));
		System.out.println(Arrays.toString(list.toArray()));
	}
}
