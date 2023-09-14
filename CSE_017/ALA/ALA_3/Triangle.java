/**
 * The Triangle class is a subclass of the Shape class and describes various
 * attributes about a Triangle object.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Triangle extends Shape {

    // data members
    private double side1, side2, side3;

    /**
     * Default constructor of the Triangle class creates a Triangle object with
     * "None" color and a side length of 1
     */
    public Triangle() {
        super();
        side1 = side2 = side3 = 1.0;
    }

    /**
     * 4-arg constructor of the Triangle class creates a Triangle object with a
     * specified color, and individually specified side lengths.
     * 
     * @param c
     * @param s1
     * @param s2
     * @param s3
     */
    public Triangle(String c, double s1, double s2, double s3) {
        super(c);
        side1 = s1;
        side2 = s2;
        side3 = s3;
    }

    // getters
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // setters
    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    /**
     * Uses area of a triangle formula to return the area of the Triangle object
     */
    public double getArea() {
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p * side3));
    }

    /**
     * Simply adds the sides and returns the perimeter of the Triangle object
     */
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    /**
     * Uses a deep-clone method to create a new Triangle object
     */
    public Object clone() {
        return new Triangle(getColor(), side1, side2, side3);
    }

    /**
     * Implements the user-defined Scalable interface to scale Triangle objects
     */
    public void scale(double f) {
        side1 *= f;
        side2 *= f;
        side3 *= f;
    }

    /**
     * Returns formatted string describing Triangle object
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%-10s\t%-5.2f\t%-5.2f\t%-5.2f\t%-10.2f\t%-8.2f", "Triangle", getColor(), side1, side2, side3,
                getArea(), getPerimeter());
    }
}