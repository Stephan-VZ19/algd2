package ch.fhnw.algd2.heaptest;

/* Heap als Implementierung einer Priority Queue */
class HeapSolutionMichi_0<K> implements PriorityQueue<K> {
	private HeapNode<K>[] heap; // Array to store the heap elements
	private int size; // Position where to insert NEXT

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity
	 *            how many items the heap can store at most
	 */
	@SuppressWarnings("unchecked")
	HeapSolutionMichi_0(int capacity) {
		heap = new HeapNode[capacity];
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	@Override
	public boolean isFull() {
		return size == heap.length;
	}

	/**
	 * Make the heap (logically) empty.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		heap = new HeapNode[heap.length];
		size = 0;
	}

	/**
	 * Add to the priority queue, maintaining heap order. Duplicates and null
	 * values are allowed. Small values of the argument priority means high
	 * priority, Large values means low priority.
	 * 
	 * @param element
	 *            the item to insert
	 * @param priority
	 *            the priority to be assigned to the item element
	 * @exception QueueFullException
	 *                if the heap is full
	 */
	@Override
	public void add(K element, long priority) throws QueueFullException {
		if (isFull())
			throw new QueueFullException();
		heap[size] = new HeapNode<K>(element, priority);
		siftUp(size);
		size++;
	}

	/**
	 * Removes and returns the item with highest priority (smallest priority
	 * value) from the queue, maintaining heap order.
	 * 
	 * @return the item with highest priority (smallest priority value)
	 * @throws QueueEmptyException
	 *             if the queue is empty
	 */
	@Override
	public K removeMin() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException();
		K res = heap[0].element;
		size--;
		heap[0] = heap[size];
		heap[size] = null;
		siftDown(0);
		return res;
	}

	/**
	 * Internal method to let an element sift down in the heap.
	 * 
	 * @param current
	 *            the index at which the percolate begins
	 */
	private void siftDown(int current) {
		int child = indexOfSmallerChild(current);
		while (child < size && heap[current].priority > heap[child].priority) {
			swapElements(current, child);
			current = child;
			child = indexOfSmallerChild(child);
		}
	}

	private void swapElements(int a, int b) {
		HeapNode<K> n = heap[a];
		heap[a] = heap[b];
		heap[b] = n;
	}

	private int indexOfSmallerChild(int current) {
		int child = 2 * current + 1;
		if (child + 1 < size && heap[child].priority > heap[child + 1].priority) {
			child++;
		}
		return child;
	}

	/**
	 * Internal method to let an element sift up in the heap.
	 * 
	 * @param current
	 *            the index at which the percolate begins
	 */
	private void siftUp(int current) {
		int parent = parentIndex(current);
		while (current > 0 && heap[current].priority < heap[parent].priority) {
			swapElements(current, parent);
			current = parent;
			parent = parentIndex(parent);
		}
	}

	private int parentIndex(int current) {
		return (current - 1) / 2;
	}

	/**
	 * Erzeugt ein neues long[] Array und kopiert die Werte der Priorit�ten aus
	 * dem Heaparray dort hinein. Die Gr�sse des zur�ckgegebenen Arrays soll der
	 * Anzahl Elemente in der Queue entsprechen (= size()). An der Position 0
	 * soll die kleinste Priorit�t (= Priorit�t des Wurzelelementes) stehen.
	 * 
	 * @return Array mit allen Priorit�ten
	 */
	@Override
	public long[] toLongArray() {
		long[] l = new long[size];
		for (int i = 0; i < size; i++)
			l[i] = heap[i].priority;
		return l;
	}

	private static class HeapNode<K> {
		private final K element;
		private final long priority;

		HeapNode(K element, long priority) {
			this.element = element;
			this.priority = priority;
		}
	}
}
