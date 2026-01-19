package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id, accountNumber, note;
    private double amount;
    private LocalDateTime timeStamp;
    private Type type;

    public Transaction(String id, String accountNumber, String note, double amount, LocalDateTime timeStamp, Type type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.note = note;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Type getType() {
        return type;
    }
}
