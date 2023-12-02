import java.util.Comparator;

/**
 * TreeSet class is a direct copy of the BST class we used for ALA_9. This
 * implementation adds two methods, first() and last(), and allows for the use
 * of a comparator object.
 * 
 * @since 2023-12-02
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class TreeSet<E extends Comparable<E>> {
    private TreeNode root;
    private int size;
    private Comparator<E> comp;

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
     * No-arg constructor of the TreeSet class creates null comparator.
     */
    public TreeSet() {
        root = null;
        size = 0;
        this.comp = null;
    }

    /**
     * 1-arg constructor of the TreeSet class uses comparator for ordering.
     * 
     * @param comp
     */
    public TreeSet(Comparator<E> comp) {
        root = null;
        size = 0;
        this.comp = comp;
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
            if (comp == null) {
                if (value.compareTo(node.value) < 0)
                    node = node.left;
                else if (value.compareTo(node.value) > 0)
                    node = node.right;
                else
                    return true;
            } else { // using comparator ordering
                if (comp.compare(value, node.value) < 0)
                    node = node.left;
                else if (comp.compare(value, node.value) > 0)
                    node = node.right;
                else
                    return true;
            }
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
            if (comp == null) {
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
            } else { // using comparator ordering
                while (node != null) {
                    addIterations++;
                    parent = node;
                    if (comp.compare(value, node.value) < 0) {
                        node = node.left;
                    } else if (comp.compare(value, node.value) > 0) {
                        node = node.right;
                    } else
                        return false;
                }
                if (comp.compare(value, parent.value) < 0)
                    parent.left = new TreeNode(value);
                else
                    parent.right = new TreeNode(value);
            }
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
            if (comp == null) {
                if (value.compareTo(node.value) < 0) {
                    parent = node;
                    node = node.left;
                } else if (value.compareTo(node.value) > 0) {
                    parent = node;
                    node = node.right;
                } else
                    break; // value found
            } else { // use comparator ordering
                if (comp.compare(value, node.value) < 0) {
                    parent = node;
                    node = node.left;
                } else if (comp.compare(value, node.value) > 0) {
                    parent = node;
                    node = node.right;
                } else
                    break; // value found
            }
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
     * Returns the first or the lowest element in the set. (Leftmost)
     * 
     * @return E
     * 
     *         Time Complexity(Average): O(log(n))
     *         Time Complexity(Sorted): O(n)
     */
    public E first() {
        TreeNode node = root;
        while (node.left != null) { // find leftmost
            node = node.left;
        }
        return node.value;
    }

    /**
     * Returns the last or highest element in the set. (Rightmost)
     * 
     * @return E
     * 
     *         Time Complexity(Average): O(log(n))
     *         Time Complexity(Sorted): O(n)
     */
    public E last() {
        TreeNode node = root;
        while (node.right != null) { // find rightmost
            node = node.right;
        }
        return node.value;
    }

    /**
     * Prints the values of the BST in the following order:
     * - Left, Value, Right
     * 
     * - Modified to print newline character for hw_9
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
            System.out.print(node.value + "\n");
            inorder(node.right);
        }
    }

    /**
     * Prints the values of the BST in the following order:
     * - Value, Left, Right
     * 
     * - Modified to print newline character for hw_9
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
            System.out.print(node.value + "\n");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Prints the values of the BST in the following order:
     * - Left, Right, Value
     * 
     * - Modified to print newline character for hw_9
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
            System.out.print(node.value + "\n");
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