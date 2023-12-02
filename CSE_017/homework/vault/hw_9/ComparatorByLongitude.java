import java.util.Comparator;

/**
 * Comparator class for City objects defines ordering by longitude.
 */
public class ComparatorByLongitude implements Comparator<City> {

    /**
     * Compare method uses Double wrapper class to compare City longitudes.
     * 
     * @param c1
     * @param c2
     * @return int
     */
    public int compare(City c1, City c2) {
        return ((Double) c1.getLongitude()).compareTo((Double) c2.getLongitude());
    }
}
