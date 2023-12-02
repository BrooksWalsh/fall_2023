import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Discussion/Comparison of the 3 data structures
 * 
 * Insertion:
 * - LinkedList performs in constant time because it always adds to the end of the list.
 * - BST performs slightly worse than logarithmic time because the BST is not balanced.
 *   The BST must ensure that new additions are in the right place, adding complexity.
 * - HashMap performs in constant time because it only ever requires one iteration to
 *   hash the new entry and add it to the end of the bucket. We designed this demo to 
 *   never allow the HashMap to rehash, performance would be worse otherwise.
 * 
 * Searching:
 * - LinkedList performs linearly, and in general, quite inefficiently. Due to the random
 *   nature of the searching, searching requires n/2 iterations on average.
 * - BST shows that our additions were well spaced out and created a fairly balanced tree
 *   structure because searching requires just a bit over logarithmic time complexity.
 * - HashMap searching once again performs in constant time. The max amount of iterations
 *   required to find any item is determined by the length of the longest LinkedList
 *   collision bucket.
 * 
 * Removal:
 * - LinkedList performs linearly, and in general, quite inefficiently once again. Again,
 *   Due to the random nature of our removals, they require n/2 iterations on average.
 * - BST removals perform the same as the two other operations. The logarithmic (plus a
 *   small bit) nature of its performance indicates a well-structures tree.
 * - HashMap removals perform the best once again. Due to the size of our HashMap and 
 *   the way we deal with collisions, the maximum amount of iterations for removals
 *   will once again be the length of the longest LinkedList collision bucket.
 * 
 * What does the maximum number of collisions mean?
 * - This is determined by the length of the longest LinkedList collision bucket that is
 *   in the HashMap data structure. The maximum can be interpreted as the largest amount
 *   iterations a HashMap will require for add/search operations (given no rehashing).
 */

/**
 * Test class for ALA_10 compares various data structures that we've used so far
 * and shows that add/search operations are most efficient on a well created
 * HashMap.
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String, String> hashWords = new HashMap<>(50000);
        readWords(hashWords, "dictionary.txt");
        ArrayList<HashMapEntry<String, String>> words = hashWords.toList();

        // testing the insertion method
        BST<String> bstWords = new BST<>();
        LinkedList<String> llWords = new LinkedList<>();
        hashWords.clear();
        testAdd(words, llWords, bstWords, hashWords);

        // testing contains method
        System.out.println();
        testContains(words, llWords, bstWords, hashWords);

        // testing remove method
        System.out.println();
        testRemove(words, llWords, bstWords, hashWords);

        // show collisions on HashMap
        System.out.println();
        System.out.println("Maximum number of collisions: " + hashWords.collisions());
    }

    /**
     * Test the add method by adding each element of an arraylist to various
     * different data structures.
     * 
     * @param al
     * @param ll
     * @param bst
     * @param hm
     */
    public static void testAdd(ArrayList<HashMapEntry<String, String>> al, LinkedList<String> ll, BST<String> bst,
            HashMap<String, String> hm) {

        int counter = 0;
        int frequency = al.size() / 20;
        int totalLL = 0, totalBST = 0, totalHash = 0;
        System.out.println("Testing the add method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL add", "BST add", "HashMap put");
        for (HashMapEntry<String, String> entry : al) {
            String word = entry.getKey();
            ll.add(word);
            bst.add(word);
            hm.put(word, entry.getValue());
            totalLL += LinkedList.addIterations;
            totalBST += BST.addIterations;
            totalHash += HashMap.putIterations;
            if (counter % frequency == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.addIterations, BST.addIterations,
                        HashMap.putIterations);
            }
            counter++;
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalLL / al.size(), totalBST / al.size(),
                totalHash / al.size());
    }

    /**
     * Test the contains method of various data structures by trying 1000 random
     * searches.
     * 
     * @param al
     * @param ll
     * @param bst
     * @param hm
     */
    public static void testContains(ArrayList<HashMapEntry<String, String>> al, LinkedList<String> ll, BST<String> bst,
            HashMap<String, String> hm) {

        int frequency = 1000 / 20;
        int totalLL = 0, totalBST = 0, totalHash = 0;
        System.out.println("Testing the search method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL contains", "BST contains", "HashMap get");
        for (int i = 0; i < 1000; i++) {
            int index = (int) (Math.random() * al.size());
            String word = al.get(index).getKey();
            ll.contains(word);
            bst.contains(word);
            hm.get(word);
            totalLL += LinkedList.containsIterations;
            totalBST += BST.containsIterations;
            totalHash += HashMap.getIterations;
            if (i % frequency == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.containsIterations,
                        BST.containsIterations,
                        HashMap.getIterations);
            }
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalLL / 1000, totalBST / 1000, totalHash / 1000);
    }

    /**
     * Test the remove method of various data structures by trying 1000 random
     * removals.
     * 
     * @param al
     * @param ll
     * @param bst
     * @param hm
     */
    public static void testRemove(ArrayList<HashMapEntry<String, String>> al, LinkedList<String> ll, BST<String> bst,
            HashMap<String, String> hm) {

        int frequency = 1000 / 20;
        int totalLL = 0, totalBST = 0, totalHash = 0;
        System.out.println("Testing the remove method");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL remove", "BST remove", "HashMap remove");
        for (int i = 0; i < 1000; i++) {
            int index = (int) (Math.random() * al.size());
            String word = al.get(index).getKey();
            ll.remove(word);
            bst.remove(word);
            hm.remove(word);
            totalLL += LinkedList.removeIterations;
            totalBST += BST.removeIterations;
            totalHash += HashMap.removeIterations;
            if (i % frequency == 0) {
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.removeIterations,
                        BST.removeIterations,
                        HashMap.removeIterations);
            }
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalLL / 1000, totalBST / 1000, totalHash / 1000);
    }

    /**
     * Reads dictionary data and stores it to a hashmap.
     * 
     * @param hm
     * @param filename
     */
    public static void readWords(HashMap<String, String> hm, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                hm.put(tokens[0], tokens[1]);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}
