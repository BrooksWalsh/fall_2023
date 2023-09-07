/**
 * The InvalidSeatException is a simple user-defined exception that is used in
 * "Airplane.java" to catch when the user inputs an invalid seat number.
 * 
 * @since 2023-09-07
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class InvalidSeatException extends Exception {

    /**
     * Default constructor of the InvalidSeatException
     */
    public InvalidSeatException() {
        super();
    }

    /**
     * 1-Arg constructor of the InvalidSeatException allows a custom message.
     * 
     * @param message
     */
    public InvalidSeatException(String message) {
        super(message);
    }
}
