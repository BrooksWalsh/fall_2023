import java.util.Scanner;
import java.util.ArrayList;

/**
 * The BankManager class is a test-class for the various classes that were
 * created/edited in assignment 2. This class contains the main method of this
 * assignment. The BankManager class acts almost the same as the BankManager
 * class provided to us in the previous assignment, but it is modified to work
 * with real-time command line interaction rather than calling the main with
 * arguments.
 * 
 *
 * @since 2023-09-14
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 * @HW5_additions For HW_5 we changed the Bank class to use an ArrayList instead
 *                of an Array. This meant I had to slightly modify several
 *                methods within this class to accommodate the change. Also, now
 *                uses the sortAccounts method with a comparator argument to
 *                sort by various means.
 */
public class BankManager {
    public static void main(String[] args) {

        Scanner kbm = new Scanner(System.in);
        Bank bankSym = new Bank("accounts.txt");
        int operationChoice = 1;

        do {
            System.out.println(
                    "Select an operation:\n1: View accounts\n2: Manage account\n3: Sort accounts by balance\n4: Sort accounts by owner\n5: Sort accounts by number\n6: View closeable accounts\n7: Exit");
            operationChoice = getOperation(kbm, false);

            switch (operationChoice) {
                case 1: // view accounts
                    System.out.println();
                    System.out.print(bankSym);
                    break;
                case 2: // manage account
                    manageAccounts(kbm, bankSym);
                    break;
                case 3: // sort by balance
                    bankSym.sortAccounts(new ComparatorByBalance());
                    System.out.println("Sorting by balance...");
                    break;
                case 4: // sort by owner
                    bankSym.sortAccounts(new ComparatorByOwner());
                    System.out.println("Sorting by owner's name...");
                    break;
                case 5: // sort by number
                    bankSym.sortAccounts(new ComparatorByNumber());
                    System.out.println("Sorting by account number...");
                    break;
                case 6: // closeables
                    manageCloseables(kbm, bankSym);
                    break;
            }
            System.out.println();
        } while (operationChoice != 7);

        System.out.printf("Total funds = $%.2f\nNumber of customers = %d\nThis bank is %s", bankSym.getTotalFunds(),
                bankSym.size(), (bankSym.isCloseable()) ? "closeable" : "not closeable");
        bankSym.saveAccount("accounts.txt");
        kbm.close();
    }

    /**
     * Displays the list of closeable accounts and asks the user if they want to
     * remove them.
     */
    public static void manageCloseables(Scanner kbm, Bank bankSym) {
        System.out.println();
        ArrayList<BankAccount> tempCloseables = bankSym.getCloseableAccounts();
        if (tempCloseables.size() == 0) {
            System.out.println("There are no closeable bank accounts");
            return;
        }
        String closeablesString = "";
        int closeablesCount = 0;
        for (BankAccount ba : tempCloseables) {
            if (ba != null) {
                closeablesString += ba.toString();
                closeablesCount++;
            }
        }
        System.out.printf("There are %d closeable accounts\n%s", closeablesCount, closeablesString);

        String userChoice = getResponse(kbm);
        switch (userChoice.charAt(0)) {
            case 'Y':
            case 'y':
                for (BankAccount ba : tempCloseables) {
                    if (ba != null) {
                        bankSym.removeAccount(ba.getNumber());
                        System.out.println("Account: " + ba.toString() + "Was removed successfully.");
                    }
                }
                break;
        }
    }

    /**
     * Checks if an account number is valid using regex for 10 digits of [1-9].
     * Returns true if matches, else throws InvalidAccountNumber exception.
     * 
     * @param number
     * @return true/false
     * @throws InvalidAccountNumber
     */
    public static boolean checkAccountNumber(long number) throws InvalidAccountNumber {
        String accountNum = number + "";
        if (accountNum.matches("[1-9]{10}")) {
            return true;
        } else {
            throw new InvalidAccountNumber("Invalid Account number ([1-9]{10}). Please try again.");
        }
    }

    /**
     * User enters a bank account number. If account is not found, displays an error
     * message. If found, user can:
     * withdraw/deposit/apply-interest/apply-profitLoss/return.
     * 
     * @param kbm
     * @param bankSym
     */
    public static void manageAccounts(Scanner kbm, Bank bankSym) {
        System.out.print("Enter an account number: ");

        try {
            // NumberFormatException caught below
            long searchNumber = Long.parseLong(kbm.nextLine());
            // InvalidAccountNumber exception caught below
            checkAccountNumber(searchNumber);
            // NullPointException caught rather than asking if (tempAccount == null)
            BankAccount tempAccount = bankSym.findAccount(searchNumber, bankSym.size() - 1);
            int operationChoice = 1;
            System.out.println(String.format("Account found. Balance = $%.2f\n", tempAccount.getBalance()));

            do {
                System.out.println(
                        "Select an operation:\n1: Withdraw\n2: Deposit\n3: Monthly interest\n4: Investment profit/loss\n5: Return to main menu");
                operationChoice = getOperation(kbm, true);

                switch (operationChoice) {
                    case 1:
                        double withdrawAmount = getAmount(kbm);
                        if (withdrawAmount > tempAccount.getBalance()) {
                            System.out
                                    .println(String.format("Withdraw failed. The available balance: $%.2f",
                                            tempAccount.getBalance()));
                        } else {
                            tempAccount.withdraw(withdrawAmount);
                            System.out.println(String.format("Withdraw Successful. The new balance: $%.2f",
                                    tempAccount.getBalance()));
                        }
                        break;
                    case 2:
                        double depositAmount = getAmount(kbm);
                        tempAccount.deposit(depositAmount);
                        System.out.println(
                                String.format("Deposit Successful. The new balance: $%.2f", tempAccount.getBalance()));
                        break;
                    case 3:
                        if (tempAccount instanceof Savings) {
                            Savings tempSavingsAccount = (Savings) tempAccount;
                            System.out.println(
                                    String.format("Monthly interest = $%.2f. The new balance: $%.2f",
                                            tempSavingsAccount.getMonthlyInterest(), tempSavingsAccount.getBalance()));
                        } else {
                            System.out.println("Cannot get the monthly interest. Not a savings account.");
                        }
                        break;
                    case 4:
                        if (tempAccount instanceof Investment) {
                            Investment tempInvestmentAccount = (Investment) tempAccount;
                            double profitLoss = tempInvestmentAccount.getProfitOrLoss();
                            String msgPL = (profitLoss > 0) ? "Profit" : "Loss";
                            System.out.println(String.format(msgPL + " = $%.2f. The new balance: $%.2f", profitLoss,
                                    tempInvestmentAccount.getBalance()));
                        } else {
                            System.out.println("Cannot get the profit/loss. Not an investment account.");
                        }
                        break;
                }
                System.out.println();
            } while (operationChoice != 5);

        } catch (InvalidAccountNumber e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Bank Account number format. Must be 10 digits [1-9].");
        } catch (NullPointerException e) {
            System.out.println("Account number is valid... but this bank currently has no account with this number.");
        }
    }

    /**
     * Input validation method to ensure user only inputs a positive double/int
     * 
     * @param scan
     * @return positive double/int
     */
    public static double getAmount(Scanner scan) {
        double input = 0;
        do {
            System.out.print("Enter amount: ");
            if (scan.hasNextDouble()) {
                input = scan.nextDouble();
                scan.nextLine(); // clear \n
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Input out of bounds. double/int must be positive.");
                }
            } else {
                System.out.println("Invalid input type. Please input a positive double/int.");
                scan.nextLine(); // clear junk
            }
        } while (true);
    }

    /**
     * Input validation method to ensure user only inputs an int between 1-5 or 1-7
     * 
     * @param scan
     * @return int between 1-5 or 1-7
     */
    public static int getOperation(Scanner scan, boolean fiveMax) {
        int input = 0;
        int maximumInput = (fiveMax) ? 5 : 7;
        do {
            System.out.print("Enter operation: ");
            if (scan.hasNextInt()) {
                input = scan.nextInt();
                scan.nextLine(); // clear \n
                if (input >= 1 && input <= maximumInput) {
                    return input;
                } else {
                    System.out.println("Input out of bounds. Integer must be [1-" + maximumInput + "].");
                }
            } else {
                System.out.println("Invalid input type. Please input an integer [1-" + maximumInput + "].");
                scan.nextLine(); // clear junk
            }
        } while (true);
    }

    /**
     * Input validation method to ensure user only inputs Yes or No
     * 
     * @param scan
     * @return String Yes||yes||No||no
     */
    public static String getResponse(Scanner scan) {
        String input = "";
        do {
            System.out.println("Do you want to remove them?");
            input = scan.nextLine();
            if (input.equals("Yes") || input.equals("yes") || input.equals("No") || input.equals("no")) {
                return input;
            } else {
                System.out.println("Input must be a String \"Yes\" or \"No\"");
            }

        } while (true);
    }
}