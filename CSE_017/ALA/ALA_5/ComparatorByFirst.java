import java.util.Comparator;

/**
 * ComparatorByFirst is a generic class with the constraint that the first
 * element must extend Comparable. This class only has one method and is
 * intended to be used within the util.sort() method to determine which element
 * of a Pair object should be compared to create ordering.
 *
 * @since 2023-10-5
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByFirst<E1 extends Comparable<E1>, E2> implements Comparator<Pair<E1, E2>> {

    /**
     * The compare method in the ComparatorByFirst class compares only the first
     * element in a Pair object. The elements themselves can be of any type, but
     * this class is designed to compare only Pair objects.
     * 
     * @param pair1
     * @param pair2
     * @return int
     */
    public int compare(Pair<E1, E2> pair1, Pair<E1, E2> pair2) {
        E1 pair1First = pair1.getFirst();
        E1 pair2First = pair2.getFirst();
        return pair1First.compareTo(pair2First);
    }
}
