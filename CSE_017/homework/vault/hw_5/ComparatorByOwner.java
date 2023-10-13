import java.util.Comparator;

/**
 * ComparatorByOwner implements the interface comparator for type BankAccount
 * and defines the method compare to order BankAccount objects based on the
 * account owner.
 *
 * @since 2023-10-13
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByOwner implements Comparator<BankAccount> {

    /**
     * Compare method for Owner comparison uses the compareTo() method definition
     * for Strings to order BankAccount objects.
     * 
     * @param ba1
     * @param ba2
     * @return int
     */
    public int compare(BankAccount ba1, BankAccount ba2) {
        return ba1.getOwner().compareTo(ba2.getOwner());
    }
}
