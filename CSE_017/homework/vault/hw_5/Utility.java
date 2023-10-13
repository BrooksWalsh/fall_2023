import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Utility class is a helper class that currently only contains the
 * mergeSort algorithm (and it's helper methods). This class's methods are
 * generic and work for ArrayList<E>.
 *
 * @since 2023-10-13
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Utility {

    /**
     * Generic MergeSort method, now using generic ArrayLists. Uses the user-defined
     * subList() method to split lists in half for sorting.
     * 
     * @param <E>  generic
     * @param list
     * @param c    Comparator
     */
    public static <E> void mergeSort(ArrayList<E> list, Comparator<E> c) {
        if (list.size() > 1) { // length==1: base case

            // NOT catching the arrayIndexOutOfBounds exception b/c
            // these index will always be in-bounds

            // split list into two halves
            ArrayList<E> firstHalf = new ArrayList<E>(subList(list, 0, list.size() / 2));
            ArrayList<E> secondHalf = new ArrayList<E>(subList(list, list.size() / 2, list.size()));
            // recursive call on each half
            mergeSort(firstHalf, c);
            mergeSort(secondHalf, c);
            // merge the sorted halves back into list
            merge(firstHalf, secondHalf, list, c);
        }
    }

    /**
     * Merge helper method, now generic and using generic ArrayLists.
     * 
     * @param <E>   generic
     * @param list1
     * @param list2
     * @param list
     * @param c     Comparator
     */
    public static <E> void merge(ArrayList<E> list1, ArrayList<E> list2, ArrayList<E> list, Comparator<E> c) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while (list1Index < list1.size() && list2Index < list2.size()) {
            // copy the smallest element to list
            if (c.compare(list1.get(list1Index), list2.get(list2Index)) < 0) {
                list.set(listIndex++, list1.get(list1Index++));
            } else {
                list.set(listIndex++, list2.get(list2Index++));
            }
        }
        // copy the remaining elements of list1 if list1 is longer than list2
        while (list1Index < list1.size())
            list.set(listIndex++, list1.get(list1Index++));
        // copy the remaining elements of list2 if list2 is longer than list1
        while (list2Index < list2.size())
            list.set(listIndex++, list2.get(list2Index++));
    }

    /**
     * Generic method to return a sublist from start index to index end-1.
     * - Isn't there a built-in subList() method for ArrayLists tho?
     * 
     * @param <E>   generic
     * @param list
     * @param start
     * @param end
     * @return elements from start-(end-1)
     * @throws ArrayIndexOutOfBoundsException
     */
    public static <E> ArrayList<E> subList(ArrayList<E> list, int start, int end)
            throws ArrayIndexOutOfBoundsException {
        int size = list.size();
        if (start > end || start > size || start < 0 || end > size || end < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (start == end) {
            return null;
        }

        // else returns an arraylist with elements from index start to index end-1
        ArrayList<E> output = new ArrayList<>();
        for (int i = start; i < end; i++) {
            output.add(list.get(i));
        }
        return output;
    }
}
