import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Discussion of big-O time complexity of contains, add, remove
 *  - All three of these method have the same time complexity. This is
 *    O(n) when the input data is sorted and O(nLog(n)) when the input
 *    data is unsorted.
 *  - The reason for the difference is that an unsorted list creates a
 *    linear tree structure, which make all subsequent operations on the
 *    BST the same as linear operations.
 *  - The actual results for each iteration (n) on average ranged from 9-10
 *      - This result makes sense because log_base2(487) = 8.9
 *      - For each element, approximately 9 iterations are needed per
 *        operation which leads to a total complexity of O(nLog(n)).
 * 
 * 
 * Discussion of meaning of values obtained for height and isBalanced
 *  - Height Discussion:
 *      - The height of a balanced tree is log(n).
 *      - Actual results for height were 17 when unsorted and 462 sorted
 *      - The height of 17 isn't log(n) = 9 because it is not balanced 
 *        regardless of sorted vs. unsorted. An unsorted dataset will get
 *        closer to a balanced BST, but has no guarantees.
 *      - The height of 462 is way off from 9 because the data was sorted.
 *        This caused the BST to take on linear properties, and searching
 *        through said BST would be approximately the same as a LinkedList.
 * 
 * - isBalanced Discussion:
 *      - The tree is not balanced in either situation because, by adding
 *        elements randomly, the BST structuring protocol will do nothing
 *        in its current implementation to balance itself. Random data has
 *        a very low percent chance of being balanced. Sorted data has none.
 */

/**
 * Test class for ALA_9 creates a BST and uses it test efficiency of add,
 * contains and remove methods.
 */
public class AnimalTree {
    public static void main(String args[]) {
        // instantiate the BST class for type String
        BST<String> animalBST = new BST<>();
        ArrayList<String> animalAL = new ArrayList<>();

        // run iteration tests
        readAnimals(animalBST, animalAL, "animals.txt");
        System.out.println();
        testContains(animalBST, animalAL);
        System.out.println();
        testRemove(animalBST, animalAL);

        System.out.println("\nBST properties (random data)");
        System.out.println("BST Height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        // make a sorted BST to show how poor it is
        animalBST.clear();
        animalAL.sort(null);
        for (String animal : animalAL) {
            animalBST.add(animal);
        }

        System.out.println("\nBST properties (sorted data)");
        System.out.println("BST Height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());
    }

    /**
     * Main method helper. Reads animal data to the BST data structure from a file.
     * Also tests the add() method iterations and prints them.
     * 
     * @param bst
     * @param al
     * @param filename
     */
    public static void readAnimals(BST<String> bst, ArrayList<String> al, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            int counter = 0, total = 0;
            System.out.println("Testing the BST add(E) method");
            System.out.printf("%-25s\t%-10s\n", "Animal name", "Iterations");
            while (read.hasNextLine()) {
                String animal = read.nextLine();

                // add to BST while counting iterations
                bst.add(animal);
                total += BST.addIterations;

                al.add(animal);

                if (counter % 25 == 0) {
                    System.out.printf("%-25s\t%-10d\n", animal, BST.addIterations);
                }
                counter++;
            }
            System.out.printf("%-25s\t%-10d\n", "Average", total / counter);
            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Main method helper. Tests the iterations for the BST contains method and
     * prints them to console.
     * 
     * @param bst
     * @param al
     */
    public static void testContains(BST<String> bst, ArrayList<String> al) {
        int total = 0;
        System.out.println("Testing the BST contains() method");
        System.out.printf("%-25s\t%-10s\n", "Animal name", "Iterations");
        for (int i = 0; i < 20; i++) {
            int index = (int) (Math.random() * al.size());
            String animal = al.get(index);

            bst.contains(animal);
            System.out.printf("%-25s\t%-10d\n", animal, BST.containsIterations);
            total += BST.containsIterations;
        }
        System.out.printf("%-25s\t%-10d\n", "Average", total / 20);
    }

    /**
     * Main method helper. Tests the iterations for the BST remove method and
     * prints them to console.
     * 
     * @param bst
     * @param al
     */
    public static void testRemove(BST<String> bst, ArrayList<String> al) {
        int total = 0;
        System.out.println("Testing the BST remove(E) method");
        System.out.printf("%-25s\t%-10s\n", "Animal name", "Iterations");
        for (int i = 0; i < 20; i++) {
            int index = (int) (Math.random() * al.size());
            String animal = al.get(index);

            bst.remove(animal);
            System.out.printf("%-25s\t%-10d\n", animal, BST.removeIterations);
            total += BST.removeIterations;
        }
        System.out.printf("%-25s\t%-10d\n", "Average", total / 20);
    }
}
