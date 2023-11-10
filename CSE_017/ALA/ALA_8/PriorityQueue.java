import java.util.Comparator;

/**
 * PriorityQueue class is a generic data structure that uses a MinHeap to order
 * its elements. Adding elements is done with the offer() method which will also
 * re-structure the binary tree to remain a MinHeap. Removing elements using the
 * poll() method will only ever remove the first (root & smallest) element in
 * the tree and re-structures accordingly.
 * 
 * @since 2023-11-9
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class PriorityQueue<E> {
    private MinHeap<E> elements;

    /**
     * Default constructor of the PriorityQueue. Instantiates an empty MinHeap that
     * has no argument therefore will use natural ordering to maintain its
     * tree-structure.
     */
    public PriorityQueue() {
        elements = new MinHeap<>();
    }

    /**
     * 1-arg constructor of the PriorityQueue. Instantiates an empty MinHeap that is
     * passed the Comparator object given to this constructor. This will allow for a
     * MinHeap with ordering that is different from the natural ordering of
     * its elements.
     * 
     * @param comp Comparator object for ordering the PriorityQueue
     */
    public PriorityQueue(Comparator<E> comp) {
        elements = new MinHeap<>(comp);
    }

    /**
     * Contains method for a PriorityQueue simply calls the contains method for the
     * MinHeap.
     * 
     * @param element
     * @return boolean
     */
    public boolean contains(E element) {
        return elements.contains(element);
    }

    // Time Complexity: O(log(n))
    /**
     * The offer method acts as the add method for the PriorityQueue. It calls the
     * add method from the MinHeap so that the tree remains a structured MinHeap.
     * 
     * @param element
     */
    public void offer(E element) {
        elements.add(element);
    }

    // Time Complexity: O(log(n))
    /**
     * Poll returns and removes the root of the MinHeap, which acts as the first
     * element in the PriorityQueue.
     * 
     * @return E first/lowest/root element
     */
    public E poll() {
        return elements.remove();
    }

    // Time Complexity: O(1)
    /**
     * Peek returns the root of the MinHeap without removing it.
     * 
     * @return E first/lowest/root element
     */
    public E peek() {
        return elements.getRoot();
    }

    /**
     * Uses the iterative list.isEmpty() method from the ArrayList structure.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns the number of elements in the PriorityQueue.
     * 
     * @return int size
     */
    public int size() {
        return elements.size();
    }

    /**
     * Clears the PriorityQueue by calling MinHeap's clear method which simply
     * resets the underlying ArrayList to an empty list.
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Uses the ArrayList toString method for a nicely formatted String.
     * 
     * @return Formatted String
     */
    public String toString() {
        return elements.toString();
    }
}
