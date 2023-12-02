/**
 * BST class for comparing data structures in ALA_10
 */
public class BST<E extends Comparable<E>> {
    private TreeNode root;
    private int size;
    public static int containsIterations, addIterations, removeIterations;

    /**
     * Inner-class: TreeNode
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
     * Default BST constructor.
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Getter method for number of elements in the BST.
     * 
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Determine if the BST is empty.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Contains method for BST searches for given node.
     * 
     * @param value
     * @return boolean
     */
    public boolean contains(E value) {
        if (root == null) {
            return false;
        }
        containsIterations = 0;
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
     * Add method for BST will add a node in the correct place to maintain
     * structure.
     * 
     * @param value
     * @return boolean
     */
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
     * Remove method for BST removes a node and maintains the current structure.
     * 
     * @param value
     * @return boolean
     */
    public boolean remove(E value) {
        TreeNode parent, node;
        parent = null;
        node = root;
        removeIterations = 0;
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
            // copy the value of rigthMost to node
            node.value = rightMost.value;
            // delete rigthMost
            if (rightMostParent.left == rightMost)
                rightMostParent.left = rightMost.left;
            else
                rightMostParent.right = rightMost.left;
        }
        size--;
        return true;
    }

    /**
     * Inorder traversal and printing of the BST.
     * - Left, Value, Right
     */
    public void inorder() {
        inorder(root);
    }

    // helper method
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    /**
     * Preorder traversal and printing of the BST.
     * - Value, Left, Right
     */
    public void preorder() {
        preorder(root);
    }

    // helper method
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Postorder traversal and printing of the BST
     * - Left, Right, Value
     */
    public void postorder() {
        postorder(root);
    }

    // helper method
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
}
