import java.util.Scanner;

// Class representing a bank account
class BankAccount {
    private String userId;
    private String pin;
    private double balance;

    // Constructor
    public BankAccount(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("Current Balance: $" + balance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("Current Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Method to transfer money to another account
    public void transfer(BankAccount recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transferred: $" + amount);
            System.out.println("Current Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Method to display transaction history (placeholder)
    public void showTransactionHistory() {
        System.out.println("Transaction History: (placeholder)");
    }
}

// Class representing the ATM
class ATM {
    private static final Scanner scanner = new Scanner(System.in);

    // Method to authenticate user
    public static BankAccount authenticateUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        // Assuming you have a method to retrieve user details from a database
        BankAccount userAccount = getUserFromDatabase(userId, pin);

        if (userAccount != null) {
            System.out.println("Authentication successful!");
            return userAccount;
        } else {
            System.out.println("Authentication failed. Please try again.");
            return null;
        }
    }

    // Main method to run the ATM functionality
    public static void main(String[] args) {
        BankAccount userAccount = authenticateUser();

        if (userAccount != null) {
            int choice;

            do {
                System.out.println("\nATM Operations:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        userAccount.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        userAccount.withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.nextLine();
                        BankAccount recipient = getUserFromDatabase(recipientId);
                        if (recipient != null) {
                            System.out.print("Enter transfer amount: $");
                            double transferAmount = scanner.nextDouble();
                            userAccount.transfer(recipient, transferAmount);
                        } else {
                            System.out.println("Recipient not found!");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting ATM. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        }
    }

    // Placeholder method to simulate retrieving user details from a database
    private static BankAccount getUserFromDatabase(String userId, String pin) {
        // Implement this method to connect to your database and retrieve user details
        // For simplicity, returning a new BankAccount with hardcoded details
        return new BankAccount(userId, pin, 1000.0);
    }

    // Placeholder method to simulate retrieving user details from a database using only userId
    private static BankAccount getUserFromDatabase(String userId) {
        // Implement this method to connect to your database and retrieve user details
        // For simplicity, returning a new BankAccount with hardcoded details
        return new BankAccount(userId, "1234", 2000.0);
    }
}
