import java.util.Comparator;

/**
 * ComparatorByBalance implements the interface comparator for type BankAccount
 * and defines the method compare to order BankAccount objects based on the
 * account balance.
 *
 * @since 2023-10-13
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByBalance implements Comparator<BankAccount> {

    /**
     * Compare method for Balance comparison uses the natural ordering for
     * BankAccounts defined in a previous assignment that used compareTo() to
     * compare balances.
     * 
     * @param ba1
     * @param ba2
     * @return int
     */
    public int compare(BankAccount ba1, BankAccount ba2) {
        return ba1.compareTo(ba2);
    }
}
