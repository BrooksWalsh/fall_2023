/**
 * Generic class Queue
 */
public class Queue<E> {
  // Queue elements stored in a linked list
  private LinkedList<E> list;

  /**
   * Default constructor to create an empty queue
   */
  public Queue() {
    list = new LinkedList<>();
  }

  /**
   * Method offer to add a new item to the queue
   * 
   * @param item to be queued at the back of the queue
   */
  public void offer(E item) { // O(1)
    list.addLast(item);
  }

  /**
   * Method poll to remove an item from the queue
   * 
   * @return value of the item at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public E poll() { // O(1)
    E value = list.getFirst();
    list.removeFirst();
    return value;
  }

  /**
   * Method peek to get the value of the element at the front
   * 
   * @return the value of the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public E peek() { // O(1)
    return list.getFirst();
  }

  /**
   * Method to get the size of the queue
   * 
   * @return the number of items in the queue
   */
  public int size() {
    return list.size();
  }

  /**
   * Method to clear the queue
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
   * @return a formatted string with all the items in the queue
   */
  public String toString() {
    return "Queue: " + list.toString();
  }

}