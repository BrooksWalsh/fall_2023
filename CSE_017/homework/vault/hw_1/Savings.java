/**
 * The Savings class is a subclass to the BankAccount class. It shares mostly
 * the same members but has an additional member to describe the yearly interest
 * rate that will affect this account.
 *
 * @since 2023-09-08
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Savings extends BankAccount {

    // data members
    private double yearlyInterestRate;

    /**
     * 4-arg constructor of the Savings class uses the constructor of BankAccount as
     * well as sets the yearly interest rate to be used for calculating a savings
     * account with specific information.
     * 
     * @param number
     * @param owner
     * @param balance
     * @param yearlyInterestRate
     */
    public Savings(long number, String owner, double balance, double yearlyInterestRate) {
        super(number, owner, balance);
        this.yearlyInterestRate = yearlyInterestRate;
    }

    // getters/setters
    public double getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    /**
     * **NOT SIMPLY A GETTER METHOD**
     * Adds the amount of the calculated monthly interest to the balance and returns
     * the amount of monthly interest.
     * 
     * @return monthly interest amount
     */
    public double getMonthlyInterest() {
        double monthlyInterest = ((yearlyInterestRate / 12.0) / 100.0) * balance;
        balance += monthlyInterest;
        return monthlyInterest;
    }

    /**
     * Returns a formatted string of the information related to a Savings account
     * object with a label in front. Additionally adds the yearly interest rate to
     * the end of the String.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%s\t%.2f%%\n", "Savings", super.toString(), yearlyInterestRate);
    }
}
