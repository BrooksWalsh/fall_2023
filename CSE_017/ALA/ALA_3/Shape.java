/**
 * The Shape class is an abstract class that is the superClass to various
 * shape-type classes. This class is never instantiated but rather can be
 * thought of as a template for all classes that inherit the Shape class.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public abstract class Shape implements Scalable, Cloneable, Comparable<Shape> {
    private String color;

    protected Shape() {
        this("None");
    }

    protected Shape(String c) {
        color = c;
    }

    // getter
    public String getColor() {
        return color;
    }

    // setter
    public void setColor(String c) {
        this.color = c;
    }

    // abstract methods for area
    public abstract double getArea();

    // abstract method for perimeter
    public abstract double getPerimeter();

    // abstract method for deep-clone of shape
    public abstract Object clone();

    /**
     * Implement compareTo method to work for comparing areas of shapes
     */
    public int compareTo(Shape s) {
        if (this.getArea() == s.getArea()) {
            return 0;
        } else if (this.getArea() > s.getArea()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Callable on instances of subclass objects with type Shape; returns a formatted string with the color
     * of that shape
     */
    @Override
    public String toString() {
        return String.format("%-10s", color);
    }
}
