import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Generic class PriorityQueue
 */
public class PriorityQueue<E> {
    // The elements in the priority queue are stored in an arraylist
    private ArrayList<E> list;
    // Comparator object to order the items in the priority queue
    private Comparator<E> comparator;

    /**
     * Default constructor to create an empty priority queue
     * the created queue uses the natural ordering of type E to order the elements
     * in the queue
     */
    public PriorityQueue() {
        list = new ArrayList<>();
        comparator = null;
    }

    /**
     * Constructor with one parameter
     * 
     * @param c Comparator object used to order the elements in the queue
     */
    public PriorityQueue(Comparator<E> c) {
        list = new ArrayList<>();
        comparator = c;
    }

    /**
     * Method to add a new item to the priority queue
     * 
     * @param item value to be added to the priority queue
     */
    public void offer(E item) { // O(n)
        int i, c;
        for (i = 0; i < list.size(); i++) {
            if (comparator == null)
                c = ((Comparable<E>) item).compareTo(list.get(i));
            else
                c = comparator.compare(item, list.get(i));
            if (c < 0)
                break;
        }
        list.add(i, item);
    }

    /**
     * Method to remove a value from the front of the queue
     * 
     * @return the value of the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public E poll() { // O(n)
        if (isEmpty())
            throw new NoSuchElementException();
        E value = list.get(0);
        list.remove(0);
        return value;
    }

    /**
     * Method to get the value at the front of the queue
     * 
     * @return the value of the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.get(0);
    }

    /**
     * Method to get size of the priority queue
     * 
     * @return the number of elements in the queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Method to clear the priority queue
     */
    public void clear() {
        list.clear();
    }

    /**
     * Method to check if the queue is empty
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Method to get the contents of the queue
     * 
     * @return a formatted string with the items in the queue
     */
    public String toString() {
        return "Priority Queue: " + list.toString();
    }
}