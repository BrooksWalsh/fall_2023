import java.util.ArrayList;

/**
 * Class BST to model a binary search tree
 * The values in the tree are sorted using the natural ordering
 */
public class BST<E extends Comparable<E>> {
	// Data members
	private TreeNode root;
	private int size;

	/**
	 * Inner class to model the nodes of the tree
	 */
	private class TreeNode {
		E value;
		TreeNode left;
		TreeNode right;

		/**
		 * Constructor of the inner class
		 * 
		 * @param the initial value of the node
		 */
		TreeNode(E val) {
			value = val;
			left = right = null;
		}
	}

	/**
	 * Default constructor
	 * creates an empty tree
	 */
	public BST() {
		root = null;
		size = 0;
	}

	/**
	 * Method size
	 * 
	 * @return the number of nodes in the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Method isEmpty
	 * 
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Method clear
	 * reset the tree to an empty tree
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Search method version 1
	 * 
	 * @param value being searched in the tree
	 * @return true if value is found, false otherwise
	 */
	public boolean contains(E value) {
		TreeNode node = root;
		while (node != null) {
			if (value.compareTo(node.value) < 0)
				node = node.left;
			else if (value.compareTo(node.value) > 0)
				node = node.right;
			else
				return true;
		}
		return false;
	}

	/**
	 * Search method version 2
	 * 
	 * @param value being searched in the tree
	 * @return the value found in the tree or null if not found
	 */
	public E find(E value) {
		TreeNode node = root;
		while (node != null) {
			if (value.compareTo(node.value) < 0)
				node = node.left;
			else if (value.compareTo(node.value) > 0)
				node = node.right;
			else
				return node.value;
		}
		return null;
	}

	/**
	 * Method to add a new node to the tree
	 * 
	 * @param value to be added to the tree
	 * @return true if value is added to the tree, false value already in the tree
	 */
	public boolean add(E value) {
		if (root == null)
			root = new TreeNode(value);
		else {
			TreeNode parent, node;
			parent = null;
			node = root;
			while (node != null) {
				parent = node;
				if (value.compareTo(node.value) < 0) {
					node = node.left;
				} else if (value.compareTo(node.value) > 0) {
					node = node.right;
				} else
					return false;
			}
			if (value.compareTo(parent.value) < 0)
				parent.left = new TreeNode(value);
			else
				parent.right = new TreeNode(value);
		}
		size++;
		return true;
	}

	/**
	 * Method remove to remove a value from the tree
	 * 
	 * @param value to be removed from the tree
	 * @return true if value was found and removed, false if value was not found
	 */
	public boolean remove(E value) {
		TreeNode parent, node;
		parent = null;
		node = root;
		int iterations = 0;
		while (node != null) {
			iterations++;
			if (value.compareTo(node.value) < 0) {
				parent = node;
				node = node.left;
			} else if (value.compareTo(node.value) > 0) {
				parent = node;
				node = node.right;
			} else
				break;
		}
		if (node == null)
			return false;

		// Case 1: node has no children
		if (node.left == null && node.right == null) {
			if (parent == null) { // delete root
				root = null;
			} else {
				changeChild(parent, node, null);
			}
		}
		// case 2: one right child
		else if (node.left == null) {
			if (parent == null) {
				root = node.right;
			} else {
				changeChild(parent, node, node.right);
			}
		}
		// case 2: one left child
		else if (node.right == null) {
			if (parent == null) {
				root = node.left;
			} else {
				changeChild(parent, node, node.left);
			}
		}
		// Case 3: node has two children
		else {
			TreeNode rightMostParent = node;
			TreeNode rightMost = node.left;
			while (rightMost.right != null) {
				iterations++;
				rightMostParent = rightMost;
				rightMost = rightMost.right;
			}
			node.value = rightMost.value;
			changeChild(rightMostParent, rightMost, rightMost.left);
		}
		size--;
		return true;
	}

	/**
	 * Method changeChild to update the links between the nodes
	 * (used by remove)
	 * 
	 * @param parent   node of the node to remove
	 * @param node     to be removed
	 * @param newChild the node to replace node as the child of parent
	 */
	private void changeChild(TreeNode parent,
			TreeNode node,
			TreeNode newChild) {
		if (parent.left == node)
			parent.left = newChild;
		else
			parent.right = newChild;
	}

	/**
	 * inorder method
	 * prints the nodes of the tree using the inorder traversal
	 */
	public void inorder() {
		inorder(root);
	}

	/**
	 * Helper method for inorder
	 * 
	 * @param node at which the traversal starts
	 */
	private void inorder(TreeNode node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.value + " ");
			inorder(node.right);
		}
	}

	/**
	 * preorder method
	 * prints the nodes of the tree using the preorder traversal
	 */
	public void preorder() {
		preorder(root);
	}

	/**
	 * Helper method for preorder
	 * 
	 * @param node at which the traversal starts
	 */
	private void preorder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}

	/**
	 * postorder method
	 * prints the nodes of the tree using the postorder traversal
	 */
	public void postorder() {
		postorder(root);
	}

	/**
	 * Helper method for postorder
	 * 
	 * @param node at which the traversal starts
	 */
	private void postorder(TreeNode node) {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.value + " ");
		}
	}

	/**
	 * toList method
	 * 
	 * - Final exam: Recursive method traverses the values of the BST nodes in
	 * order.
	 * 
	 * @return an arraylist with the nodes of the tree using the inorder traversal
	 */
	// Time Complexity: O(n)
	// - The BST toList method must reach every node in the tree.
	public ArrayList<E> toList() {
		ArrayList<E> output = new ArrayList<>();
		TreeNode currentNode = root;

		toList(output, currentNode);
		return output;
	}

	// toList helper method
	// WOW: I did preorder instead of inorder...
	public void toList(ArrayList<E> list, TreeNode node) {
		if (node != null) {
			list.add(node.value);
			toList(list, node.left);
			toList(list, node.right);
		}
	}
}
