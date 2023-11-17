public class BST<E extends Comparable<E>> {
    private TreeNode root;
    private int size;

    // static counter data members
    public static int containsIterations, addIterations, removeIterations;

    /**
     * Inner-class defines Tree Nodes that represent data elements.
     */
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        TreeNode(E val) {
            value = val;
            left = right = null;
        }
    }

    /**
     * No-arg constructor of the BST class.
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Getter method for the size of the BST.
     * 
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the BST is empty, else otherwise.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the BST by resetting the root to null and size to 0.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns true if the value passed as an argument is within the BST.
     * 
     * @param value
     * @return
     */
    // Unsorted Time Complexity: O(nLog(n))
    // Sorted Time Complexity: O(n)
    public boolean contains(E value) {
        containsIterations = 0;
        if (root == null) {
            return false;
        }
        TreeNode node = root;
        while (node != null) {
            containsIterations++;
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
     * Adds an element into the BST and re-structures to retain it's balance.
     * 
     * @param value
     * @return boolean
     */
    // Unsorted Time Complexity: O(nLog(n))
    // Sorted Time Complexity: O(n)
    public boolean add(E value) {
        addIterations = 0;
        if (root == null)
            root = new TreeNode(value);
        else {
            TreeNode parent, node;
            parent = null;
            node = root;
            while (node != null) {
                addIterations++;
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
     * Removes an element from the BST and re-structures to retain it's balance.
     * 
     * @param value
     * @return
     */
    // Unsorted Time Complexity: O(nLog(n))
    // Sorted Time Complexity: O(n)
    public boolean remove(E value) {
        removeIterations = 0;
        TreeNode parent, node;
        parent = null;
        node = root;
        // Find value first
        while (node != null) {
            removeIterations++;
            if (value.compareTo(node.value) < 0) {
                parent = node;
                node = node.left;
            } else if (value.compareTo(node.value) > 0) {
                parent = node;
                node = node.right;
            } else
                break; // value found
        }
        if (node == null) // value not in the tree
            return false;
        // Case 1: node has no children
        if (node.left == null && node.right == null) {
            if (parent == null) { // delete root
                root = null;
                size = 0;
            } else if (parent.left == node)
                parent.left = null;
            else
                parent.right = null;
        } else if (node.left == null) {
            // case 2: node has one right child
            if (parent == null)
                root = node.right;
            else if (parent.left == node)
                parent.left = node.right;
            else
                parent.right = node.right;
        } else if (node.right == null) {
            // case 2: node has one left child
            if (parent == null)
                root = node.left;
            else if (parent.left == node)
                parent.left = node.left;
            else
                parent.right = node.left;
        }

        else {
            // case 3: node has two children
            TreeNode rightMostParent = node;
            TreeNode rightMost = node.left;
            // go right on the left subtree
            while (rightMost.right != null) {
                removeIterations++;
                rightMostParent = rightMost;
                rightMost = rightMost.right;
            }
            // copy the value of rightMost to node
            node.value = rightMost.value;
            // delete rightMost
            if (rightMostParent.left == rightMost)
                rightMostParent.left = rightMost.left;
            else
                rightMostParent.right = rightMost.left;
        }
        size--;
        return true;
    }

    /**
     * Prints the values of the BST in the following order:
     * - Left, Value, Right
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Helper method of inorder printing.
     * 
     * @param node
     */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    /**
     * Prints the values of the BST in the following order:
     * - Value, Left, Right
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Helper method of preorder printing.
     * 
     * @param node
     */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Prints the values of the BST in the following order:
     * - Left, Right, Value
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Helper method of postorder printing.
     * 
     * @param node
     */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }

    /**
     * Returns the height of the tree, implemented recursively
     * 
     * @return int height
     */
    // Time Complexity: O(n)
    public int height() {
        return height(root);
    }

    // height of tree helper method
    public int height(TreeNode node) {
        if (node == null) { // empty tree
            return 0;
        }

        if (node.left == null && node.right == null) { // leaf node base case
            return 1;
        }

        // recursive case
        int heightL = height(node.left);
        int heightR = height(node.right);
        int maximum = Math.max(heightL, heightR);
        return 1 + maximum;
    }

    /**
     * Returns true if the binary search tree is balanced, false otherwise. A
     * balanced tree if, for each node in the tree, the heights of the left subtree
     * and the right subtree differ by 1 at most.
     * 
     * @return boolean
     */
    // Time Complexity: O(n^2)
    public boolean isBalanced() {
        return isBalanced(root);
    }

    public boolean isBalanced(TreeNode node) {
        if (node == null) { // empty tree
            return true;
        }

        int heightL = height(node.left);
        int heightR = height(node.right);
        int diff = Math.abs(heightL - heightR);

        if (diff > 1) { // imbalanced tree at this node
            return false;
        }

        // recursive case
        return isBalanced(node.left) && isBalanced(node.right);
    }
}