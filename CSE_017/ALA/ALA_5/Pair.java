/**
 * The Pair class is a generic class the defines a Pair object to be used in ALA
 * 5. A pair in this context is simply defined as 2 elements which can be of any
 * type.
 *
 * @since 2023-10-5
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Pair<E1, E2> {

    // data members
    private E1 first;
    private E2 second;

    /**
     * 2-arg constructor of the generic Pair class.
     * 
     * @param first
     * @param second
     */
    public Pair(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    // getters/setters
    public E1 getFirst() {
        return first;
    }

    public E2 getSecond() {
        return second;
    }

    public void setFirst(E1 first) {
        this.first = first;
    }

    public void setSecond(E2 second) {
        this.second = second;
    }

    /**
     * Overrides the toString() method to define how to make a string out of the
     * generic Pair object.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    /**
     * Overrides the equals() method to define how to check equality of two pairs.
     * Ensures that both elements of the provided pairs pass the .equals() method.
     */
    @Override
    @SuppressWarnings("unchecked") // couldn't stand the compiler warnings...
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair<E1, E2> p = (Pair<E1, E2>) o; // compiler error for unsafe type ignored
            return this.first.equals(p.first) && this.second.equals(p.second);
        } else {
            return false;
        }
    }
}