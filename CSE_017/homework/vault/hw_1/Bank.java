public class Bank {

    // data members
    private BankAccount[] accounts;
    private int count;

    /**
     * The default constructor of the Bank class has no arguments and initializes a
     * new array that can potentially contain up to 50 BankAccount objects. It also
     * initializes count to 0 to keep track of how many BankAccounts are added.
     */
    public Bank() {
        accounts = new BankAccount[50];
        count = 0;
    }

    /**
     * Adds the BankAccount object passed as an argument into the array of
     * BankAccounts that is being "held" by the Bank object.
     * 
     * @param ba
     */
    public void addAccount(BankAccount ba) {
        accounts[count] = ba;
        count++;
    }

    /**
     * Searches the array of BankAccount objects that is currently "held" by the
     * Bank object using the BankAccount number.
     * 
     * @param number
     * @return BankAccount object matching number. Null if none are found.
     */
    public BankAccount findAccount(long number) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getNumber() == number) {
                return accounts[i];
            }
        }
        return null;
    }

    /**
     * Sorts the array "accounts" based on the account number or the balance
     * (indicated by boolean arg). If true, sorts by account number; if false, sorts
     * by account balance. Both sort in ascending order.
     * 
     * @param numberOrBalance
     */
    public void sortAccounts(boolean numberOrBalance) {
        for (int i = 1; i < count; i++) {
            // Insert element i in the sorted sub-accounts
            BankAccount currentAccount = accounts[i];
            int j = i;

            if (numberOrBalance) { // sort by number if true
                long currentVal = currentAccount.getNumber();
                while (j > 0 && currentVal < (accounts[j - 1].getNumber())) {
                    // Shift element (j-1) into element (j)
                    accounts[j] = accounts[j - 1];
                    j--;
                }
            } else { // sort by balance if false
                double currentVal = currentAccount.getBalance();
                while (j > 0 && currentVal < (accounts[j - 1].getBalance())) {
                    // Shift element (j-1) into element (j)
                    accounts[j] = accounts[j - 1];
                    j--;
                }
            }
            // Insert currentAccount at position j
            accounts[j] = currentAccount;
        }
    }

    /**
     * Returns a formatted string that contains all of the BankAccount objects
     * currently "held" by this Bank object. Formatted string includes an initial
     * row that indicates the column names.
     */
    @Override
    public String toString() {
        String out = String.format("%-10s\t%-10s\t%-30s\t%-10s\t%s\n", "Type", "Number", "Owner", "Balance",
                "Interest/InvestmentType");
        for (int i = 0; i < count; i++) {
            out += accounts[i].toString();
        }
        return out;
    }
}
