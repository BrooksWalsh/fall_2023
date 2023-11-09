import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList Generic Class
 */
public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;

    public static int containsIterations, addIterations, removeIterations;

    /**
     * Inner class Node
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
     * Default Constructor
     * creates an empty linkedlist
     * Time complexity: O(1)
     */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Adding a value at the head of the list
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
     */
    public boolean addFirst(E value) {
        Node newNode = new Node(value);
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
     * Adding a value at the tail of the list
     * 
     * @param value to be added
     * @return true if the operation was successful
     *         Time complexity: O(1)
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
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(1)
     */
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null) // removed the only node in the LL
            tail = null;
        size--;
        return true;
    }

    /**
     * Removes the node at the tail of the list
     * 
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     *                                Time complexity: O(n)
     */
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
        return true;
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
     * restes size to 0 and head and tail to null
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

    // Time Complexity: O(n)
    /**
     * Contains method returns true if this LinkedList contains the object passed as
     * an argument, else returns false.
     * 
     * @param o
     * @return
     */
    public boolean contains(Object o) {
        Iterator<E> iter = iterator();
        containsIterations = 0;
        while (iter.hasNext()) {
            containsIterations++;
            E element = iter.next();
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    // Time Complexity: O(n)
    /**
     * Remove method for LinkedLists works by setting the node.next on the previous
     * to the removed node to the node.next of the removed node. Also deals with
     * some edge cases. Returns true if the element is found and removed.
     * 
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        Node current = head;
        Node previous = null;
        removeIterations = 0;
        while (current != null && !current.value.equals(o)) {
            removeIterations++;
            previous = current;
            current = current.next;
        }

        if (current == null) { // if o not found or LL is empty
            return false;
        }

        if (previous == null) { // if o is equal to value of head
            return removeFirst();
        }
        previous.next = current.next;
        size--;
        return true;
    }

    // Time Complexity: O(n)
    /**
     * Add(index, item) method for a LinkedList works by iterating through the
     * LinkedList to "simulate" having an index. Returns true in all cases because
     * we do not deal with memory in this class.
     * 
     * @param index
     * @param item
     * @return
     */
    public boolean add(int index, E item) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return addFirst(item);
        }

        if (index == size) {
            return addLast(item);
        }

        addIterations = 0;

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