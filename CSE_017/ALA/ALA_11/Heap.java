import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
 * Class to implement a maxHeap
 */
public class Heap<E extends Comparable<E>> {
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;

    public static int iterations;
    /**
     * Default Constructor
     */
    public Heap(){
        list = new ArrayList<>();
    }
    /**
     * Method size
     * @return the number of nodes in the heap
     */
    public int size(){
        return list.size(); 
    }
    /**
     * Method isEmpty
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * Method to empty the heap
     */
    public void clear(){
        list.clear(); 
    }
    /**
     * Method toString
     * @return a formatted string containing the values of the nodes of the heap
     */
    public String toString(){
        return list.toString();
    }
    /**
     * Method getRoot
     * @return the value of the root
     */
    public E getRoot(){
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(0);
    }
    /**
     * Method contains
     * @param value the value being searched in the heap
     * @return true if the value is found, false otherwise
     * Time complexity: O(n)
     */
    public boolean contains(E value) {
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(value))
                return true;
        }
        return false;
    }
    /**
     * Method add
     * @param value to be added to the heap
     * reconstructs the heap to keep the MaxHeap properties
     * Time complexity: O(logn)
     */
    public void add(E value) {
        iterations = 0;
        list.add(value);
        int currentIndex = list.size()-1; 
        while(currentIndex > 0) {
            iterations++;
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            if(current.compareTo(parent) > 0) {
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else
                break;
            currentIndex = parentIndex;
        }
    }
    /**
     * Method remove
     * @return the value of the root, null if the heap is empty
     * reconstructs the heap to keep the MaxHeap properties
     * Time complexity: O(logn)
     */
    public E remove() {
        iterations = 0;
        if(list.isEmpty()) 
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1)); 
        list.remove(list.size()-1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            iterations++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size()) 
                break;
            int maxIndex = left;
            E max = list.get(maxIndex);
            if (right < list.size())
                if(max.compareTo(list.get(right)) < 0)
                    maxIndex = right;
            E current = list.get(currentIndex);
            max = list.get(maxIndex);            
            if(current.compareTo(max) < 0){
                list.set(maxIndex, current);
                list.set(currentIndex, max);
                currentIndex = maxIndex;
            }
            else
                break;
        }
        return removedItem;
    }
}