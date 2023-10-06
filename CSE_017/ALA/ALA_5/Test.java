import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Test class is what it's name states; it is a class that's primary purpose
 * is to test the three other classes that were created for this assignment.
 * This class is how the program is run, and it has command-line arguments that
 * must either be the string "states" or "trees".
 *
 * @since 2023-10-5
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Test {
    public static void main(String[] args) {
        Scanner kbm = new Scanner(System.in);
        int selection = 0;
        if (args.length == 0) {
            System.out.println("This program requires a command line argument ('states' or 'trees')");
            System.exit(0);
        }
        if (args[0].equals("states")) {
            ArrayList<Pair<String, String>> states = new ArrayList<>();
            readStates(states, "states.txt");
            do {
                printMenu(true);
                try {
                    selection = Integer.parseInt(kbm.nextLine());
                } catch (NumberFormatException e) {
                    selection = 0;
                }
                switch (selection) {
                    case 1: // view
                        print(states);
                        break;
                    case 2: // search
                        System.out.println("Enter a state:");
                        String name = kbm.nextLine();
                        int index = search(states, name);
                        if (index == -1) {
                            System.out.println("State not found");
                        } else {
                            System.out.println("State found: " + states.get(index));
                        }
                        break;
                    case 3: // sort by name
                        ComparatorByFirst<String, String> compName = new ComparatorByFirst<>();
                        states.sort(compName);
                        print(states);
                        break;
                    case 4: // sort by capital
                        ComparatorBySecond<String, String> compCapital = new ComparatorBySecond<>();
                        states.sort(compCapital);
                        print(states);
                        break;
                    case 5: // exit
                        break;
                    default:
                        System.out.println("Not a valid menu selection. Please input an integer [1-5]");
                }
                System.out.println();
            } while (selection != 5);
        } else if (args[0].equals("trees")) {
            ArrayList<Pair<String, Integer>> trees = new ArrayList<>();
            readTrees(trees, "trees.txt");
            do {
                printMenu(false);
                try {
                    selection = Integer.parseInt(kbm.nextLine());
                } catch (NumberFormatException e) {
                    selection = 0;
                }
                switch (selection) {
                    case 1: // view
                        print(trees);
                        break;
                    case 2: // search
                        System.out.println("Enter a tree name:");
                        String name = kbm.nextLine();
                        int index = search(trees, name);
                        if (index == -1) {
                            System.out.println("Tree not found");
                        } else {
                            System.out.println("Tree found: " + trees.get(index));
                        }
                        break;
                    case 3: // sort by name
                        ComparatorByFirst<String, Integer> compName = new ComparatorByFirst<>();
                        trees.sort(compName);
                        print(trees);
                        break;
                    case 4: // sort by height
                        ComparatorBySecond<String, Integer> compHeight = new ComparatorBySecond<>();
                        trees.sort(compHeight);
                        print(trees);
                        break;
                    case 5: // exit
                        break;
                    default:
                        System.out.println("Not a valid menu selection. Please input an integer [1-5]");
                }
                System.out.println();
            } while (selection != 5);
        } else {
            System.out.println("Invalid type of data (states or trees only)");
        }
        kbm.close();
    }

    /**
     * Generic print method for an ArrayList of objects. As a generic method, an
     * ArrayList with any data type is able to be printed with this method.
     * 
     * @param <E>  generic
     * @param list
     */
    public static <E> void print(ArrayList<E> list) {
        for (E elem : list) {
            System.out.println(elem);
        }
    }

    /**
     * PrintMenu simply cleans up the main method by separating the long strings
     * used in the menu of the program from the bulk of the main method. This method
     * is used for printing both the 'states' and 'trees' menus, so it requires a
     * boolean argument to determine which is intended.
     * 
     * @param statesOn
     */
    public static void printMenu(boolean statesOn) {
        if (statesOn) {
            System.out.println(
                    "Select an operation:\n 1: view states\n 2: search states\n 3: sort states by name\n 4: sort states by capital\n 5: exit");
        } else {
            System.out.println(
                    "Select an operation:\n 1: view trees\n 2: search trees\n 3: sort trees by name\n 4: sort trees by height\n 5: exit");
        }
    }

    /**
     * Reads states data from a file with the assumed delimiter of "|" and 2 unique
     * elements per line (state, capital).
     * 
     * @param list
     * @param filename
     */
    public static void readStates(ArrayList<Pair<String, String>> list, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                String name = tokens[0];
                String capital = tokens[1];
                Pair<String, String> state = new Pair<>(name, capital);
                list.add(state);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Reads trees data from a file with the assumed delimiter of "|" and 2 unique
     * elements per line (treeName, height).
     * 
     * @param list
     * @param filename
     */
    public static void readTrees(ArrayList<Pair<String, Integer>> list, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split("\\|");
                String name = tokens[0];
                Integer height = Integer.parseInt(tokens[1]);
                Pair<String, Integer> tree = new Pair<>(name, height);
                list.add(tree);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Semi-generic search method using simple linear search. Searches an ArrayList
     * of Pair objects that can be of any type. It searches the ArrayList with a
     * generic key that is compared with the first element of each pair.
     * 
     * @param <E1> generic1
     * @param <E2> generic2
     * @param list
     * @param key
     * @return int
     */
    public static <E1, E2> int search(ArrayList<Pair<E1, E2>> list, E1 key) {
        for (int i = 0; i < list.size(); i++) {
            Pair<E1, E2> pair = list.get(i);
            if (pair.getFirst().equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
