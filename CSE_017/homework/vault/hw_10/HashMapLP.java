import java.util.ArrayList;

/**
 * Class that implements a hashtable with two generic types
 * K for key
 * V for value
 * Linear Probing is used to resolve the collisions
 */
public class HashMapLP<K, V> {
	// data member: Array of linked lists
	private HashMapEntry<K, V>[] hashTable;
	// data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor;
	// data member: counts the total number of collisions in the hashmap
	private int collisions;
	// data member: for tracking iterations
	public static int getIterations;

	/**
	 * Default constructor
	 * Creates a hashmap with capacity 100 and load factor 0.9
	 */
	public HashMapLP() {
		this(100, 0.5);
	}

	/**
	 * Constructor with one parameter
	 * Creates a hashmap with capacity c and default load factor 0.9
	 * 
	 * @param c the capacity of the hashtable
	 */
	public HashMapLP(int c) {
		this(c, 0.5);
	}

	/**
	 * Constructor with two parameters
	 * 
	 * @param c  the capacity of the hashtable
	 * @param lf the load factor for the hashtable
	 */
	@SuppressWarnings("unchecked")
	public HashMapLP(int c, double lf) {
		hashTable = new HashMapEntry[trimToPowerOf2(c)];
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
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		this.hashTable = new HashMapEntry[hashTable.length];
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
	 * @param key to be searched
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
	 * - Remade for HW_10 to implement linear probing
	 * 
	 * @param key to be searched
	 * @return the value of the key if found, null otherwise
	 */
	public V get(K key) {
		getIterations = 0;
		int HTIndex = hash(key.hashCode());
		int originalIndex = HTIndex;
		boolean wrappedAround = false;

		// linear probing
		while (hashTable[HTIndex] != null) {
			HashMapEntry<K, V> entry = hashTable[HTIndex];
			getIterations++; // track iterations

			// if key found
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}

			// else move to next index or wrap around
			HTIndex = ++HTIndex % hashTable.length;

			// handle wrapping around to beginning
			if (HTIndex == originalIndex) {
				// if wrapped around already, key not found
				if (wrappedAround) {
					return null;
				}
				wrappedAround = true;
			}
		}

		return null; // key not found
	}

	/**
	 * Method to add a pair (key,value) to the hashtable
	 * 
	 * - Remade for HW_10 to implement linear probing
	 * 
	 * @param key   to be added
	 * @param value of the key to be added
	 * @return old value if the key was found or the new value if key was not found
	 */
	public V put(K key, V value) {
		// rehash if necessary
		if (size >= hashTable.length * loadFactor) {
			rehash();
		}

		// hash the key
		int HTIndex = hash(key.hashCode());
		boolean collisionCounted = false;

		// linear probing (if necessary)
		while (hashTable[HTIndex] != null) {
			HashMapEntry<K, V> entry = hashTable[HTIndex];

			// update value if key already exists and return old
			if (entry.getKey().equals(key)) {
				V oldValue = entry.getValue();
				entry.setValue(value);
				return oldValue;
			}

			// else move to next index or wrap around
			HTIndex = ++HTIndex % hashTable.length;

			// track collision count
			if (!collisionCounted) {
				collisions++;
				collisionCounted = true;
			}
		}

		// once a null reference is found, assuming key not found, add entry
		hashTable[HTIndex] = new HashMapEntry<>(key, value);
		size++;
		// return the value that was added successfully
		return value;
	}

	/**
	 * Method to rehash the hashtable
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		ArrayList<HashMapEntry<K, V>> list = toList();
		hashTable = new HashMapEntry[hashTable.length << 1]; // double the length of hashtable
		size = 0;
		for (HashMapEntry<K, V> entry : list) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Method to return all the pairs (key,value) stored in the hashtable
	 * 
	 * @return an array list with all the pairs (key,value)
	 */
	public ArrayList<HashMapEntry<K, V>> toList() {
		ArrayList<HashMapEntry<K, V>> list = new ArrayList<>();
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				list.add(hashTable[i]);
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
				HashMapEntry<K, V> entry = hashTable[i];
				out += entry.toString();
				out += ",";
			}
		}
		out += "]";
		return out;
	}

	/**
	 * Prints the capacity of the hashTable, total number of collisions, number of
	 * clusters formed in the hashtable and the size of the largest and smallest
	 * cluster.
	 */
	public void printClusters() {
		int capacity = hashTable.length, clusters = 0, largestCluster = 0, smallestCluster = hashTable.length;
		int tempLargest = 0, tempSmallest = 0;
		boolean activeCluster = false;
		// find number of clusters, and largest/smallest
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				tempLargest++;
				tempSmallest++;
				if (activeCluster == false) {
					activeCluster = true;
					clusters++;
				}
			} else { // end of current cluster
				if (tempLargest > largestCluster) {
					largestCluster = tempLargest;
				}
				if (tempSmallest < smallestCluster && tempSmallest > 0) {
					smallestCluster = tempSmallest;
				}
				activeCluster = false;
				tempLargest = tempSmallest = 0;
			}
		}
		System.out.printf(
				"HashTable capacity: %d\nTotal number of collisions: %d\nNumber of clusters: %d\nSize of the largest cluster: %d\nSize of the smallest cluster: %d\n",
				capacity, collisions, clusters, largestCluster, smallestCluster);

	}
}