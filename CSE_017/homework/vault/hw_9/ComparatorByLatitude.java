import java.util.Comparator;

/**
 * Comparator class for City objects defines ordering by latitude.
 */
public class ComparatorByLatitude implements Comparator<City> {

    /**
     * Compare method uses Double wrapper class to compare City latitudes.
     * 
     * @param c1
     * @param c2
     * @return int
     */
    public int compare(City c1, City c2) {
        return ((Double) c1.getLatitude()).compareTo((Double) c2.getLatitude());
    }
}
