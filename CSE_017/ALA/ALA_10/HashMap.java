import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class that implements a hashtable of Hashmap entries with two generic types
 * K for key
 * V for value
 */
public class HashMap<K, V> {
	// data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor;
	// data member: Array of linked lists
	private LinkedList<HashMapEntry<K, V>>[] hashTable;

	// tracking iterations data members
	public static int getIterations, putIterations, removeIterations;

	/**
	 * Default constructor
	 * Creates a hashmap with capacity 100 and load factor 0.9
	 */
	public HashMap() {
		this(100, 0.9);
	}

	/**
	 * Constructor with one parameter
	 * Creates a hashmap with capacity c and default load factor 0.9
	 * 
	 * @param c the capacity of the hashtable
	 */
	public HashMap(int c) {
		this(c, 0.9);
	}

	/**
	 * Constructor with two parameters
	 * 
	 * @param c  the capacity of the hashtable
	 * @param lf the load factor for the hashtable
	 */
	@SuppressWarnings("unchecked")
	public HashMap(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
	}

	/**
	 * Method trimToPowerOf2 to create a hashtable with a size that is
	 * the closest power of two to c
	 * 
	 * @param c the capacity intended for the hashtable
	 * @return the closet power of 2 to c
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity = capacity << 1; // * 2
		return capacity;
	}

	/**
	 * The hash function
	 * 
	 * @param the hash code of the key
	 * @return a valid index in the hashtable
	 */
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
	}

	/**
	 * Method to get the size of the hashtable
	 * 
	 * @return the number of elements in the hashtable
	 */
	public int size() {
		return size;
	}

	/**
	 * Method to clear the hashtable
	 */
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
			if (hashTable[i] != null)
				hashTable[i].clear();
	}

	/**
	 * Method to check if the hashtable is empty
	 * 
	 * @return true if the hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Search method
	 * 
	 * @param key to be serached
	 * @return true if key was found, false otherwise
	 */
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		return false;
	}

	/**
	 * Method to get the value of a key
	 * 
	 * @param key to be searched
	 * @return the value of the key if found, null otherwise
	 */
	public V get(K key) {
		getIterations = 0; // tracking
		int HTIndex = hash(key.hashCode());
		if (hashTable[HTIndex] != null) {
			LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
			for (HashMapEntry<K, V> entry : ll) {
				getIterations++; // tracking
				if (entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Method to remove a pair from the hashtable
	 * 
	 * @param key to be searched and removed
	 *            if the key is not found, the hashtable is unchanged
	 */
	public void remove(K key) {
		removeIterations = 0; // tracking
		int HTIndex = hash(key.hashCode());
		if (hashTable[HTIndex] != null) { // key is in the hash map
			LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
			for (HashMapEntry<K, V> entry : ll) {
				removeIterations++; // tracking
				if (entry.getKey().equals(key)) {
					ll.remove(entry);
					size--;
					break;
				}
			}
		}
	}

	/**
	 * Method to add a pair (key,value) to the hashtable
	 * 
	 * @param key   to be added
	 * @param value of the key to be added
	 * @return old value if the key was found or the new value if key was not found
	 */
	public V put(K key, V value) {
		putIterations = 0;
		V val = get(key); // modified for tracking
		putIterations += getIterations; // tracking
		if (val != null) { // The key is in the hash map
			int HTIndex = hash(key.hashCode());
			LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
			for (HashMapEntry<K, V> entry : ll) {
				putIterations++; // tracking
				if (entry.getKey().equals(key)) {
					V old = entry.getValue();
					entry.setValue(value);
					return old;
				}
			}
		}
		// key not in the hash map - check load factor
		if (size >= hashTable.length * loadFactor)
			rehash();

		int HTIndex = hash(key.hashCode());
		// create a new LL if empty
		if (hashTable[HTIndex] == null) {
			hashTable[HTIndex] = new LinkedList<>();
		}
		hashTable[HTIndex].add(new HashMapEntry<>(key, value));
		size++;
		return value;
	}

	/**
	 * Method to rehash the hashtable
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		ArrayList<HashMapEntry<K, V>> list = toList();
		hashTable = new LinkedList[hashTable.length << 1]; // double the length of hashtable
		size = 0;
		for (HashMapEntry<K, V> entry : list) {
			putIterations++; // tracking, but call to put() instantly clears putIterations
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Method to return the pairs (key,value) stored in teh hashtable
	 * 
	 * @return an array list with all the pairs (key,value)
	 */
	public ArrayList<HashMapEntry<K, V>> toList() {
		ArrayList<HashMapEntry<K, V>> list = new ArrayList<>();
		for (int i = 0; i < hashTable.length; i++) {
			putIterations++; // tracking
			if (hashTable[i] != null) {
				LinkedList<HashMapEntry<K, V>> ll = hashTable[i];
				for (HashMapEntry<K, V> entry : ll) {
					putIterations++; // tracking
					list.add(entry);
				}
			}
		}
		return list;
	}

	/**
	 * toString method
	 * 
	 * @return formatted string with all the pairs (key,value) in the hashtable
	 */
	public String toString() {
		String out = "[";
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				for (HashMapEntry<K, V> entry : hashTable[i])
					out += entry.toString();
				out += "\n";
			}
		}
		out += "]";
		return out;
	}

	/**
	 * Returns the largest number of collisions that happened when created the
	 * current HashMap.
	 * 
	 * @return int
	 */
	public int collisions() {
		int max = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				int size = hashTable[i].size();
				if (size > max) {
					max = size;
				}
			}
		}
		return max;
	}
}
