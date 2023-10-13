import java.util.Stack;
import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Test class contains the main method for ALA_6 and is used to perform 2
 * separate tasks:
 * 1) Process postfix expressions and display results
 * 2) Simulate a printRequest priority queue
 * 
 * Note: I'm curious how the sample output code decided to format the times; I
 * tried floor, ceil, and round. Ended up going with Math.round() because
 * although ceil() makes the most sense to me, round() got the closest results
 * to the sample. I am still currently off by 2 seconds because of round, but it
 * would be off by more doing any other method.
 *
 * @since 2023-10-12
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Test {
    public static void main(String[] args) {
        // using the stack
        Stack<Integer> postfixStack = new Stack<>();
        Scanner keyb = new Scanner(System.in);
        String expression = "", userResponse = "";
        System.out.println(
                "Evaluating postfix expressions\n-------------------------------------------------------------");
        do {
            try {
                postfixStack = new Stack<>();
                System.out.println("Enter a postfix expression:");
                expression = keyb.nextLine();
                String[] tokens = expression.split(" ");
                for (String token : tokens) {
                    if (token.matches("\\d{1,}")) {
                        int operand = Integer.parseInt(token);
                        postfixStack.push(operand);
                    } else {
                        int operand1 = postfixStack.pop();
                        int operand2 = postfixStack.pop();
                        switch (token) {
                            case "+":
                                postfixStack.push(operand2 + operand1);
                                break;
                            case "-":
                                postfixStack.push(operand2 - operand1);
                                break;
                            case "*":
                                postfixStack.push(operand2 * operand1);
                                break;
                            case "/":
                                postfixStack.push(operand2 / operand1);
                                break;
                            default:
                                System.out.println("Invalid operation");
                        }
                    }
                }
                int result = postfixStack.pop();
                if (postfixStack.isEmpty()) {
                    System.out.println("Result = " + result);
                } else {
                    System.out.println("Error: Malformed postfix expression");
                }
                userResponse = getResponse(keyb);
            } catch (EmptyStackException e) {
                System.out.println("Error: Malformed postfix expression");
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!userResponse.equals("No") && !userResponse.equals("no"));
        System.out.println();

        // using the PriorityQueue
        System.out.println(
                "Summary of the print requests\n-------------------------------------------------------------");
        PriorityQueue<PrintRequest> printingQ = new PriorityQueue<>();
        try {
            Scanner read = new Scanner(new File("requests.txt"));
            while (read.hasNext()) {
                int id = read.nextInt();
                String group = read.next();
                long size = read.nextLong();
                PrintRequest pr = new PrintRequest(id, group, size);
                printingQ.offer(pr);
            }
            read.close();

            long speed = 10000; // printer speed bytes/second
            long timeSum = 0;

            System.out
                    .println(String.format("%7s\t\t%5s\t\t%4s\t\t%15s", "User ID", "Group", "Size", "Completion Time"));
            while (!printingQ.isEmpty()) {
                PrintRequest pr = printingQ.poll();
                long time = Math.round((double) pr.getSize() / speed);
                timeSum += time;
                System.out.println(pr + "\t\t" + formatTime(time));
            }
            System.out.println("\n\nTotal Printing time: " + formatTime(timeSum));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        keyb.close();
    }

    /**
     * Takes as an argument a time formatted in seconds, returns the same amount of
     * time but represented in a formatted string with days:hours:minutes:seconds.
     * 
     * @param time in seconds
     * @return formatted string
     */
    public static String formatTime(long time) {
        long days = 0, hours = 0, minutes = 0, seconds = 0;
        if (time > 86400) { // days
            days = time / 86400;
            time -= 86400 * days;
        }
        if (time > 3600) { // hours
            hours = time / 3600;
            time -= 3600 * hours;
        }
        if (time > 60) { // minutes
            minutes = time / 60;
            time -= 60 * minutes;
        }
        seconds = time;
        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }

    /**
     * Input validation method to ensure user only inputs Yes or No
     * 
     * @param scan
     * @return
     */
    public static String getResponse(Scanner scan) {
        String input = "";
        do {
            System.out.println("Do you want to evaluate another postfix expression? (Yes/No)");
            input = scan.nextLine();
            if (input.equals("Yes") || input.equals("yes") || input.equals("No") || input.equals("no")) {
                return input;
            } else {
                System.out.println("Input must be a String \"Yes\" or \"No\"");
            }

        } while (true);
    }
}
