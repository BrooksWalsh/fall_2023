import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Discussion of Performance:
 * 
 * Theoretical Time/Space Complexity Discussion:
 * 
 *  - Time Complexity Calculation: O(nLog(n) + nLog(n)) = O(2(nLog(n))) = O(nLog(n))
 *      - The time complexity of the HeapSort algorithm overall is O(nLog(n))
 *  - This is fairly straightforward to work out, but a quick explanation is that there
 *    are 2 repeats of the same pattern: iterate through a list with a Log(n) add/remove
 *    method during each iteration. 2 repeats of nLog(n) simplifies to nLog(n)
 * 
 *  - The Space Complexity: Creates new MinHeap with complexity O(n)
 *      - Overall Space Complexity: O(n)
 *  - In some more standard HeapSort implementations, a "Heapify" method is used to change the
 *    current data structure into a Heap, which effectively sorts it in-place and allows for
 *    a space complexity of O(1). Our implementation did not use this method, so we must create
 *    another data structure of equal length, incurring O(n) extra memory allocation.
 * 
 * 
 * Theory Compared with Reality:
 *  - Theory Prediction: O(nLog(n)) = log_base2(500) * (500) = (8.97) * (500) = 4483 Iterations
 *  - Results:
 *      - ArrayList: 5781, 5796, 5738 iterations
 *      - LinkedList: 5781, 5796, 5738 iterations
 *  - Discussion of Difference: (on average: predicted 1288 iterations less than reality)
 *      - The difference between the reality and the theory always shows theory predicting a lower
 *        amount of iterations compared to reality. This can be explained by several factors ranging
 *        from implementation inefficiencies to the way that we chose to measure iterations. Perhaps
 *        most importantly, big-O notation abstracts a lot of detail away from the calculation of the
 *        theoretical time complexity. This way, it is much easier for us to quickly understand and
 *        compare, but also it is not quite as specific.
 */

/**
 * Test class for HW_8 tests the HeapSort algorithm by counting the iterations
 * required for various orders of sorting. Tests on both ArrayList and
 * LinkedList.
 * 
 * @since 2023-11-17
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Student> studentAL = new ArrayList<>();
        LinkedList<Student> studentLL = new LinkedList<>();

        readFile(studentAL, studentLL, "students.txt");
        runMenu(studentAL, studentLL);
    }

    /**
     * Main method helper. Prints menu, allows user to input choice, then sorts &
     * displays lists or shows performance after all sorts have been completed.
     * 
     * @param al ArrayList
     * @param ll LinkedList
     */
    public static void runMenu(ArrayList<Student> al, LinkedList<Student> ll) {
        Scanner kbm = new Scanner(System.in);
        String menuString = "Select an operation:\n1: sort by id\n2: sort by name\n3: sort by gpa\n4: view performance\n5: quit";
        int userChoice = 0, idIterationsAL = 0, nameIterationsAL = 0, gpaIterationsAL = 0, idIterationsLL = 0,
                nameIterationsLL = 0, gpaIterationsLL = 0;
        while (userChoice != 5) {
            System.out.println(menuString);
            userChoice = getOperation(kbm, true);
            System.out.println();
            switch (userChoice) {
                case 1: // sort by ID
                    al.sort(null);
                    idIterationsAL = ArrayList.sortIterations;
                    ll.sort(null);
                    idIterationsLL = LinkedList.sortIterations;
                    displayLists(al, ll, "ID");
                    break;
                case 2: // sort by name
                    ComparatorByName compName = new ComparatorByName();
                    al.sort(compName);
                    nameIterationsAL = ArrayList.sortIterations;
                    ll.sort(compName);
                    nameIterationsLL = LinkedList.sortIterations;
                    displayLists(al, ll, "Name");
                    break;
                case 3: // sort by gpa
                    ComparatorByGPA compGPA = new ComparatorByGPA();
                    al.sort(compGPA);
                    gpaIterationsAL = ArrayList.sortIterations;
                    ll.sort(compGPA);
                    gpaIterationsLL = LinkedList.sortIterations;
                    displayLists(al, ll, "GPA");
                    break;
                case 4: // view performance
                    viewPerformance(idIterationsAL, idIterationsLL, nameIterationsAL, nameIterationsLL, gpaIterationsAL,
                            gpaIterationsLL);
                    break;
            }
            System.out.println();
        }
    }

    /**
     * Prints formatted string to console to show the performance of the various
     * sort method orders done during the current session of the program.
     * 
     * @param idAL idIterationsAL
     * @param idLL idIterationsLL
     * @param nmAL nameIterationsAL
     * @param nmLL nameIterationsLL
     * @param gpAL gpaIterationsAL
     * @param gpLL gpaIterationsLL
     */
    public static void viewPerformance(int idAL, int idLL, int nmAL, int nmLL, int gpAL, int gpLL) {
        System.out.println("Performance of the sort method (# iterations)");
        System.out.printf("%-12s\t%-12s\t%-12s\t%s\n", "List", "by ID", "by Name", "by GPA");
        System.out.printf("%-12s\t%-12d\t%-12d\t%d\n", "ArrayList", idAL, nmAL, gpAL);
        System.out.printf("%-12s\t%-12d\t%-12d\t%d\n", "LinkedList", idLL, nmLL, gpLL);
    }

    /**
     * Prints formatted Strings to console to show both the ArrayList and the
     * LinkedList in various sorted orders.
     * 
     * @param al
     * @param ll
     */
    public static void displayLists(ArrayList<Student> al, LinkedList<Student> ll, String order) {
        Iterator<Student> iter1 = al.iterator();
        Iterator<Student> iter2 = ll.iterator();
        System.out.println("ArrayList sorted by " + order);
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
        System.out.println();
        System.out.println("LinkedList sorted by " + order);
        while (iter2.hasNext()) {
            System.out.println(iter2.next());
        }
    }

    /**
     * Reads from txt file and adds student objects to the Test class ArrayList and
     * LinkedList.
     * 
     * @param al
     * @param ll
     * @param filename
     */
    public static void readFile(ArrayList<Student> al, LinkedList<Student> ll, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String[] tokens = read.nextLine().split(",");
                Student student = new Student(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
                al.add(student);
                ll.add(student);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Input validation method to ensure user only inputs an int between 1-5 or 1-7
     * 
     * @param scan
     * @return int between 1-5 or 1-7
     */
    public static int getOperation(Scanner scan, boolean fiveMax) {
        int input = 0;
        int maximumInput = (fiveMax) ? 5 : 7;
        do {
            System.out.print("Enter operation: ");
            if (scan.hasNextInt()) {
                input = scan.nextInt();
                scan.nextLine(); // clear \n
                if (input >= 1 && input <= maximumInput) {
                    return input;
                } else {
                    System.out.println("Input out of bounds. Integer must be [1-" + maximumInput + "].");
                }
            } else {
                System.out.println("Invalid input type. Please input an integer [1-" + maximumInput + "].");
                scan.nextLine(); // clear junk
            }
        } while (true);
    }
}
