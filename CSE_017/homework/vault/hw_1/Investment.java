import java.util.Random;

/**
 * The Investment class is a subclass of the BankAccount class. It shares mostly
 * the same members, but can be specified to a type of Investment account.
 *
 * @since 2023-09-08
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Investment extends BankAccount {

    // data members
    private String type;

    /**
     * 4-arg constructor of the Investment class uses BankAccount constructor and
     * specifies the type of investment account.
     * 
     * @param number
     * @param owner
     * @param balance
     * @param type
     */
    public Investment(long number, String owner, double balance, String type) {
        super(number, owner, balance);
        this.type = type;
    }

    // setters/getters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * **NOT SIMPLY A GETTER METHOD**
     * Adds or subtracts a profit/loss amount calculated as a percentage of the
     * balance (5%). The probability of having a profit or loss is 50% and
     * determined by random number generation.
     * 
     * @return signed value of the profit/loss amount
     */
    public double getProfitOrLoss() {
        Random rng = new Random();
        double profitLoss = balance * .05;
        // simulating behavior specified by instructions instead of actually generating
        // a number between 0-1
        profitLoss = (rng.nextBoolean()) ? profitLoss : -profitLoss;
        balance += profitLoss; // add/subtract profit/loss amount
        return profitLoss;

    }

    /**
     * Returns a formatted string of the information related to an Investment
     * account object with a label in front. Additionally adds the investment
     * account type to the end of the String.
     */
    @Override
    public String toString() {
        return String.format("%-10s\t%s\t%s\n", "Investment", super.toString(), type);
    }
}
