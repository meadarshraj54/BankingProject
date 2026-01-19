package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepo {
    private final Map<String, List<Transaction>> transactions = new HashMap<>();

    public void add(Transaction t) {
        // check previous transactions and then add new transaction
        //if no previous transactions are found then create a new arraylist and add current transaction
        transactions.computeIfAbsent(t.getAccountNumber(), k -> new ArrayList<>()).add(t);
    }

    public List<Transaction> findByAccount(String accountNumber) {
        return new ArrayList<>(transactions.getOrDefault(accountNumber,  Collections.emptyList()));
    }
}
