import java.util.EmptyStackException;

/**
 * Generic class Stack
 */
public class Stack<E> {
	// the elements of the stack are stored in an arraylist
	private ArrayList<E> elements;

	/**
	 * Default constructor to create an empty stack
	 */
	public Stack() {
		elements = new ArrayList<>();
	}

	/**
	 * Constructor with one parameter
	 * 
	 * @param capacity the initial capacity of the stack
	 */
	public Stack(int capacity) {
		elements = new ArrayList<>(capacity);
	}

	/**
	 * Method to get the size of the stack
	 * 
	 * @return the number of elements in the stack
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Method to check if teh stack is empty
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/**
	 * Method to add a new item to the top of the stack
	 * 
	 * @param item value to be pushed at the top of the stack
	 */
	public void push(E item) { // O(1) --> O(n)
		elements.add(item);
	}

	/**
	 * Method to return the value of the top of the stack
	 * 
	 * @return value of the item at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public E peek() { // O(1)
		if (isEmpty())
			throw new EmptyStackException();
		return elements.get(size() - 1);
	}

	/**
	 * Method to remove the value at the top of the stack
	 * 
	 * @return value of the item at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public E pop() { // O(1)
		if (isEmpty())
			throw new EmptyStackException();
		E value = peek();
		elements.remove(size() - 1);
		return value;
	}

	/**
	 * Method to get the elements of the stack
	 * 
	 * @return a formatted string with the elements in the stack
	 */
	public String toString() { // O(n)
		return "Stack: " + elements.toString();
	}
}