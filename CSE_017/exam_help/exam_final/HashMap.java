import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Class HashMap to model a hashtable with separate chaining
 */
public class HashMap<K, V> {
	// Data members
	private int size;
	private double loadFactor;
	private LinkedList<HashMapEntry<K, V>>[] hashTable;
	/**
	 * Private Inner class to model the entries of the hashtable
	 */
	private class HashMapEntry<K, V> {
		// Data members
		K key;
		V value;
		/**
		 * Constructor
		 * @param k the initial value of the member key
		 * @param v the initial value of the member value
		 */
		public HashMapEntry(K k, V v) {
			key = k;
			value = v;
		}
		/**
		 * getKey
		 * @return the value of the member key
		 */
		public K getKey() {
			return key;
		}
		/**
		 * getValue
		 * @return the value of the member value
		 */
		public V getValue() {
			return value;
		}
		/**
		 * setKey
		 * @param k the new value of the member key
		 */
		public void setKey(K k) {
			key = k;
		}
		/**
		 * setValue
		 * @param v the new value of the member value
		 */
		public void setValue(V v) {
			value = v;
		}
		/**
		 * toString
		 * @return a formatted string with the values of the members key and value
		 */
		public String toString() {
			return "(" + key + ", " + value + ")";
		}
	}
	/**
	 * Default constructor
	 * creates a hashtable with initial capacity 100 and load factor 0.9
	 */
	public HashMap() {
		this(100, 0.9);
	}
	/**
	 * Constructor with one parameter
	 * @param c the initial capacity of the hashtable
	 * default load factor 0.9
	 */
	public HashMap(int c) {
		this(c, 0.9);
	}
	/**
	 * Constructor with two parameters
	 * @param c the initial capacity of the hashtable
	 * @param lf the initial load factor
	 */
	public HashMap(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
	}

	/**
	 * trimToPowerOf2
	 * @param c the capacity of the hashtable
	 * @return the closest power of two to c
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity = capacity << 1;
		return capacity;
	}
	/**
	 * hash function
	 * @param hashCode the integer value to be hashed
	 * @return a valid index in the hashtable corresponding to the hashCode
	 */
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
	}
	/**
	 * rehash function
	 * doubles the capacity of the hashtable if the load factor is reached
	 * rehashes all the entries of the hashtable in the new hashtable
	 */
	private void rehash() {
		ArrayList<HashMapEntry<K,V>> list = toList();
		hashTable = new LinkedList[hashTable.length << 1];
		size = 0;
		for(HashMapEntry<K,V> entry: list)
			put(entry.getKey(), entry.getValue()); 
	}

	/**
	 * Method size
	 * @return the number of entries in the hashtable
	 */
	public int size() {
		return size;
	}
	/**
	 * Method clear
	 * clears all the entries of the hashtable
	 */
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
			if (hashTable[i] != null)
				hashTable[i].clear();
	}
	/**
	 * Method isEmpty
	 * @return true if there are no entries in the hashtable, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Method containsKey
	 * @param key the value of the key being searched
	 * @return true if key was found in the hashtable, false otherwise
	 */
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		return false;
	}
	
	/**
	 * Method get
	 * @param key the value of the key being searched
	 * @return the value corresponding to key if key is found, null otherwise
	 */
	public V get(K key) {
		int HTIndex = hash(key.hashCode());
		if (hashTable[HTIndex] != null) {
			LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
			for (HashMapEntry<K, V> entry : ll) {
				if (entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Method remove
	 * @param key the value of the key of the entry to remove
	 * the hashtable is modified if key was found (entry removed)
	 * the hashtable is unchanged if the key was not found
	 */
	public void remove(K key) {
		int HTIndex = hash(key.hashCode());
		if (hashTable[HTIndex] != null) { // key is in the hash map
			LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
			for (HashMapEntry<K, V> entry : ll) {
				if (entry.getKey().equals(key)) {
					ll.remove(entry);
					size--;
					break;
				}
			}
		}
	}
	
	/**
	 * Method put
	 * @param key the value of the member key of the entry to be added to the hashtable
	 * @param value the value of the member value of the entry to be added to the hashtable
	 * @return the old value of the entry if an entry with key is found in the hashtable
	 *         the value of the new entry added to the hashtable if the key was not found
	 */
	public V put(K key, V value) { 
		// check first if key is in the hashtable
		if(get(key) != null) {
			int HTIndex = hash(key.hashCode());
			LinkedList<HashMapEntry<K,V>> ll;
			ll = hashTable[HTIndex];
			for(HashMapEntry<K,V> entry: ll) {
				if(entry.getKey().equals(key)) {
					V old = entry.getValue();
					entry.setValue(value); 
					return old;
				}
			}
		}
		// key not in the hashtable - check load factor
		if(size >= hashTable.length * loadFactor)
			rehash();
		int HTIndex = hash(key.hashCode());
		//create a new LL if empty
		if(hashTable[HTIndex] == null){
				hashTable[HTIndex] = new LinkedList<>();
		}
		// add the new entry to the corresponding LL
		hashTable[HTIndex].add(new HashMapEntry<>(key, value));
		size++; 
		return value;
	}
	
	/**
	 * Method toList
	 * @return an arraylist with all the entries in the hashtable
	 */
	public ArrayList<HashMapEntry<K,V>> toList(){ // O(n)
		ArrayList<HashMapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
				LinkedList<HashMapEntry<K,V>> ll = hashTable[i];
				for(HashMapEntry<K,V> entry: ll)
					list.add(entry);
			}
		} 
		return list;
	}
	/**
	 * Method toString
	 * @return a formatted string with all the entries (key,value) in the hashtable
	 */
	public String toString() { // O(n)
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				for(HashMapEntry<K,V> entry: hashTable[i])
					out += entry.toString();
				out += "\n";
			}
		}
		out += "]"; 
		return out;
	}
}
