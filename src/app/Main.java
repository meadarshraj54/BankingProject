package app;

import service.BankService;
import service.implementation.ServiceImplementation;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BankService bankService = new ServiceImplementation();
        boolean running = true;

        while(running){
            System.out.println("Welcome to Console based banking App:  ");
            System.out.println("""
                    1) Open Account
                    2) Deposit
                    3) Withdraw
                    4) Transfer
                    5) Account Statement
                    6) List Accounts
                    7) Search Account by Customer Name
                    0) Exit
                """);
            System.out.print("Choice : ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> openAccount(scanner, bankService);

                case "2" -> deposit(scanner, bankService);

                case "3" -> withdraw(scanner, bankService);

                case "4" -> transfer(scanner, bankService);

                case "5" -> statement(scanner, bankService);

                case "6" -> listAccounts(bankService);

                case "7" -> searchAccounts(scanner, bankService);

                case "0" -> running = false;
            }

        }
        
    }

    private static void openAccount(Scanner scanner, BankService bankService) {
        System.out.print("Customer name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Customer email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Account Type (SAVINGS/CURRENT): ");
        String type = scanner.nextLine().trim();

        System.out.print("Initial deposit (optional, blank for 0): ");
        String input = scanner.nextLine().trim();

        double amount = input.isBlank() ? 0.00 : Double.parseDouble(input);

        String accountNumber = bankService.openAccount(name,email,type);
        if (amount > 0)
            bankService.deposit(accountNumber, amount, "Initial Deposit.");
        System.out.println("Account opened: " + accountNumber);
    }

    private static void deposit(Scanner scanner, BankService bankService) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        Double amount = Double.valueOf(scanner.nextLine().trim());

        bankService.deposit(accountNumber, amount, "Deposited");
        System.out.println(amount + " Deposited Successfully.");
    }

    private static void withdraw(Scanner scanner, BankService bankService) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        Double amount = Double.valueOf(scanner.nextLine().trim());

        bankService.withdraw(accountNumber, amount, "Withdraw");

        System.out.println(amount + " Withdraw Successfully.");
    }

    private static void transfer(Scanner scanner, BankService bankService) {
        System.out.print("From Account: ");
        String from = scanner.nextLine().trim();

        System.out.print("To Account: ");
        String to = scanner.nextLine().trim();

        System.out.print("Amount: ");
        String amt = scanner.nextLine().trim();
        if(amt.isBlank()) amt = "0";
        Double amount = Double.valueOf(amt);

        bankService.transfer(from, to, amount, "Transfer");
        System.out.println(amount + " transferred Successfully from " + from + " to " + to);
    }

    private static void statement(Scanner scanner, BankService bankService) {
        System.out.print("Enter Account Number:  ");
        String accountNumber = scanner.nextLine().trim();

        bankService.getStatement(accountNumber).forEach(t -> {
            System.out.println(t.getTimeStamp().format(FORMATTER) + " | " + t.getType() + " | " + t.getAmount());
        });
    }

    private static void listAccounts(BankService bankService) {
        bankService.listAccounts().forEach(acc -> {
            System.out.println(acc.getAccountNumber() + " | " + acc.getAccountType() + " | " + acc.getBalance());
        });
    }

    private static void searchAccounts(Scanner scanner, BankService bankService) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine().trim();
        bankService.searchAccount(name).forEach(acc -> System.out.println(" | " + acc.getAccountNumber() + " | " + acc.getAccountType() + " | " + acc.getBalance()));
    }

}
