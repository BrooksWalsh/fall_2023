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
public class Bank implements Closeable {

    // data members
    private BankAccount[] accounts;
    private int count;

    // Time Complexity: O(1)
    // Constant time complexity because this constructor initializes an empty array
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

    // Time Complexity: O(n)
    // Linear time complexity because the time depends on amount of BankAccount
    // objects to be initialized by readAccounts (see below).
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

    // Time Complexity: O(n*L) = O(n)
    // Linear time complexity because the time depends on the amount of BankAccount
    // objects (n) initialized when reading the file and also the length (L) of each
    // line in the file.
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

    // Time Complexity: O(n*L) = O(n)
    // Linear time complexity because the time depends on the amount of BankAccount
    // objects (n) iterated through when saving the file and also the length (L) of
    // each line to be written in the new file.
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

    // Time Complexity: O(1)
    // Constant time complexity because there is just one operation in this method
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

    // ------------------------------------------------
    // Start of added/modified material for Homework 4
    // ------------------------------------------------

    // Time Complexity: O(n)
    // Linear time complexity for this recursive method because the number of
    // recursive calls will (on average) increase as the number of objects to search
    // through gets larger.
    /**
     * Searches the array of BankAccount objects that is currently "held" by the
     * Bank object using the BankAccount number.
     * 
     * Now uses recursion to fulfill assignment requirements. This is done by adding
     * an index argument, i.
     * 
     * @param number
     * @return BankAccount object matching number. Null if none are found.
     */
    public BankAccount findAccount(long number, int i) {
        if (accounts[i].getNumber() == number) { // base case 1
            return accounts[i];
        } else if (i == 0) { // base case 2
            return null;
        } else {
            return findAccount(number, --i); // recursive call
        }
    }

    // Time Complexity: O(n) * O(log(n)) = O(nLog(n))
    // Quasilinear time complexity because at each level of recursion, the merge
    // method is called which has linear time complexity based on the size of the
    // arrays that it is merging. On top of this, the number of recursive calls for
    // this method is logarithmic because it will be reducing by half each time it
    // is called. All of this together is O(nLog(n)).
    /**
     * Method to start the mergeSort algorithm from the BankManager class with no
     * arguments. Assumes sorting by owner's name. This method is nearly identical
     * to the one below that is used for the recursive calls. During the initial
     * call, we only want to split the list based on how many BankAccount objects
     * are initialized within the accounts data member. After the initial call, all
     * recursive calls of mergeSort use the length of the sub-array using
     * list.length.
     */
    public void mergeSort() {
        if (size() > 1) { // length==1: base case
            // split list into two halves
            BankAccount[] firstHalf = new BankAccount[size() / 2];
            BankAccount[] secondHalf = new BankAccount[size() - size() / 2];
            // copy the first half of list into the array firstHalf
            System.arraycopy(accounts, 0, firstHalf, 0, size() / 2);
            // copy the second half of list into the array secondHalf
            System.arraycopy(accounts, size() / 2, secondHalf, 0,
                    size() - size() / 2);
            // recursive call on each half
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            // merge the sorted halves back into list
            merge(firstHalf, secondHalf, accounts);
        }
    }

    // Time Complexity: O(nLog(n))
    // Time complexity statement is the same as previous...
    // Quasilinear time complexity because at each level of recursion, the merge
    // method is called which has linear time complexity based on the size of the
    // arrays that it is merging. On top of this, the number of recursive calls for
    // this method is logarithmic because it will be reducing by half each time it
    // is called. All of this together is O(nLog(n)).
    /**
     * Merge sort method. Used for recursive calls, no substantial modifications.
     * 
     * @param list to be sorted
     */
    private void mergeSort(BankAccount[] list) {
        if (list.length > 1) { // length==1: base case
            // split list into two halves
            BankAccount[] firstHalf = new BankAccount[list.length / 2];
            BankAccount[] secondHalf = new BankAccount[list.length - list.length / 2];
            // copy the first half of list into the array firstHalf
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            // copy the second half of list into the array secondtHalf
            System.arraycopy(list, list.length / 2, secondHalf, 0,
                    list.length - list.length / 2);
            // recursive call on each half
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            // merge the sorted halves back into list
            merge(firstHalf, secondHalf, list);
        }
    }

    // Time Complexity: O(n*L) = O(n)
    // Linear time complexity because the time it takes is based on the the number
    // of elements (n) and the length of the strings involved in merging (L).
    /**
     * merge method used by mergeSort. Only modifications were to compare the owners
     * of BankAccount objects rather than integer comparison.
     * 
     * @param list  where the merged elements will be stored
     * @param list1 the first sorted list to be merged
     * @param list2 the second sorted list to be merged
     */
    private void merge(BankAccount[] list1, BankAccount[] list2, BankAccount[] list) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while (list1Index < list1.length && list2Index < list2.length) {
            // copy the smallest element to list
            if (list1[list1Index].getOwner().compareTo(list2[list2Index].getOwner()) < 0)
                list[listIndex++] = list1[list1Index++];
            else
                list[listIndex++] = list2[list2Index++];
        }
        // copy the remaining elements of list1 if list1 is longer than list2
        while (list1Index < list1.length)
            list[listIndex++] = list1[list1Index++];
        // copy the remaining elements of list2 if list2 is longer than list1
        while (list2Index < list2.length)
            list[listIndex++] = list2[list2Index++];
    }

    // ------------------------------------------------
    // End of added/modified material for Homework 4
    // ------------------------------------------------

    // ------------------------------------------------
    // Start of added/modified material for Homework 3
    // ------------------------------------------------

    // Time Complexity: O(nLog(n))
    // Quasilinear time complexity because the javadoc for util.Arrays.sort()
    // indicates that it uses the merge sort algorithm. The javadoc has more details
    // on best/worst case scenarios, but for big-O notation, we are concerned only
    // with worst case meaning O(nLog(n)).
    /**
     * Sorts the array of BankAccount objects by balance by invoking Arrays.sort().
     */
    public void sortAccounts() {
        java.util.Arrays.sort(accounts, 0, size());
    }

    // Time Complexity: O(n*N) = O(n)
    // Linear time complexity because this method is essentially just a beefed up
    // linear search (n) (when calling findAccountIndex) along with a linear removal
    // process (N); this naturally has worst case linear complexity.
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

    // Time Complexity: O(n)
    // Linear time complexity because this method simply iterates through the
    // accounts array with constant time operations in between.
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

    // Time Complexity: O(1)
    // Constant time complexity because there is no iteration and all operations
    // within the method are constant time.
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

    // Time Complexity: O(n)
    // Linear time complexity because this method simply iterates through the
    // accounts array with constant time operations in between.
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

    // Time Complexity: O(1)
    // Constant time complexity because this method is a single operation getter.
    /**
     * Getter method for the data member "count".
     * 
     * @return integer representing the count of BankAccounts in the bank
     */
    public int size() {
        return count;
    }

    // Time Complexity: O(n)
    // Linear time complexity because this method simply iterates through the
    // accounts array with constant time operations in between.
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

    // Time Complexity: O(n*L) = O(n)
    // Linear time complexity because the toString method's complexity is dominated
    // by the for loop that iterates (n) through all the BankAccount objects. The
    // time is also affected by the length of each string (L), but for large values
    // of n, this is irrelevant and linear time complexity is appropriate
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
