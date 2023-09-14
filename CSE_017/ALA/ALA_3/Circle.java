/**
 * The Circle class is a subclass of the Shape class and describes various
 * attributes about a Circle object.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Circle extends Shape {

    // data members
    private double radius;

    /**
     * Default constructor of the Circle object initializes a Circle with "None"
     * color and a radius of 1.0
     */
    public Circle() {
        super();
        radius = 1.0;
    }

    /**
     * 2-arg constructor of the Circle object initializes a Circle with a given
     * color and radius.
     * 
     * @param c
     * @param r
     */
    public Circle(String c, double r) {
        super(c);
        radius = r;
    }

    // getter
    public double getRadius() {
        return radius;
    }

    // setter
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Uses area of a circle formula to calculate the area of the Circle object.
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Uses perimeter of a circle formula to calculate the perimeter of the Circle
     * object
     */
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    /**
     * Implements cloneable by overriding the clone method using a deep clone
     */
    public Object clone() {
        return new Circle(getColor(), radius);
    }

    /**
     * Implements the user-defined Scalable interface to scale Circle objects
     */
    public void scale(double f) {
        radius *= f;
    }

    /**
     * Returns formatted string describing Circle object.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%-10s\t%-15.2f\t\t%-10.2f\t%-8.2f\t", "Circle", getColor(), radius, getArea(), getPerimeter());
    }
}