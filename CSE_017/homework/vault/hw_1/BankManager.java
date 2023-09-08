public class BankManager {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            System.out.println("Too few or too many arguments. Program accepts one, two, or three arguments.");
            System.exit(0);
        }
        String operation = args[0];
        long number = 0;
        boolean sort = true;
        double amount = 0;
        if (operation.equals("find") || operation.equals("withdraw") || operation.equals("deposit") ||
                operation.equals("interest") || operation.equals("profit")) {
            number = Long.parseLong(args[1]);
            if (operation.equals("withdraw") || operation.equals("deposit")) {
                amount = Double.parseDouble(args[2]);
            }
        } else if (operation.equals("sort")) {
            if (args[1].equals("number")) {
                sort = true;
            } else if (args[1].equals("balance")) {
                sort = false;
            } else {
                System.out.println("Invalid criterion for sort. Must be number or balance.");
                System.exit(0);
            }
        } else if (!operation.equals("view")) {
            System.out.println("Invalid operation. Must be view, find, or sort");
            System.exit(0);
        }
        Bank myBank = new Bank();
        BankAccount ba = new Checking(6754917864L, "William Burst", 10000.0);
        myBank.addAccount(ba);
        ba = new Savings(7070611070L, "Lara Stevens", 75000.0, 9.25);
        myBank.addAccount(ba);
        ba = new Checking(9512559591L, "Nathan Steward", 24900.9);
        myBank.addAccount(ba);
        ba = new Investment(9269543890L, "Tracey Bold", 19000.0, "Property");
        myBank.addAccount(ba);
        ba = new Savings(4715004908L, "Isabel Truman", 89250.0, 10.5);
        myBank.addAccount(ba);
        ba = new Savings(3037853819L, "Andrew Sullivan", 12734.0, 12.1);
        myBank.addAccount(ba);
        ba = new Investment(1378190104L, "Abigail Clark", 11255.0, "Shares");
        myBank.addAccount(ba);
        ba = new Checking(8039310269L, "George Duck", 29990.0);
        myBank.addAccount(ba);
        ba = new Savings(7024987212L, "Emma Stevens", 31250.0, 8.89);
        myBank.addAccount(ba);
        ba = new Investment(5792112384L, "Lily Daves", 8800.0, "Growth");
        myBank.addAccount(ba);
        switch (operation) {
            case "view": // view accounts
                System.out.println(myBank.toString());
                break;
            case "find": // find account
                BankAccount b = myBank.findAccount(number);
                if (b != null) {
                    System.out.println("Account found: " + b.toString());
                } else {
                    System.out.println("Account not found.");
                }
                break;
            case "sort": // sort accounts
                myBank.sortAccounts(sort);
                System.out.println(myBank.toString());
                break;
            case "withdraw": // withdraw amount from the account
                b = myBank.findAccount(number);
                if (b != null) {
                    if (b.withdraw(amount)) {
                        System.out.println("Withdrawal Successful. The new balance: $" +
                                String.format("%.2f", b.getBalance()));
                    } else {
                        System.out.println("Withdrawal failed. The available balance: $" +
                                String.format("%.2f", b.getBalance()));
                    }
                } else {
                    System.out.println("Account not found.");
                }
                break;
            case "deposit": // Deposit amount in the account
                b = myBank.findAccount(number);
                if (b != null) {
                    b.deposit(amount);
                    System.out
                            .println("Deposit Successful. The new balance: $" + String.format("%.2f", b.getBalance()));
                } else {
                    System.out.println("Account not found.");
                }
                break;
            case "interest": // add the monthly interest to the account balance
                b = myBank.findAccount(number);
                if (b != null) {
                    if (b instanceof Savings) {
                        double interest = ((Savings) b).getMonthlyInterest();
                        System.out.print("Monthly interest = $" + String.format("%.2f", interest));
                        System.out.println(". The new balance: $" + String.format("%.2f", b.getBalance()));
                    } else {
                        System.out.println("Cannot get the monthly interest. Not a savings account.");
                    }
                } else {
                    System.out.println("Account not found.");
                }
                break;
            case "profit": // adds or subtracts profit/loss to/from the account balance
                b = myBank.findAccount(number);
                if (b != null) {
                    if (b instanceof Investment) {
                        double profit = ((Investment) b).getProfitOrLoss();
                        if (profit < 0) {
                            System.out.print("Loss = $" + String.format("%.2f", (-profit)));
                            System.out.println(". The new balance: $" + String.format("%.2f", b.getBalance()));
                        } else {
                            System.out.print("Profit = $" + String.format("%.2f", profit));
                            System.out.println(". The new balance: $" + String.format("%.2f", b.getBalance()));
                        }
                    } else {
                        System.out.println("Cannot get the profit/loss. Not an investment account.");
                    }
                } else {
                    System.out.println("Account not found.");
                }
                break;
        }
    }
}