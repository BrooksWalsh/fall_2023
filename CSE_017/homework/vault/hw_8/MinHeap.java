import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * Generic MinHeap Class
 */
public class MinHeap<E> {
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;
    private Comparator<E> comp;
    public static int addIterations, removeIterations;

    /**
     * Default Constructor
     */
    public MinHeap() { // uses natural ordering
        list = new ArrayList<>();
        comp = null;
    }

    /**
     * 1-arg constructor that allows passing a Comparator for ordering the MinHeap.
     * 
     * @param c
     */
    public MinHeap(Comparator<E> c) {
        list = new ArrayList<>();
        comp = c;
    }

    /**
     * Method size
     * 
     * @return the number of nodes in the heap
     */
    public int size() {
        return list.size();
    }

    /**
     * Method isEmpty
     * 
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Method to empty the heap
     */
    public void clear() {
        list.clear();
    }

    /**
     * Method toString
     * 
     * @return a formatted string containing the values of the nodes of the heap
     */
    public String toString() {
        return list.toString();
    }

    /**
     * Method getRoot
     * 
     * @return the value of the root
     */
    public E getRoot() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }

    /**
     * Method contains
     * 
     * @param value the value being searched in the heap
     * @return true if the value is found, false otherwise
     */
    public boolean contains(E value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value))
                return true;
        }
        return false;
    }

    /**
     * Method add
     * 
     * @param value to be added to the heap
     *              reconstructs the heap to keep the MinHeap properties
     */
    @SuppressWarnings("unchecked")
    public void add(E value) {
        addIterations = 0;

        list.add(value);
        int currentIndex = list.size() - 1;
        while (currentIndex > 0) {
            // tracking iterations
            addIterations++;
            LinkedList.sortIterations++;
            ArrayList.sortIterations++;

            int parentIndex = (currentIndex - 1) / 2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            int result = 0;
            if (comp == null) { // natural ordering
                result = ((Comparable<E>) current).compareTo(parent);
            } else { // use comparator
                result = comp.compare(current, parent);
            }

            if (result < 0) {
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            } else
                break;
            currentIndex = parentIndex;
        }
    }

    /**
     * Method remove
     * 
     * @return the value of the root, null if the heap is empty
     *         reconstructs the heap to keep the MinHeap properties
     */
    @SuppressWarnings("unchecked")
    public E remove() {
        if (list.isEmpty())
            return null;
        removeIterations = 0;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size() - 1)); // value of the root = value of the last node in the heap
        list.remove(list.size() - 1);// remove the last node
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            // tracking iterations
            removeIterations++;
            LinkedList.sortIterations++;
            ArrayList.sortIterations++;

            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size())
                break;
            // finding the smallest of the two children
            int minIndex = left;
            E min = list.get(minIndex); // left child is the min
            if (right < list.size()) {
                int result = 0;
                if (comp == null) {
                    result = ((Comparable<E>) min).compareTo(list.get(right));
                } else {
                    result = comp.compare(min, list.get(right));
                }

                if (result > 0) {
                    minIndex = right;
                }
            }
            E current = list.get(currentIndex);
            min = list.get(minIndex);
            int result = 0;
            if (comp == null) {
                result = ((Comparable<E>) current).compareTo(min);
            } else {
                result = comp.compare(current, min);
            }
            if (result > 0) {
                list.set(minIndex, current);
                list.set(currentIndex, min);
                currentIndex = minIndex;
            } else
                break;
        }
        return removedItem;
    }
}