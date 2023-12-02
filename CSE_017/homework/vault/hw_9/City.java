/**
 * The City class is a simple object class that represents various statistics
 * about a city.
 * 
 * @since 2023-12-02
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class City implements Comparable<City> {

    // data members
    private String name;
    private String state;
    private double latitude;
    private double longitude;

    /**
     * 4-arg constructor of the City class allows setting of all data members.
     * 
     * @param name
     * @param state
     * @param latitude
     * @param longitude
     */
    public City(String name, String state, double latitude, double longitude) {
        this.name = name;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter method for City name.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for City's parent state.
     * 
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * Getter method for City's latitude.
     * 
     * @return double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter method for City's longitude.
     * 
     * @return double
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter method for City name.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for City's parent state.
     * 
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Setter method for City's latitude.
     * 
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Setter method for City's longitude.
     * 
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Override the toString method to return a formatted String.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%-20s\t%-2s\t%.5f\t%.5f", name, state, latitude, longitude);
    }

    /**
     * Override equals method to define equality of City objects by name.
     * 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof City) {
            City cityComp = (City) o;
            if (this.name.equals(cityComp.name)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Implement comparable for City object and define natural ordering by name.
     * 
     * @param cityComp
     * @return int
     */
    @Override
    public int compareTo(City cityComp) {
        return this.name.compareTo(cityComp.name);
    }
}
