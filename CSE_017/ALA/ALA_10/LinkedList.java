import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList class with iteration trackers for ALA_10
 */
public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;
    public static int containsIterations, removeIterations, addIterations;

    /**
     * Inner-class node
     */
    private class Node {
        E value;
        Node next;

        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }

    /**
     * no-arg constructor
     */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * add item to the beginning of the list.
     * 
     * @param item
     * @return boolean
     */
    public boolean addFirst(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    /**
     * add item to the end of the list
     * 
     * @param item
     * @return boolean
     */
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Calls addLast() to add item to end of the list.
     * 
     * @param item
     * @return
     */
    public boolean add(E item) {
        return addLast(item);
    }

    /**
     * Get the first item in the list.
     * 
     * @return E
     */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the last item in the list.
     * 
     * @return E
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Remove the first item in the list.
     * 
     * @return boolean
     */
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return true;
    }

    /**
     * Remove the last item in the list.
     * 
     * @return boolean
     */
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        Node previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return true;
    }

    /**
     * Override the toString() method to return a formatted string of list.
     * 
     * @return String
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
     * Empties the list by setting head/tail references to null.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Determine if this list is empty.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * getter method for number of elements in list.
     * 
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Generate iterator for LinkedList.
     * 
     * @return Iterator<E>
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Iterator class for LinkedList
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * Checks for next item in the list.
         * 
         * @return boolean
         */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * Returns the next item in the list.
         * 
         * @return E
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
     * Contains method for the LinkedList
     * 
     * @param o
     * @return boolean
     */
    // O(n)
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        containsIterations = 0;
        E value = (E) o;
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            containsIterations++;
            if (iter.next().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a specific item from the LinkedList.
     * 
     * @param o
     * @return boolean
     */
    // O(n)
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        removeIterations = 0;
        Node current = head;
        Node previous = null;
        E value = (E) o;
        while (current != null && !current.value.equals(value)) {
            removeIterations++;
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (previous == null) {
            return removeFirst();
        }
        previous.next = current.next;
        if (current == tail) {
            tail = previous;
        }
        size--;
        return true;
    }

    /**
     * Add an item at a specific index to the LinkedList.
     * 
     * @param index
     * @param item
     * @return boolean
     */
    // O(n)
    public boolean add(int index, E item) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        addIterations = 0;
        if (index == 0) {
            return addFirst(item);
        }
        if (index == size) {
            return addLast(item);
        }
        Node current = head;
        Node previous = null;
        for (int i = 0; i < index; i++) {
            addIterations++;
            previous = current;
            current = current.next;
        }
        Node newNode = new Node(item);
        previous.next = newNode;
        newNode.next = current;
        size++;
        return true;
    }
}