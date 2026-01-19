package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String type);

    List<Account> listAccounts();

    void deposit(String accountNumber, Double amount, String deposited);

    void withdraw(String accountNumber, Double amount, String withdraw);

    void transfer(String from, String to, Double amount, String transfer);

    List<Transaction> getStatement(String accountNumber);

    List<Account> searchAccount(String name);
}
