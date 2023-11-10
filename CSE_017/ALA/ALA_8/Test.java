import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Comparison and Discussion of the offer and poll methods and their time complexity results
    - Both the offer() method and the poll() method have a worst case time complexity of O(log(n)).
    - In practice, the offer method averages 1 iteration whereas the poll method averages 6 iterations
    in our test. This can be explained by the way that these methods work to re-structure the MinHeap. 
    - The offer method (add) will add the element to the MinHeap and then re-structure by iterating 
    backwards through the heap, from child to parent, ordering the elements until the 
    parent < child condition is met.
    - The poll method (remove), on the other hand, will remove the root element, swap it will the last
    element then percolate down the MinHeap, swapping parents and children until the MinHeap is once
    again structured. Because it starts with the last node in the heap, it will take a substantially
    higher proportion of the worst case (log(n)) iterations. 
    - In summary, both of these methods, being logarithmic in complexity, are far more efficient than
    any of the exponential, linear, or quasilinear methods that we have created thus far. The difference
    in the results relates to the way that the methods must re-structure the heap.
 */

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Patient> emergencyRoom = new PriorityQueue<>();

        readPatients(emergencyRoom, "patients.txt");
        analyzePoll(emergencyRoom);
    }

    /**
     * Main method helper that loops through every Patient in the emergencyRoom
     * PriorityQueue and removes all of them using the .poll() method. There is a
     * total of 500 Patient objects loaded for this assignment, so we use mod 25 to
     * only analyze 20 of the 500 as we loop through them all. Finally the average
     * amount of iterations needed for the poll method is printed.
     * 
     * @param pq
     */
    public static void analyzePoll(PriorityQueue<Patient> pq) {
        System.out.printf("(%-18s%-8s)\tPoll_Iterations\n", "Patient Name", "age/type");
        int counter = 0, total = 0;
        while (!pq.isEmpty()) {
            Patient p = pq.poll();
            total += MinHeap.removeIterations;
            if (counter % 25 == 0) {
                System.out.println(p + "\t" + MinHeap.removeIterations);
            }
            counter++;
        }
        System.out.println("Average Poll Iterations:" + "\t" + total / counter);
    }

    /**
     * Main method helper that reads Patient objects from a csv file and adds them
     * to the given PriorityQueue. We use mod 25 to print the amount of iterations
     * needed for 20 of the 500 times the .offer() method is called. Finally, the
     * average amount of iterations needed for the offer method is printed.
     * 
     * @param pq
     * @param filename
     */
    public static void readPatients(PriorityQueue<Patient> pq, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            int counter = 0, total = 0;
            System.out.printf("(%-18s%-8s)\tOffer_Iterations\n", "Patient Name", "age/type");
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split(",");
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                int type = Integer.parseInt(tokens[2]);
                Patient p = new Patient(name, age, type);
                pq.offer(p);
                total += MinHeap.addIterations;
                if (counter % 25 == 0) {
                    System.out.println(p + "\t" + MinHeap.addIterations);
                }
                counter++;
            }
            System.out.println("Average Offer Iterations:" + "\t" + total / counter);
            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        System.out.println();
    }
}
