public class GCD {

    static int iter1, iter2, iter3, iter4;

    public static void main(String[] args) {
        System.out.println("Comparison of the execution times");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Number1", "Number2", "Time_1", "Time_2", "Time_3",
                "Time_4");
        compareTimes();

        System.out.println();
        System.out.println("Comparison of the number of iterations");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Number1", "Number2", "Iterations_1", "Iterations_2",
                "Iterations_3",
                "Iterations_4");
        compareIterations();
    }

    /**
     * Compares the total runtime of each GCD finding method. Uses the nanoTime()
     * System method to track the current time at the end of each method and
     * compares their runtimes using subtraction.
     */
    public static void compareTimes() {
        for (int i = 0; i < 20; i++) {
            int number1 = (int) (Math.random() * 1000000);
            int number2 = (int) (Math.random() * 1000000);
            if (number2 > number1) {
                int temp = number1;
                number1 = number2;
                number2 = temp;
            }
            long start = System.nanoTime();
            gcd_1(number1, number2);
            long end1 = System.nanoTime();
            gcd_2(number1, number2);
            long end2 = System.nanoTime();
            gcd_3(number1, number2);
            long end3 = System.nanoTime();
            gcd_4(number1, number2);
            long end4 = System.nanoTime();

            // compare times table
            System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d\n", number1, number2, (end1 - start), (end2 - end1),
                    (end3 - end2),
                    (end4 - end3));
        }
    }

    /**
     * Compares the number of iterations that each GCD finding method takes. Simply
     * uses an integer counter to track the number of iterations and prints them to
     * console.
     */
    public static void compareIterations() {
        for (int i = 0; i < 20; i++) {
            int number1 = (int) (Math.random() * 1000000);
            int number2 = (int) (Math.random() * 1000000);
            if (number2 > number1) {
                int temp = number1;
                number1 = number2;
                number2 = temp;
            }
            gcd_1(number1, number2);
            gcd_2(number1, number2);
            gcd_3(number1, number2);
            iter4 = 0;
            gcd_4(number1, number2);

            // compare times table
            System.out.printf("%-15d %-15d %-15d %-15d %-15d %-15d\n", number1, number2, iter1, iter2, iter3, iter4);
        }
    }

    /**
     * Finding the greatest common divisor of two numbers starting from 1 up
     * 
     * Time(n) = (n-2) * constant time
     * Time Complexity: O(n)
     * 
     * @param m
     * @param n
     * @return
     */
    public static int gcd_1(int m, int n) {
        iter1 = 0;
        int divisor = 1;
        for (int i = 2; i < m && i < n; i++) {
            iter1++;
            if (m % i == 0 && n % i == 0)
                divisor = i;
        }
        return divisor;
    }

    /**
     * Finding the GCD of two numbers tarting from n down (from maximum)
     * 
     * Time(n) = (n) * constant time
     * Time Complexity: O(n)
     * 
     * @param m
     * @param n
     * @return
     */
    public static int gcd_2(int m, int n) {
        iter2 = 0;
        int divisor = 1;
        for (int i = n; i >= 1; i--) {
            iter2++;
            if (m % i == 0 && n % i == 0) {
                divisor = i;
                break;
            }
        }
        return divisor;
    }

    /**
     * Finding the GCD less than or equal to n/2 (divisor of n cannot be greater
     * than n/2)
     * 
     * Time(n) = (n/2) * constant time
     * Time Complexity: O(n)
     * 
     * @param m
     * @param n
     * @return
     */
    public static int gcd_3(int m, int n) {
        iter3 = 0;
        int divisor = 1;
        if (m % n == 0) {
            return n;

        }
        for (int i = n / 2; i >= 1; i--) {
            iter3++;
            if (m % i == 0 && n % i == 0) {
                divisor = i;
                break;
            }
        }
        return divisor;
    }

    /**
     * Euclid's GCD algorithm using recursion
     * 
     * Time(n) = log(n) * constant time
     * Time Complexity: O(log(n))
     * 
     * @param m
     * @param n
     * @return
     */
    public static int gcd_4(int m, int n) {
        iter4++;
        if (m % n == 0)
            return n;
        else
            return gcd_4(n, m % n);
    }
}