/**
 * User-defined exception intended for use with checking a bank account number.
 */
public class InvalidAccountNumber extends Exception {

    /**
     * Default null-message constructor of the InvalidAccountNumber exception
     */
    public InvalidAccountNumber() {
        super();
    }

    /**
     * 1-arg detailed-message constructor of the InvalidAccountNumber exception
     * 
     * @param message
     */
    public InvalidAccountNumber(String message) {
        super(message);
    }
}
