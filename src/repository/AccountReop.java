package repository;

import domain.Account;

import java.util.*;

public class AccountReop {
    private final Map<String, Account> accounts = new HashMap<>();

    public void save(Account acc){
        accounts.put(acc.getAccountNumber(), acc);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public List<Account> findById(String id) {
        List<Account> result = new ArrayList<>();
        for(Account a : accounts.values()){
            if(a.getCustomerId().equals(id)){
                result.add(a);
            }
        }
        return result;
    }
}
