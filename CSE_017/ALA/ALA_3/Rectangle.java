/**
 * The Rectangle class is a subclass of the Shape class and describes various
 * attributes about a Rectangle object.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Rectangle extends Shape {

    // data members
    private double length, width;

    /**
     * Default constructor of the Rectangle class creates a rectangle with "None"
     * color and side lengths of 1.0
     */
    public Rectangle() {
        super();
        length = width = 1.0;
    }

    /**
     * 3-arg constructor of the Rectangle class creates a rectangle with a specified
     * color, and individually specified length and width.
     * 
     * @param c
     * @param l
     * @param w
     */
    public Rectangle(String c, double l, double w) {
        super(c);
        length = l;
        width = w;
    }

    // getters
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    // setters
    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Returns the area of the rectangle by multiplying length and width
     */
    public double getArea() {
        return length * width;
    }

    /**
     * Returns 2-times the length plus 2-times the width, which is the perimeter of
     * a rectangle
     */
    public double getPerimeter() {
        return (2 * length) + (2 * width);
    }

    /**
     * Implements cloneable by overriding the clone method using a deep clone
     */
    public Object clone() {
        return new Rectangle(getColor(), length, width);
    }

    /**
     * Implements the user-defined Scalable interface to scale Rectangle objects
     */
    public void scale(double f) {
        length *= f;
        width *= f;
    }

    /**
     * Returns formatted string describing Rectangle object.
     */
    public String toString() {
        return String.format("%-10s\t%-10s\t%-5.2f\t%-10.2f\t%-10.2f\t%-8.2f", "Rectangle", getColor(), length, width, getArea(),
                getPerimeter());
    }

}