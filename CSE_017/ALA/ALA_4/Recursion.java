import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        Scanner kbm = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str = kbm.nextLine();
        System.out.println("Enter a character:");
        char c = kbm.nextLine().charAt(0);
        System.out.println(String.format("\"%c\" appears %d times in \"%s\"", c, count(str, c), str));

        // permutations section
        System.out.println("Enter a string:");
        str = kbm.nextLine();
        permutations(str);
        kbm.close();
    }

    /**
     * Uses recursion to count the number of occurrences of a particular char in a
     * given String.
     * 
     * @param str
     * @param c
     * @return
     */
    public static int count(String str, char c) {
        if (str.length() == 0) {
            return 0;
        } else if (str.charAt(0) == c) {
            return 1 + count(str.substring(1), c);
        } else {
            return count(str.substring(1), c);
        }
    }

    /**
     * Recursive method to print all the permutations of a String. Has an overloaded
     * helper method.
     * 
     * @param s
     */
    public static void permutations(String s) {
        permutations(" ", s);
    }

    /**
     * Helper method for the recursive permutations method.
     * 
     * @param s1
     * @param s2
     */
    public static void permutations(String s1, String s2) {
        if (s2.length() == 0) {
            System.out.println(s1);
        } else {
            for (int i = 0; i < s2.length(); i++) {
                permutations(s1 + s2.charAt(i), s2.substring(0, i) + s2.substring(i + 1));
            }
        }
    }
}