import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Stores the carbon emissions of a country as two lists, one for the total
 * carbon emissions in tons, and the other for the carbon emissions per capita
 * over a list of years.
 * 
 * @since 2023-10-27
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Country implements Comparable<Country> {

    // data members
    private String name;
    private LinkedList<Pair<Integer, Double>> carbonEmission;
    private LinkedList<Pair<Integer, Double>> carbonEmissionPerCapita;

    /**
     * 1-arg constructor of the Country class sets the name of the country and
     * creates empty instances of the LinkedList class for carbonEmission and
     * carbonEmissionPerCapita.
     * 
     * @param name
     */
    public Country(String name) {
        this.name = name;
        carbonEmission = new LinkedList<>();
        carbonEmissionPerCapita = new LinkedList<>();
    }

    /**
     * Getter method for country name data member
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for country name data member
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the pair (year, tons) to the linked list carbonEmission
     * 
     * @param year
     * @param tons
     */
    public void addEmission(int year, double tons) {
        carbonEmission.add(new Pair<Integer, Double>(year, tons));
    }

    /**
     * Adds the pair (year, tons) to the linked list carbonEmissionPerCapita.
     * 
     * @param year
     * @param tons
     */
    public void addEmissionPerCapita(int year, double tons) {
        carbonEmissionPerCapita.add(new Pair<Integer, Double>(year, tons));
    }

    /**
     * Returns an iterator pointing to this country's carbon emission in tons at the
     * given year. Returns null if year not found.
     * 
     * @param year
     * @return ListIterator
     */
    public ListIterator<Pair<Integer, Double>> getEmission(int year) {
        ListIterator<Pair<Integer, Double>> output = carbonEmission.listIterator();
        while (output.hasNext()) {
            if (output.next().getFirst().equals(year)) {
                output.previous();
                return output;
            }
        }
        return null;
    }

    /**
     * Returns an iterator pointing to the country's carbon emission per capita at
     * the given year.
     * 
     * @param year
     * @return ListIterator
     */
    public ListIterator<Pair<Integer, Double>> getEmissionPerCapita(int year) {
        ListIterator<Pair<Integer, Double>> output = carbonEmissionPerCapita.listIterator();
        while (output.hasNext()) {
            if (output.next().getFirst().equals(year)) {
                output.previous();
                return output;
            }
        }
        return null;
    }

    /**
     * Returns the total of the country's carbon emissions in tons over all the
     * years.
     * 
     * @return double
     */
    public double getTotalEmissions() {
        ListIterator<Pair<Integer, Double>> iter = carbonEmission.listIterator();
        double output = 0.0;
        while (iter.hasNext()) {
            output += iter.next().getSecond();
        }
        return output;
    }

    /**
     * Returns a formatted string that contains the name of the country followed by
     * the list of carbon emissions and the carbon emission per capita for all the
     * years.
     * 
     * @return String
     */
    @Override
    public String toString() {
        String output = this.name;
        output += "\n" + carbonEmission;
        output += "\n" + carbonEmissionPerCapita;
        return output;
    }

    /**
     * Returns true if the two countries being compared have the same name, false
     * otherwise.
     * 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Country) {
            Country tempComp = (Country) o;
            if (tempComp.getName().equals(this.name)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Compares two countries using their total carbon emissions.
     * 
     * @return int
     */
    public int compareTo(Country c) {
        if (this.getTotalEmissions() < c.getTotalEmissions()) {
            return -1;
        } else if (this.getTotalEmissions() > c.getTotalEmissions()) {
            return 1;
        } else {
            return 0;
        }
    }
}
