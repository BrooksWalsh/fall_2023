import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Discussion of results obtained from comparing the various methods:
/*
 * 
 */

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
