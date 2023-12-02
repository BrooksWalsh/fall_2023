import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Discussion of Differences in sets of Cities
 * 
 * The ordering that is set for the BST determines how nodes are added with 
 * the add method. Because of this, when we select different operations in
 * this test class, which use different ordering, the add method will be
 * checking for different data members for its structure. Specifically,
 * there are less cities is less for operation 1 (sort by name) because
 * the BST simply returns false if there is already a node with the same
 * exact value as determined by the ordering of the BST. Due to there being
 * some duplicate entries by name in the cities.txt file, the BST contains 
 * less elements when sorting this way because it won't contain those
 * duplicate entries. 
 * 
 * In comparison, the BST is (pretty much) fully representing the data when
 * sorting my latitude or longitude because there are effectively no cities
 * in the data set with the same latitude or longitude to that precision.
 * Thus, for these sorting methods, there are very few, or even no duplicates.
 * 
 * For example, if we were to sort by state, there should be about 50 entries
 * in the TreeSet (plus misspellings like Utah and territories perhaps).
 */

/**
 * Test class for HW_9 was provided.
 */
public class Test {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int selection = 0;
        TreeSet<City> cityTree;
        do {
            System.out.println("\nSelect an operation:");
            System.out.println("1: USA Cities by name");
            System.out.println("2: USA Cities by latitude");
            System.out.println("3: USA Cities by longitude");
            System.out.println("4: Exit");
            selection = keyboard.nextInt();
            switch (selection) {
                case 1: // natural ordering
                    cityTree = new TreeSet<>();
                    readCities(cityTree, "cities.txt");
                    System.out.println("\nNumber of cities in the set: " + cityTree.size());
                    System.out.println("Is \"Philadelphia\" in the set? "
                            + cityTree.contains(new City("Philadelphia", null, 0.0, 0.0)));
                    System.out.println(
                            "Is \"Paris\" in the set? " + cityTree.contains(new City("Paris", null, 0.0, 0.0)));
                    cityTree.inorder();
                    break;
                case 2: // ordering by latitude
                    cityTree = new TreeSet<>(new ComparatorByLatitude());
                    readCities(cityTree, "cities.txt");
                    System.out.println("\nNumber of cities in the set: " + cityTree.size());

                    cityTree.inorder();
                    System.out.println("\nCity with the lowest latitude:\n" + cityTree.first());
                    System.out.println("City with the highest latitude:\n" + cityTree.last());
                    break;
                case 3: // ordering by longitude
                    cityTree = new TreeSet<>(new ComparatorByLongitude());
                    readCities(cityTree, "cities.txt");
                    System.out.println("\nNumber of cities in the set: " + cityTree.size());
                    cityTree.inorder();
                    System.out.println("\nCity with the lowest longitude:\n" + cityTree.first());
                    System.out.println("City with the highest longitude:\n" + cityTree.last());
                    break;

            }
        } while (selection != 4);
        keyboard.close();
    }

    /**
     * Reads City data from a file and adds City objects to a TreeSet data
     * structure. (Provided)
     * 
     * @param tree
     * @param filename
     */
    public static void readCities(TreeSet<City> tree, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                // System.out.println(line);
                String[] tokens = line.split(",");
                City c = new City(tokens[0],
                        tokens[1],
                        Double.parseDouble(tokens[2]),
                        Double.parseDouble(tokens[3]));
                tree.add(c);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}