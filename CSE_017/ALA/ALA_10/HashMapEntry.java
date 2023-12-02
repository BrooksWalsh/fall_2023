/**
 * HashMapEntry class acts as "nodes" to a HashMap.
 */
public class HashMapEntry<K, V> {
	private K key;
	private V value;

	/**
	 * 2-arg constructor of Entry class requires key and value arguments.
	 * 
	 * @param k
	 * @param v
	 */
	public HashMapEntry(K k, V v) {
		key = k;
		value = v;
	}

	/**
	 * Getter method for key data member.
	 * 
	 * @return K
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Getter method for the value data member.
	 * 
	 * @return V
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Setter method for the key data member
	 * 
	 * @param k
	 */
	public void setKey(K k) {
		key = k;
	}

	/**
	 * Setter method for the key data member
	 * 
	 * @param v
	 */
	public void setValue(V v) {
		value = v;
	}

	/**
	 * Returns formatted string of HashMapEntry
	 */
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}