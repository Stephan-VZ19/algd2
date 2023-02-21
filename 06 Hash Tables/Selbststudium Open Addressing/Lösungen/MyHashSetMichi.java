package ch.fhnw.algd2.hashing;


import java.util.*;

/**
 * Hashset mit Open Addressing Kollisionsbehandlung
 */
public class MyHashSetMichi<E> implements Set<E> {

    private static final Object SENTINEL = new Object();

    private Object[] table;
    private int size = 0;

    public MyHashSetMichi(int minCapacity) {
        if (minCapacity < 4) {
            throw new IllegalArgumentException("Min Capacity must be at least 4");
        }
        // Aufgabe 1
        int capacity = 4;
        while (capacity < minCapacity) {
            capacity <<= 1;
        }
        table = new Object[capacity];
    }

    private int index(Object element) {
        // Aufgabe 2
        return (element.hashCode() & (table.length - 1));
    }

    private int step(Object o) {
        // Aufgabe 3
        int stepSize = 1 + (o.hashCode() & table.length - 1);
        return stepSize % 2 == 0 ? stepSize - 1 : stepSize;
    }

    @Override
    public boolean contains(Object o) {
        Objects.requireNonNull(o);
        int index = indexOf(o);
        return index != -1;
    }

    /**
     * Finds index of given element. Returns -1 if not found
     *
     * @param o Object to be found
     * @return index of element equal to the given parameter o or -1 if not found
     */
    private int indexOf(Object o) {
        int index = index(o);
        int step = step(o);
        int counter = 0;
        while (table[index] != null &&
                !table[index].equals(o) &&
                counter != table.length) {
            index = (index + step) & (table.length - 1);
            ++counter;
        }
        return table[index] == null || counter == table.length ? -1 : index;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public Object[] copyOfArray() {
        return Arrays.copyOf(table, table.length);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);

        if (contains(e)) {
            return false;
        }

        if (size == table.length) {
            throw new IllegalStateException("Can not add, is full");
        }

        // Aufgabe 4
        int index = index(e);
        int step = step(e);

        // No iteration counter because of size == table.length check: At least
        // one free place
        while (table[index] != null && table[index] != SENTINEL) {
            index = (index + step) & (table.length - 1);
        }
        table[index] = e;
        ++size;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean hasChanged = false;
        for (E elem : c) {
            if (add(elem)) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            if (remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        table[index] = SENTINEL;
        --size;
        return true;
    }

    @Override
    public void clear() {
        table = new Object[table.length];
        size = 0;
    }

}
