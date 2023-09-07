import java.util.Scanner;

/**
 * The SeatReservation class simulates reserving/freeing seats on an airplane
 * using the user-defined Airplane class.
 *
 * @since 2023-09-07
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class SeatReservation {

    public static void main(String[] args) {
        Airplane ap = new Airplane("seatmap.txt");
        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        String seatNumber = "";

        do {
            try {
                System.out.println(ap);
                System.out.println("Select an operation");
                System.out.println("1: Reserve a seat");
                System.out.println("2: Free a seat");
                System.out.println("3: Quit");
                operation = keyboard.nextInt();

                switch (operation) {
                    case 1:
                        System.out.println("Enter a seat number");
                        seatNumber = keyboard.next();
                        if (ap.reserveSeat(seatNumber)) {
                            System.out.println("Seat " + seatNumber + " reserved successfully.");
                        } else {
                            System.out.println("Seat " + seatNumber + " already reserved");
                        }
                        break;
                    case 2:
                        System.out.println("Enter a seat number");
                        seatNumber = keyboard.next();
                        if (ap.freeSeat(seatNumber)) {
                            System.out.println("Seat " + seatNumber + " freed successfully");
                        } else {
                            System.out.println("Seat " + seatNumber + " already free");
                        }
                        break;
                    case 3:
                        ap.saveMap("seatmap.txt");
                        System.out.println("Thank you for using my airplane seat reservation program :)");
                        break;
                    default:
                        System.out.println("Invalid Operation. Please try again");
                }
            } catch (InvalidSeatException e) {
                System.out.println(e.getMessage());
            }
        } while (operation != 3);
        keyboard.close();
    }
}
