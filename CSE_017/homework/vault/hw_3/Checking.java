/**
 * The Checking class is a subclass to the BankAccount class. It shares all of
 * its members with BankAccount and can be thought of as a "default" BankAccount
 * instantiation (kindof).
 *
 * @since 2023-09-08
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Checking extends BankAccount {

    /**
     * 3-arg constructor of the Checking class uses the BankAccount constructor to
     * initialize a Checking account with specific information.
     * 
     * @param number
     * @param owner
     * @param balance
     */
    public Checking(long number, String owner, double balance) {
        super(number, owner, balance);
    }

    /**
     * Returns a formatted string of the information related to a Checking account
     * object with a label in front.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%s\n", "Checking", super.toString());
    }
}
