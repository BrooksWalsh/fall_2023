public class TestShapes {
    public static void main(String[] args) {

        // initialize the array and add 4 shapes to test the 4 classes
        Shape[] shapes = new Shape[8];
        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        shapes[2] = new Rectangle("Red", 5.0, 3.0);
        shapes[3] = new Pentagon("Yellow", 7.0);
        
        // clone these objects to repeat array using downcasting
        shapes[4] = (Shape) (shapes[0].clone()); // circle
        shapes[5] = (Shape) (shapes[1].clone()); // triangle
        shapes[6] = (Shape) (shapes[2].clone()); // rectangle
        shapes[7] = (Shape) (shapes[3].clone()); // pentagon

        // modify the cloned objects
        shapes[4].scale(2.0);
        shapes[5].setColor("Orange");
        ((Rectangle) shapes[6]).setLength(10.0);
        ((Pentagon) shapes[7]).setSide(4.0);

        System.out.println("Original list");
        printShapes(shapes);

        System.out.println("\nSorted list");
        java.util.Arrays.sort(shapes);
        printShapes(shapes);
    }

    /**
     * Prints an array of Shape objects
     * 
     * @param list
     */
    public static void printShapes(Shape[] list) {
        System.out.println(String.format("%-10s\t%-10s\t%-10s\t\t%-10s\t%-8s\t", "Shape", "Color", "Dimensions", "Area", "Perimeter"));
        for (Shape s : list) {
            System.out.println(s);
        }
    }
}