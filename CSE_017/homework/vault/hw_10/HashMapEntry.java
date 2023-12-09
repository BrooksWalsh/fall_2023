/**
 * Class HashMapEntry to model a pair of key,value
 */
public class HashMapEntry<K, V> {
	// Data members
	private K key;
	private V value;

	/**
	 * Constructor with two parameters
	 * 
	 * @param k initial value for key
	 * @param v initial value for value
	 */
	public HashMapEntry(K k, V v) {
		key = k;
		value = v;
	}

	/**
	 * Getter for the key
	 * 
	 * @return the value of the member key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Getter for the value
	 * 
	 * @return the value of the member value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Setter for the key
	 * 
	 * @param k the new value of the member key
	 */
	public void setKey(K k) {
		key = k;
	}

	/**
	 * Setter for the value
	 * 
	 * @param v the new value of the member value
	 */
	public void setValue(V v) {
		value = v;
	}

	/**
	 * toString method
	 * 
	 * @return a formatted string with the values of the members key and value
	 */
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}