package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class BankTest {

    @Test
    void getUsers() {
        Bank bankMock = mock(Bank.class);

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();

        Account account1 = new Account("123",100);
        Account account2 = new Account("123",100);

        accounts.add(account1);
        accounts.add(account2);

        User goku = new User("Goku","123",accounts);
        User vegeta = new User("Vegeta","123",accounts);
        User gohan = new User("Gohan","123",accounts);

        users.add(goku);
        users.add(vegeta);
        users.add(gohan);

        when(bankMock.getUsers()).thenReturn(users);
    }

    @Test
    void setAccountMoney() {
        Bank bankMock = mock(Bank.class);

        Account account1 = new Account("123",100);

        bankMock.setAccountMoney(100,"123","deposit");

        int expectedBalance = 200;
        int actualBalance = account1.balance+100;

        assertEquals(expectedBalance,actualBalance);
    }
}