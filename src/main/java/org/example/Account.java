package org.example;

public class Account {
    String AccountNumber;
    int balance;

    public Account(String accountNumber, int balance) {
        AccountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
