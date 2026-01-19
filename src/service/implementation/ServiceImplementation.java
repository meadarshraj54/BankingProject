package service.implementation;

import domain.*;
import exceptions.*;
import repository.*;
import service.BankService;
import util.Validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ServiceImplementation implements BankService{

    private final AccountReop accountReop = new AccountReop();
    private final TransactionRepo transactionRepo = new TransactionRepo();
    private final CustomerRepo customerRepo = new CustomerRepo();

    private final Validation<String> validateName = name -> {
        if(name == null || name.isBlank())
            throw new ValidationException("Name is Required.");
    };

    private final Validation<String> validateEmail = email -> {
        if(email == null || !email.contains("@"))
            throw new ValidationException("Enter Valid Email.");

    };

    private final Validation<String> validateType = type -> {
        if(type == null || !(type.equalsIgnoreCase("SAVINGS") || type.equalsIgnoreCase("CURRENT")))
            throw new ValidationException("Invalid Type.");
    };

    private final Validation<Double> validateAmount = amount -> {
        if(amount == null || amount < 0)
            throw new ValidationException("Enter Valid amount.");

    };
    @Override
    public String openAccount(String name, String email, String type) {
        validateName.validate(name);
        validateEmail.validate(email);
        validateType.validate(type);

        String customerId = UUID.randomUUID().toString();
        Customer c = new Customer(customerId, name, email);
        customerRepo.save(c);

        String accountNumber = getAccountNumber();

        Account acc = new Account(accountNumber, customerId, type.toUpperCase(), 0);
        accountReop.save(acc);

        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountReop.findAll().stream().sorted(Comparator.comparing(Account::getAccountNumber)).toList();
    }

    @Override
    public void deposit(String accountNumber, Double amount, String note) {
        validateAmount.validate(amount);
        Account acc = accountReop.findByNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account not found."));

        acc.setBalance(acc.getBalance() + amount);

        Transaction t = new Transaction(UUID.randomUUID().toString(),accountNumber, note, amount, LocalDateTime.now(), Type.DEPOSIT);
        transactionRepo.add(t);
    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        validateAmount.validate(amount);
        Account acc = accountReop.findByNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account not found."));

        if(acc.getBalance() < amount){
            throw new InsufficientBalanceException("Insufficient Balance.");
        }

        acc.setBalance(acc.getBalance() - amount);

        transactionRepo.add(new Transaction(UUID.randomUUID().toString(),accountNumber, note, amount, LocalDateTime.now(), Type.WITHDRAW));

    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        validateAmount.validate(amount);

        if(fromAcc.equals(toAcc))
            throw new ValidationException("Can't transfer to same account.");

        Account from = accountReop.findByNumber(fromAcc).orElseThrow(() -> new AccountNotFoundException("Senders Account not found."));

        Account to = accountReop.findByNumber(toAcc).orElseThrow(() -> new AccountNotFoundException("Receiver Account not found."));

        if(from.getBalance() < amount){
            throw new InsufficientBalanceException("Insufficient Balance.");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        transactionRepo.add(new Transaction(UUID.randomUUID().toString(),fromAcc, note, amount, LocalDateTime.now(), Type.TRANSFER_OUT));

        transactionRepo.add(new Transaction(UUID.randomUUID().toString(),toAcc, note, amount, LocalDateTime.now(), Type.TRANSFER_IN));

    }

    @Override
    public List<Transaction> getStatement(String accountNumber) {
        return transactionRepo.findByAccount(accountNumber).stream()
                .sorted(Comparator.comparing(Transaction::getTimeStamp))
                .toList();
    }

    @Override
    public List<Account> searchAccount(String name) {
        name = (name == null) ? "" : name.toLowerCase();

        List<Account> result = new ArrayList<>();
        for (Customer c : customerRepo.findAll()){
            if(c.name().toLowerCase().contains(name)){
                result.addAll(accountReop.findById(c.id()));
            }
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;
    }

    private String getAccountNumber() {
        int size = accountReop.findAll().size() + 1;
        return String.format("AC%06d", size);
    }
}
