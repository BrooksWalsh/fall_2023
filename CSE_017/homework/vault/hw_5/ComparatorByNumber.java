import java.util.Comparator;

/**
 * ComparatorByNumber implements the interface comparator for type BankAccount
 * and defines the method compare to order BankAccount objects based on the
 * account number.
 *
 * @since 2023-10-13
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByNumber implements Comparator<BankAccount> {

    /**
     * Compare method for account number comparison uses compareTo method
     * for the wrapper class Long.
     * 
     * @param ba1
     * @param ba2
     * @return int
     */
    public int compare(BankAccount ba1, BankAccount ba2) {
        return ((Long) ba1.getNumber()).compareTo(((Long) ba2.getNumber()));
    }
}
