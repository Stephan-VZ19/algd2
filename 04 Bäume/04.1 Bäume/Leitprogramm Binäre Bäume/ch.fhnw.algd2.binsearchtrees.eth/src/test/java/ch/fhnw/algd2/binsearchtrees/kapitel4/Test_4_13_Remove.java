package ch.fhnw.algd2.binsearchtrees.kapitel4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Test_4_13_Remove {


	@Test
	public void remove_keyDoesNotExist_unchangedInorder() {
		int[] expected = new int[]{3, 3, 5, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 25, 30, 30, 40, 40, 40, 40};
		this.removeAndExpect(22, expected);
	}

	@Test
	public void remove_nodeWithKeyHasOnlyLeftChild() {
		int[] expected = new int[]{3, 3, 5, 10, 10, 11, 12, 13, 14, 16, 17, 18, 20, 25, 30, 30, 40, 40, 40, 40};
		this.removeAndExpect(15, expected);
	}

	@Test
	public void remove_nodeWithKeyHasOnlyRightChild() {
		int[] expected = new int[]{3, 3, 5, 10, 10, 11, 12, 13, 14, 15, 17, 18, 20, 25, 30, 30, 40, 40, 40, 40};
		this.removeAndExpect(16, expected);
	}

	@Test
	public void remove_hasDuplicateWithNoChildren() {
		int[] expected = new int[]{5, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 25, 30, 30, 40, 40, 40, 40};
		this.removeAndExpect(3, expected);
	}

	@Test
	public void remove_hasDuplicateWithOneChild() {
		int[] expected = new int[]{3, 3, 5, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 25, 40, 40, 40, 40};
		this.removeAndExpect(30, expected);
	}
	@Test
	public void remove_hasDuplicateWithTwoChildren() {
		int[] expected = new int[]{3, 3, 5, 11, 12, 13, 14, 15, 16, 17, 18, 20, 25, 30, 30, 40, 40, 40, 40};
		this.removeAndExpect(10, expected);
	}
	@Test
	public void remove_keyExistsSeveralTimes() {
		int[] expected = new int[]{3, 3, 5, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 25, 30, 30};
		this.removeAndExpect(40, expected);
	}

	private void removeAndExpect(int key, int[] expectedInorder) {
		BinSearchTree root = sampleTree();
		root = root.delete(root, key );

		int[] actual = inorderTraversal(root);
		assertArrayEquals(expectedInorder, actual);
	}

	private int[] inorderTraversal(BinSearchTree root) {
		List<Integer> l = new ArrayList<>();
		inorderTraversal(root, l);
		int[] arr = new int[l.size()];
		int i = 0;
		for (int x : l)
			arr[i++] = x;
		return arr;
	}

	private void inorderTraversal(BinSearchTree root, List<Integer> l) {
		if (root != null) {
			inorderTraversal(root.getLeft(), l);
			l.add(root.getKey());
			inorderTraversal(root.getRight(), l);
		}
	}

	/*                        20
	 *         10a                           30a
	 *  3a            15               25          30b
	 *     5       14     16                           40a
	 *    3b    10b         17                            40b
	 *              12        18
	 *          11      13
	 */
	private BinSearchTree sampleTree() {
		BinSearchTree n_20 = new BinSearchTree(20);
		BinSearchTree n_10a = new BinSearchTree(10);
		BinSearchTree n_30a = new BinSearchTree(30);
		BinSearchTree n_3a = new BinSearchTree(3);
		BinSearchTree n_15 = new BinSearchTree(15);
		BinSearchTree n_16 = new BinSearchTree(16);
		BinSearchTree n_17 = new BinSearchTree(17);
		BinSearchTree n_18 = new BinSearchTree(18);
		BinSearchTree n_25 = new BinSearchTree(25);
		BinSearchTree n_30b = new BinSearchTree(30);
		BinSearchTree n_5 = new BinSearchTree(5);
		BinSearchTree n_14 = new BinSearchTree(14);
		BinSearchTree n_40a = new BinSearchTree(40);
		BinSearchTree n_3b = new BinSearchTree(3);
		BinSearchTree n_10b = new BinSearchTree(10);
		BinSearchTree n_40b = new BinSearchTree(40);
		BinSearchTree n_40c = new BinSearchTree(40);
		BinSearchTree n_40d = new BinSearchTree(40);
		BinSearchTree n_12 = new BinSearchTree(12);
		BinSearchTree n_11 = new BinSearchTree(11);
		BinSearchTree n_13 = new BinSearchTree(13);


		n_20.setLeft(n_10a);
		n_20.setRight(n_30a);

		n_10a.setLeft(n_3a);
		n_10a.setRight(n_15);
		n_30a.setLeft(n_25);
		n_30a.setRight(n_30b);

		n_3a.setRight(n_5);
		n_15.setLeft(n_14);
		n_15.setRight(n_16);
		n_30b.setRight(n_40a);

		n_5.setLeft(n_3b);
		n_14.setLeft(n_10b);
		n_16.setRight(n_17);
		n_40a.setRight(n_40b);

		n_10b.setRight(n_12);
		n_17.setRight(n_18);
		n_40b.setRight(n_40c);

		n_12.setLeft(n_11);
		n_12.setRight(n_13);
		n_40c.setRight(n_40d);

		return n_20;
	}

}
