import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Bank class is a concrete class that can be used to organize BankAccount
 * objects in an array. It comes with various methods for usage on the
 * BankAccount array.
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
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

    // -------------------------------------------
    // start of added material for homework 2
    // -------------------------------------------

    /**
     * 1-arg constructor of the Bank class initializes a new array that can contain
     * up to 100 BankAccount objects. It calls the readAccounts method to fill the
     * array with the contents of a file, whose name is passed as a String to this
     * constructor.
     * 
     * @param filename
     */
    public Bank(String filename) {
        accounts = new BankAccount[100];
        count = readAccounts(filename);
    }

    /**
     * Reads bank account information from a file, creates the bank account objects
     * according to their type, then stores these in an array, 'accounts'.
     * 
     * @param filename
     * @return int representing the number BankAccount objects added
     */
    private int readAccounts(String filename) {
        File file = new File("accounts.txt");
        int inputCount = 0;
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                String tempInput = fileInput.nextLine();
                String[] tempArgs = tempInput.split("\\|");
                BankAccount tempBA = null;
                switch (tempArgs[0].charAt(0)) {
                    case 'I':
                        tempBA = new Investment(Long.parseLong(tempArgs[1]), tempArgs[2],
                                Double.parseDouble(tempArgs[3]), tempArgs[4]);
                        break;
                    case 'C':
                        tempBA = new Checking(Long.parseLong(tempArgs[1]), tempArgs[2],
                                Double.parseDouble(tempArgs[3]));
                        break;
                    case 'S':
                        tempBA = new Savings(Long.parseLong(tempArgs[1]), tempArgs[2],
                                Double.parseDouble(tempArgs[3]), Double.parseDouble(tempArgs[4]));
                        break;
                }
                if (!(tempBA == null)) {
                    accounts[inputCount] = tempBA;
                    inputCount++;
                }
            }
        } catch (FileNotFoundException e) {
            // if file isn't found, array stays empty
            System.out.println("File not found. Bank initialized to empty array.\n");
        } finally {
            // fileInput ~should~ never be null here but I wanted to be sure
            if (fileInput != null) {
                fileInput.close();
            }
        }
        return inputCount;
    }

    /**
     * Writes the list of bank accounts from the array 'accounts' to the file
     * "filename" in a standardized format.
     * 
     * @param filename
     */
    public void saveAccount(String filename) {
        File file = new File(filename);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            for (BankAccount ba : accounts) {
                String accountType = "";
                if (ba instanceof Investment) {
                    Investment baInv = (Investment) ba;
                    accountType = "Investment";
                    writer.println(String.format("%s|%d|%s|%.2f|%s", accountType, baInv.getNumber(), baInv.getOwner(),
                            baInv.getBalance(), baInv.getType()));
                } else if (ba instanceof Checking) {
                    Checking baChk = (Checking) ba;
                    accountType = "Checking";
                    writer.println(String.format("%s|%d|%s|%.2f", accountType, baChk.getNumber(), baChk.getOwner(),
                            baChk.getBalance()));
                } else if (ba instanceof Savings) {
                    Savings baSav = (Savings) ba;
                    accountType = "Savings";
                    writer.println(String.format("%s|%d|%s|%.2f|%.2f", accountType, baSav.getNumber(),
                            baSav.getOwner(), baSav.getBalance(), baSav.getYearlyInterestRate()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file. The array 'accounts' was not saved.\n");
        } finally {
            // writer ~should~ never be null here but I wanted to be sure
            if (writer != null) {
                writer.close();
            }
        }
    }

    // -------------------------------------------
    // end of added material for homework 2
    // -------------------------------------------

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

    // ------------------------------------------------
    // Start of added/modified material for Homework 3
    // ------------------------------------------------

    /**
     * Sorts the array of BankAccount objects by balance by invoking Arrays.sort().
     */
    public void sortAccounts() {
        java.util.Arrays.sort(accounts, 0, size());
    }

    /**
     * Takes a bank account number and removes the related BankAccount object from
     * the list "accounts" in the Bank object.
     * 
     * @param number
     * @return true if BankAccount is found and therefore removed, else false.
     */
    public boolean removeAccount(long number) {
        int accountIndex = findAccountIndex(number);
        if (accountIndex != -1) {
            for (int i = accountIndex; i < accounts.length - 1; i++) {
                accounts[i] = accounts[i + 1];
            }
            count--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an array of BankAccount objects currently held by the Bank object
     * that are "closeable" (as defined in isCloseable() method). Array is always of
     * length 100 to ensure that even the case of all closeable accounts is caught.
     * 
     * @return array of closeable BankAccount objects
     */
    public BankAccount[] getCloseableAccounts() {
        BankAccount[] output = new BankAccount[100];
        int tempCount = 0;
        for (BankAccount ba : accounts) {
            if (ba != null && ba.isCloseable()) {
                output[tempCount] = ba;
                tempCount++;
            }
        }
        return output;
    }

    /**
     * Implements Closeable interface to define what the isCloseable method does for
     * Bank objects.
     * 
     * @return true if the bank has less than 100 BankAccount objects and the total
     *         funds doesn't exceed $2M, else returns false.
     */
    public boolean isCloseable() {
        if (size() < 100 && getTotalFunds() <= 2000000) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds up all BankAccount balances in the Bank object and returns the total
     * funds of the bank.
     * 
     * @return total funds of the bank as a double
     */
    public double getTotalFunds() {
        double totalFunds = 0.0;
        for (BankAccount ba : accounts) {
            if (ba != null) {
                totalFunds += ba.getBalance();
            }
        }
        return totalFunds;
    }

    /**
     * Getter method for the data member "count".
     * 
     * @return integer representing the count of BankAccounts in the bank
     */
    public int size() {
        return count;
    }

    /**
     * Searches the array of BankAccount objects that is currently "held" by the
     * Bank object using the BankAccount number.
     * 
     * @param number
     * @return Index of BankAccount searched for. -1 if not found.
     */
    public int findAccountIndex(long number) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getNumber() == number) {
                return i;
            }
        }
        return -1;
    }

    // ------------------------------------------------
    // End of added/modified material for Homework 3
    // ------------------------------------------------

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
