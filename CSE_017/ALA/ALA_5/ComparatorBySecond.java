import java.util.Comparator;

/**
 * ComparatorBySecond is a generic class with the constraint that the second
 * element must extend Comparable. This class only has one method and is
 * intended to be used within the util.sort() method to determine which element
 * of a Pair object should be compared to create ordering.
 *
 * @since 2023-10-5
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorBySecond<E1, E2 extends Comparable<E2>> implements Comparator<Pair<E1, E2>> {

    /**
     * The compare method in the ComparatorBySecond class compares only the second
     * element in a Pair object. The elements themselves can be of any type, but
     * this class is designed to compare only Pair objects.
     * 
     * @param pair1
     * @param pair2
     * @return int
     */
    public int compare(Pair<E1, E2> pair1, Pair<E1, E2> pair2) {
        E2 pair1Second = pair1.getSecond();
        E2 pair2Second = pair2.getSecond();
        return pair1Second.compareTo(pair2Second);
    }
}
