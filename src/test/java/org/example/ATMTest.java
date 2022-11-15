package org.example;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    void testRun() {
    }

    @Test
    void logIn() {
        Database database = new Database();
        ArrayList<Account>testAccounts = new ArrayList<>();
        Account testAccount1 = new Account("123",100);
        Account testAccount2 = new Account("123",100);
        User testUser = new User("TestUser","123",testAccounts);

        database.addStuff();
        testAccounts.add(testAccount1);
        testAccounts.add(testAccount2);
        database.users.add(testUser);

        String expectedName = "TestUser";
        String expectedPassword = "123";

        String actualName = null;
        String actualPassword = null;

        for(int i = 0; i < database.users.size(); i++){
            if (database.users.get(i).name.equals("TestUser") && database.users.get(i).password.equals("123")){
                actualName = database.users.get(i).name;
                actualPassword = database.users.get(i).password;
                break;
            }
        }

        assertEquals(expectedName,actualName);
        assertEquals(expectedPassword,actualPassword);
    }

    @Test
    void checkAccountBalance() {
        ATM atm = new ATM();
        Database database = new Database();
        ArrayList<Account>testAccounts = new ArrayList<>();
        Account testAccount1 = new Account("123",100);
        Account testAccount2 = new Account("123",100);
        User testUser = new User("TestUser","123",testAccounts);

        testAccounts.add(testAccount1);
        testAccounts.add(testAccount2);
        database.users.add(testUser);
        database.addStuff();

        database.loggedUser = testUser;

        int expectedAmount = 100;
        int actualAmount = atm.checkAccountBalance(database.loggedUser.accounts.get(0).AccountNumber);

        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void depositMoney() {
        ATM atm = new ATM();
        Database database = new Database();
        ArrayList<Account>testAccounts = new ArrayList<>();
        Account testAccount1 = new Account("123",100);
        Account testAccount2 = new Account("123",100);
        User testUser = new User("TestUser","123",testAccounts);

        testAccounts.add(testAccount1);
        testAccounts.add(testAccount2);
        database.users.add(testUser);
        database.addStuff();

        database.loggedUser = testUser;

        String accountNumber = database.loggedUser.accounts.get(0).AccountNumber;
        int howMuchMoney = 100;

        assertTrue(atm.depositMoney(accountNumber,howMuchMoney));
    }

    @Test
    void withDrawMoney() {
        ATM atm = new ATM();
        Database database = new Database();
        ArrayList<Account>testAccounts = new ArrayList<>();
        Account testAccount1 = new Account("123",100);
        Account testAccount2 = new Account("123",100);
        User testUser = new User("TestUser","123",testAccounts);

        testAccounts.add(testAccount1);
        testAccounts.add(testAccount2);
        database.users.add(testUser);
        database.addStuff();

        database.loggedUser = testUser;

        String accountNumber = database.loggedUser.accounts.get(0).AccountNumber;
        int howMuchMoney = 100;

        assertTrue(atm.withDrawMoney(accountNumber,howMuchMoney));
    }
}