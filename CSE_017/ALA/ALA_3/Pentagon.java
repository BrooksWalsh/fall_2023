/**
 * The Pentagon class is a subclass of the Shape class and describes various
 * attributes about a Pentagon object.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Pentagon extends Shape {

    // data member
    private double side;

    /**
     * Default constructor of the Pentagon class creates a regular pentagon with
     * "None" color and a side length of 1.0
     */
    public Pentagon() {
        super();
        side = 1.0;
    }

    /**
     * 2-arg Constructor of the of the Pentagon class creates a regular pentagon
     * with a specified color and side length.
     * 
     * @param c
     * @param s
     */
    public Pentagon(String c, double s) {
        super(c);
        side = s;
    }

    // getter
    public double getSide() {
        return side;
    }

    // setter
    public void setSide(double side) {
        this.side = side;
    }

    /**
     * Uses the area of a pentagon formula to calculate the area of the Pentagon
     * object.
     */
    public double getArea() {
        double c = .25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5)));
        return c * side * side;
    }

    /**
     * Simply returns 5 times the side length of a Pentagon object as this is a
     * regular pentagon and thus that is the perimeter formula.
     */
    public double getPerimeter() {
        return 5 * side;
    }

    /**
     * Implements cloneable by overriding the clone method using a deep clone
     */
    public Object clone() {
        return new Pentagon(getColor(), side);
    }

    /**
     * Implements the user-defined Scalable interface to scale Pentagon objects
     */
    public void scale(double f) {
        side *= f;
    }

    /**
     * Returns formatted string describing Pentagon object.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%-10s\t%-15.2f\t\t%-10.2f\t%-8.2f", "Pentagon", getColor(), side, getArea(), getPerimeter());
    }

}