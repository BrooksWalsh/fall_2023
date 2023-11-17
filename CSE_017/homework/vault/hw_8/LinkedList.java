import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * LinkedList Generic Class
 */
public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;

    // Tracking stats members
    public static int sortIterations;

    /**
     * Inner class Node
     * - modified*
     */
    private class Node {
        E value;
        Node next, previous;

        Node(E initialValue) {
            value = initialValue;
            next = null;
            previous = null;
        }
    }

    /**
     * Default Constructor
     * creates an empty linked list
     * Time complexity: O(1)
     */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Adding a value at the head of the list
     * - modified*
     * 
     * @param value to be added
     * @return true if the operation was successful
     */
    // Time complexity: O(1)
    public boolean addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head; // change new head's next pointer
            head.previous = newNode; // change old head's previous pointer
            head = newNode; // change old head to new head
        }
        size++;
        return true;
    }

    /**
     * Adding a value at the tail of the list
     * - modified*
     * 
     * @param value to be added
     * @return true if the operation was successful
     */
    // Time complexity: O(1)
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode; // change old tail's next pointer
            newNode.previous = tail; // change new tail's previous pointer
            tail = newNode; // change old tail to new tail
        }
        size++;
        return true;
    }

    /**
     * Adding a value at the tail of the list
     * calls addLast
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
     */
    public boolean add(E item) {
        return addLast(item);
    }

    /**
     * Get the value of the node at the head of the list
     * 
     * @return value of the node at the head
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the value of the node at the tail of the list
     * 
     * @return value of the node at the tail
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes the node at the head of the list
     * - modified*
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     */
    // Time complexity: O(1)
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next; // change head to next element
        if (head != null) {
            head.previous = null; // remove the new head's previous pointer
        }
        if (head == null) // removed the only node in the LL
            tail = null;
        size--;
        return true;
    }

    /**
     * Removes the node at the tail of the list
     * - modified*
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     */
    // Time complexity: O(1)
    public boolean removeLast() {
        if (size == 1)
            return removeFirst();
        if (head == null)
            throw new NoSuchElementException();
        Node current = tail.previous;
        current.next = null;
        tail = current;
        size--;
        return true;
    }

    /**
     * Uses the heap sort algorithm to sort the nodes of the LinkedList. Uses
     * Comparator object passed for ordering. If null is passed, the natural
     * ordering of the object will be used.
     * 
     * @param comp Comparator for order or null for natural ordering
     */
    // Time Complexity: O(nLog(n) + nLog(n)) = O(2(nLog(n))) = O(nLog(n))
    // Space Complexity: O(n)
    public void sort(Comparator<E> comp) {
        sortIterations = 0; // tracking
        MinHeap<E> minHeap = null;
        if (comp == null) {
            minHeap = new MinHeap<>();
        } else {
            minHeap = new MinHeap<>(comp);
        }
        Node currentNode = head;
        while (currentNode != null) {
            sortIterations++; // tracking
            minHeap.add(currentNode.value);
            currentNode = currentNode.next;
        }
        clear();
        while (!minHeap.isEmpty()) {
            sortIterations++; // tracking
            add(minHeap.remove());
        }
    }

    /**
     * toString method
     * 
     * @return a formatted string that contains the values of all the nodes in the
     *         list
     *         Time complexity: O(n)
     */
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }

    /**
     * clear method
     * resets size to 0 and head and tail to null
     * Time complexity: O(1)
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * isEmpty method
     * 
     * @return true if the list is empty
     *         Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * size method
     * 
     * @return the number of nodes in the list
     *         Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * iterator method
     * 
     * @override iterator() from the interface Collection
     * @return an iterator object pointing to the first value in the list
     *         Time complexity: O(1)
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner class that implements the interface Iterator
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * hasNext method
         * 
         * @return true if the current is not null
         *         Time complexity: O(1)
         */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * next method
         * 
         * @return the value of the node referenced by current and
         *         modifies current to hold the reference of the next node
         * @throws NoSuchElementException if current is null
         *                                Time complexity: O(1)
         */
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }

    /**
     * Allows for creation of a ListIterator<E> for this LinkedList. Different from
     * the regular Iterator<E> because it can iterate backwards.
     * 
     * @return ListIterator object pointing to the first element
     */
    // Time Complexity: O(1)
    public ListIterator<E> listIterator() {
        return new LinkedListListIterator();
    }

    /**
     * Allows for creation of a ListIterator<E> for this LinkedList. Different from
     * the regular Iterator<E> because it can iterate backwards. This method creates
     * a ListIterator<E> that starts at a given index.
     * 
     * @return ListIterator object pointing to the given index
     */
    // Time Complexity: O(n)
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new LinkedListListIterator(index);
    }

    /**
     * Inner class to implement the interface ListIterator<E>
     */
    private class LinkedListListIterator implements ListIterator<E> {

        // data member
        private Node current = head;

        /**
         * Default constructor of the LinkedListListIterator class. Points to the head
         * of the list.
         */
        // Time Complexity: O(1)
        public LinkedListListIterator() {
            current = head;
        }

        /**
         * 1-arg constructor of the LinkedListListIterator class. Points to whichever
         * element is at the given index. After the final element is called with size.
         * 
         * @param index
         */
        // Time Complexity: O(n)
        public LinkedListListIterator(int index) {
            if (index == size) {
                current = null;
                return;
            }
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }

        /**
         * Returns true if there is another element after this one in the list.
         */
        // Time Complexity: O(1)
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the list.
         */
        // Time Complexity: O(1)
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E output = current.value;
            current = current.next;
            return output;
        }

        /**
         * Returns true if there is another element previous to this one in the list.
         */
        // Time Complexity: O(1)
        public boolean hasPrevious() {
            if (current == null) { // after end of list
                if (tail == null) { // catch empty list
                    return false;
                } else {
                    return true;
                }
            }
            return current.previous != null;
        }

        /**
         * Returns the previous element in the list.
         */
        // Time Complexity: O(1)
        public E previous() {
            if (current == null) { // handle when iterator is at the end of list
                if (tail == null) {
                    throw new NoSuchElementException("Empty List");
                }
                current = tail;
            } else if (current.previous == null) { // if at beginning of list
                throw new NoSuchElementException();
            } else {
                current = current.previous;
            }

            return current.value;
        }

        // all other methods are not implemented

        /**
         * Add method not implemented for LinkedListListIterator class.
         */
        public void add(E value) {
            throw new UnsupportedOperationException();
        }

        /**
         * Remove method not implemented for LinkedListListIterator class.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Set method not implemented for LinkedListListIterator class.
         */
        public void set(E value) {
            throw new UnsupportedOperationException();
        }

        /**
         * nextIndex method not implemented for LinkedListListIterator class.
         */
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        /**
         * previousIndex method not implemented for LinkedListListIterator class.
         */
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
    }
}