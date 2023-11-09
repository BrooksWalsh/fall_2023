import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Discussion of results obtained from comparing the various methods:
/* 
.contains(Object o) method:
    - For this method, the ArrayList and the LinkedList perform identically in all cases. This is because
        both must simply iterate to the item-to-be-checked in a linear fashion and end up having the exact
        same amount of iterations.
.add(int index, E item)
    - For the .add() method, the difference in iterations always satisfies the equation:
        iterations_AL + iterations_LL = list.size(). This equation is satisfied always
        because the ArrayList adds an item by iterating from the back of the list and
        shifting everything up one. In the case of the LinkedList, the add() method
        is only iterating to search for the node with the given index. The two data structures'
        performances must add up to the total size of the list because they start from
        opposite ends. Neither is "more efficient" in general.
.remove(Object o)
    - For the .remove() method, the performance difference is finally extremely noticeable. In the case
        of the ArrayList, the iterations are always going to be exactly the amount of elements in the 
        list. This is because it must first iterate from the front to find the Object o that is supposed
        to be removed. Then it must iterate from the back of the list, shifting elements one-by-one
        until it has reindexed the list without the removed element. In contrast, the LinkedList has no
        index, so must only search for the item to be removed, then change the previous node's "next" value
        to match the node that is subsequent to the removed node. No reindexing required for the LinkedList
        which cuts down the average iterations by a lot. On average, about half the iterations are required
        for the .remove() method on a LinkedList when compared with an ArrayList().
*\

/**
 * Test class for comparing how ArrayLists and LinkedLists differ in time
 * complexity with various methods.
 */
public class AnimalList {
    public static void main(String[] args) {
        ArrayList<String> animalAL = new ArrayList<>();
        LinkedList<String> animalLL = new LinkedList<>();
        readAnimals(animalAL, animalLL, "animals.txt");

        testContains(animalAL, animalLL);
        testAdd(animalAL, animalLL);
        testRemove(animalAL, animalLL);
    }

    /**
     * Takes an ArrayList and a LinkedList and compares the ".contains()" method by
     * comparing the number of iterations required given each data structure.
     * 
     * @param al
     * @param ll
     */
    public static void testContains(ArrayList<String> al, LinkedList<String> ll) {
        System.out.println("Comparing the methods contains(Object o)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        int totalAL = 0, totalLL = 0;
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.contains(randomAnimal);
            ll.contains(randomAnimal);
            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.containsIterations,
                    LinkedList.containsIterations);
            totalAL += ArrayList.containsIterations;
            totalLL += LinkedList.containsIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalAL / 20, totalLL / 20);
    }

    /**
     * Takes an ArrayList and a LinkedList and compares the ".add()" method by
     * comparing the number of iterations required given each data structure.
     * 
     * @param al
     * @param ll
     */
    public static void testAdd(ArrayList<String> al, LinkedList<String> ll) {
        System.out.println("Comparing the methods add(int index, E item)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        int totalAL = 0, totalLL = 0;
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            int addIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.add(addIndex, randomAnimal);
            ll.add(addIndex, randomAnimal);
            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.addIterations,
                    LinkedList.addIterations);
            totalAL += ArrayList.addIterations;
            totalLL += LinkedList.addIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalAL / 20, totalLL / 20);
    }

    /**
     * Takes an ArrayList and a LinkedList and compares the ".remove()" method by
     * comparing the number of iterations required given each data structure.
     * 
     * @param al
     * @param ll
     */
    public static void testRemove(ArrayList<String> al, LinkedList<String> ll) {
        System.out.println("Comparing the methods remove(Object o)");
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        int totalAL = 0, totalLL = 0;
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.remove(randomAnimal);
            ll.remove(randomAnimal);
            System.out.printf("%-30s\t%-15d\t%-15d\n", randomAnimal, ArrayList.removeIterations,
                    LinkedList.removeIterations);
            totalAL += ArrayList.removeIterations;
            totalLL += LinkedList.removeIterations;
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalAL / 20, totalLL / 20);
    }

    /**
     * Reads a file that is structured line-by-line and adds each line to both an
     * ArrayList and a LinkedList
     * 
     * @param al
     * @param ll
     * @param filename
     */
    public static void readAnimals(ArrayList<String> al, LinkedList<String> ll, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String animal = read.nextLine();
                al.add(animal);
                ll.add(animal);
            }
            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
