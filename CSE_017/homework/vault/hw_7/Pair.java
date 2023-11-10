/**
 * Generic class Pair with two generic types
 */
public class Pair<E1, E2> {
    // data member: first object of the pair
    private E1 first;
    // data member: second object of the pair
    private E2 second;

    /**
     * Default constructor
     * sets first and second to null
     */
    public Pair() {
        first = null;
        second = null;
    }

    /**
     * Constructor with two parameters
     * 
     * @param f the value of first
     * @param s the value of second
     */
    Pair(E1 f, E2 s) {
        first = f;
        second = s;
    }

    /**
     * Get the reference of the first object
     * 
     * @return the reference first
     */
    public E1 getFirst() {
        return first;
    }

    /**
     * Get the reference of the second object
     * 
     * @return the reference second
     */
    public E2 getSecond() {
        return second;
    }

    /**
     * sets the reference first
     * 
     * @param f the reference first
     */
    public void setFirst(E1 f) {
        first = f;
    }

    /**
     * sets the reference second
     * 
     * @param f the reference second
     */
    public void setSecond(E2 s) {
        second = s;
    }

    /**
     * @override toString from class Object
     * @return formatted string with the values of the objects first and second
     */
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    /**
     * @override equals from class Object
     * @param o object to be compared to this pair
     * @return true if this pair is equal to the pair o
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair<E1, E2> pair = (Pair<E1, E2>) o;
            return this.getFirst().equals(pair.getFirst()) &&
                    this.getSecond().equals(pair.getSecond());
        }
        return false;
    }
}