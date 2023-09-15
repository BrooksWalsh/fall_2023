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
