import ch.fhnw.algd2.treeeditor.base.Tree;

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Dominik Gruntz
 */
class BinarySearchTree<K extends Comparable<? super K>, E> implements
		Tree<K, E> {
	private Node<K, E> root = null;
	private int nodeCount = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Tree#height()
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Return the height of node t, or -1, if null.
	 */
	private int height(Node<K, E> t) {
		if (t != null) {
			int hl = height(t.left), hr = height(t.right);
			return hl >= hr ? hl + 1 : hr + 1;
		} else
			return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Tree#getRoot()
	 */
	public Tree.Node<K, E> getRoot() {
		return root;
	}

	/**
	 * Searches an item in the tree.
	 * 
	 * @param key
	 *            the key to search for.
	 * @return the matching item or null if not found.
	 */
	public E search(K key) {
		return search(root, key);
	}

	private E search(Node<K, E> p, K key) {
		if (p == null)
			return null;
		else {
			int c = key.compareTo(p.key);
			if (c == 0)
				return p.element;
			else if (c < 0)
				return search(p.left, key);
			else
				return search(p.right, key);
		}
	}

	/**
	 * number of nodes in the tree
	 * 
	 * @return size of the tree.
	 */
	public int size() {
		return nodeCount;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Insert a value into the tree; if an element is already stored under the
	 * given key the element is replaced by the new one.
	 * 
	 * @param key
	 *            key with which the specified element is to be associated.
	 * @param element
	 *            element to be inserted into the tree.
	 */
	public void insert(K key, E element) {
		root = insert(root, key, element);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param p
	 *            the node that roots the tree.
	 * @param key
	 *            the key of the element to insert.
	 * @param element
	 *            the element to insert.
	 * @return the new root.
	 */
	private Node<K, E> insert(Node<K, E> p, K key, E element) {
		if (p == null) {
			nodeCount++;
			return new Node<K, E>(key, element);
		} else {
			int c = key.compareTo(p.key);
			if (c < 0) {
				p.left = insert(p.left, key, element);
			} else if (c > 0) {
				p.right = insert(p.right, key, element);
			} else {
				p.element = element;
			}
			p = rebalanceNode(p);
			return p;
		}
	}

	private Node<K, E> rebalanceNode(Node<K, E> p) {
		if (p == null) {
			return null;
		}
		int balance = height(p.right) - height(p.left);
		if (balance == 2) {
			int subBalance = height(p.right.right) - height(p.right.left);
			if (subBalance == -1) {
				return rotateRL(p);
			} // subBalance == 1 or 0
			else {
				return rotateL(p);
			}
		} else if (balance == -2) {
			int subBalance = height(p.left.right) - height(p.left.left);
			if (subBalance == 1) {
				return rotateLR(p);
			} // subBalance == -1 or 0
			else {
				return rotateR(p);
			}
		}
		// No rebalancing
		return p;
	}

	/**
	 * Remove Node with key <code>key</code> from the tree. Nothing is done if x
	 * is not found.
	 * 
	 * @param key
	 *            the key of the item to remove.
	 */
	public void remove(K key) {
		root = rebalanceNode(remove(root, key));
	}

	private Node<K, E> remove(Node<K, E> current, K key) {
		// Anker, nothing to remove
		if (current == null) {
			return null;
		}
		int c = key.compareTo(current.key);
		// Find in left tree
		if (c < 0) {
			current.left = remove(current.left, key);
		} // find in right tree
		else if (c > 0) {
			current.right = remove(current.right, key);
		}
		// Found
		else {
			// left is null (also matches if both are null)
			if (current.left == null) {
				--nodeCount;
				return current.right;
			} else if (current.right == null) {
				--nodeCount;
				return current.left;
			}
			Node<K, E> leftmost = current.right;
			while (leftmost.left != null) {
				leftmost = leftmost.left;
			}
			// Replace removed node
			leftmost.right = remove(current.right, leftmost.key);
			leftmost.left = current.left;
			current = leftmost;
		}
		if (current != null) {
			current = rebalanceNode(current);
		}
		return current;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		createExpression(root, sb);
		return sb.toString();
	}

	/*
	 * In-Order traversal, create expression in brackets
	 */
	private void createExpression(Node<K, E> current, StringBuilder sb) {
		if (current == null) {
			return;
		}
		sb.append("[");
		createExpression(current.left, sb);
		sb.append(current.key);
		createExpression(current.right, sb);
		sb.append("]");
	}

	private Node<K, E> rotateR(Node<K, E> node) {
		Node<K, E> nextRoot = node.left;
		node.left = nextRoot.right;
		nextRoot.right = node;
		return nextRoot;
	}

	private Node<K, E> rotateL(Node<K, E> node) {
		Node<K, E> nextRoot = node.right;
		node.right = nextRoot.left;
		nextRoot.left = node;
		return nextRoot;
	}

	private Node<K, E> rotateRL(Node<K, E> node) {
		node.right = rotateR(node.right);
		return rotateL(node);
	}

	private Node<K, E> rotateLR(Node<K, E> node) {
		node.left = rotateL(node.left);
		return rotateR(node);
	}

	static class Node<K extends Comparable<? super K>, E> implements
			Tree.Node<K, E> {
		K key;
		E element;
		Node<K, E> left, right;

		Node(K key) {
			this(key, null);
		}

		Node(K key, E element) {
			this.key = key;
			this.element = element;
		}

		Node(K key, E element, Node<K, E> left, Node<K, E> right) {
			this(key, element);
			this.left = left;
			this.right = right;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getKey()
		 */
		public K getKey() {
			return key;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getLeft()
		 */
		public Tree.Node<K, E> getLeft() {
			return left;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getRight()
		 */
		public Tree.Node<K, E> getRight() {
			return right;
		}
	}
}