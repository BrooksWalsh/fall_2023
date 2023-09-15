/**
 * The BankAccount class is the parent class to the various account types that
 * are created for this assignment. It contains standard information about any
 * bank account including account number, holder name, and account balance. This
 * class contains simple methods (deposit/withdrawal) that can change the
 * balance.
 *
 * @since 2023-09-08
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public abstract class BankAccount {

    // data members
    private long number;
    private String owner;
    protected double balance;

    /**
     * 3-arg constructor for the BankAccount class. This class is abstract, thus
     * this constructor will never be called directly; instead this constructor will
     * be called by the subclasses to BankAccount.
     * 
     * @param number
     * @param owner
     * @param balance
     */
    public BankAccount(long number, String owner, double balance) {
        this.number = number;
        this.owner = owner;
        this.balance = balance;
    }

    /**
     * Getter method for account number data member
     * 
     * @return long accountNumber
     */
    public long getNumber() {
        return number;
    }

    /**
     * Getter method for account balance data member
     * 
     * @return double accountBalance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Getter method for the account owner data member
     * 
     * @return String accountOwner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter method for the account number data member
     * 
     * @param number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Adds the amount passed as an argument to the total balance of an account.
     * 
     * @param amount
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Returns true if amount passed as an argument is less than the total balance
     * of an account, then subtracts that amount. If the balance is not enough for
     * the withdrawal, returns false.
     * 
     * @param amount
     * @return true/false
     */
    public boolean withdraw(double amount) {
        if (balance > amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a formatted string with the information about a generic BankAccount
     * object. As this is an abstract class, this method will only be called by
     * subclasses of BankAccount.
     */
    @Override
    public String toString() {
        return String.format("%-10d\t%-30s\t$%,-10.2f", number, owner, balance);
    }
}
