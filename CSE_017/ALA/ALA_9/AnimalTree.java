import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

        System.out.println("\nBST Height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        // make a sorted BST to show how poor it is
        animalBST.clear();
        animalAL.sort(null);
        for (String animal : animalAL) {
            animalBST.add(animal);
        }

        System.out.println("\nBST Height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());
    }

    public static void readAnimals(BST<String> bst, ArrayList<String> al, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            int counter = 0, total = 0;
            System.out.println("Testing the BST add method");
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

    public static void testContains(BST<String> bst, ArrayList<String> al) {
        int total = 0;
        System.out.println("Testing the BST contains method");
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

    public static void testRemove(BST<String> bst, ArrayList<String> al) {
        int total = 0;
        System.out.println("Testing the BST remove method");
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
