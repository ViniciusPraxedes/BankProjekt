package org.example;

import java.util.ArrayList;

public class User {
    public String name;
    public String password;
    ArrayList<Account> accounts;

    public User(String name, String password, ArrayList<Account> accounts) {
        this.name = name;
        this.password = password;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
