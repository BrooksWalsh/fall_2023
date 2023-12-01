import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;
    public static int containsIterations, removeIterations, addIterations;
    // Inner class Node
    private class Node {
        E value;
        Node next;
        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }
    // Constructor
    public LinkedList() {
        head = tail = null;
        size = 0;
    }
    // Adding an item to the list
    public boolean addFirst(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    public boolean add(E item) {
        return addLast(item);
    }
    // Retrieving an item from the list
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    // Removing an item from the list
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return true;
    }
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
    // toString() method
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
    // clear, check if empty, and size
    public void clear() {
        head = tail = null;
        size = 0;
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public int size() {
        return size;
    }

    // Generating an iterator for the list
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;
        public boolean hasNext() {
            return (current != null);
        }
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }

    // Method contains
    // O(n)
    public boolean contains(Object o){
        containsIterations = 0;
        E value = (E) o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIterations++;
            if(iter.next().equals(value))
            {
                return true;
            }
        }
        return false;
    }
    // O(n)
    public boolean remove (Object o){
        removeIterations = 0;
        Node current = head;
        Node previous = null;
        E value = (E) o;
        while(current != null && !current.value.equals(value)){
            removeIterations++;
            previous = current;
            current = current.next;
        }
        if(current == null){
            return false;
        }
        if(previous == null){
            return removeFirst();
        }
        previous.next = current.next;
        if(current == tail){
            tail = previous;
        }
        size--;
        return true;
    }
    // O(n)
    public boolean add(int index, E item){
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        addIterations = 0;
        if(index == 0){
            return addFirst(item);
        }
        if(index == size){
            return addLast(item);
        }
        Node current = head;
        Node previous = null;
        for(int i=0; i<index; i++){
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