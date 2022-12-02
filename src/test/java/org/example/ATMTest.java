package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ATMTest {

    @Test
    void testRun() {
    }

    @Test
    void logInShouldSuccess() {
        //Given
        ATM atmMock = mock(ATM.class);
        Database database = new Database();
        database.addStuff();
        String expectedName = database.testUser.name;
        String expectedPassword = database.testUser.password;
        String nameInput = "TestUser";
        String passwordInput = "123";
        String actualName = nameInput;
        String actualPassword = passwordInput;
        //When
        if (actualName.equals(expectedName) && actualPassword.equals(expectedPassword)){
            when(atmMock.logIn()).thenReturn(true);
        }else {
            when(atmMock.logIn()).thenReturn(false);
        }
        //Then
        assertTrue(atmMock.logIn());

    }
    @Test
    void loginInvalidName(){
        //Given
        ATM atmMock = mock(ATM.class);
        Database database = new Database();
        database.addStuff();
        String expectedName = database.testUser.name;
        String expectedPassword = database.testUser.password;
        String nameInput = "UserThatDoesntExists";
        String passwordInput = "123";
        String actualName = nameInput;
        String actualPassword = passwordInput;
        //When
        if (actualName.equals(expectedName) && actualPassword.equals(expectedPassword)){
            when(atmMock.logIn()).thenReturn(true);
        }else {
            when(atmMock.logIn()).thenReturn(false);
        }
        //Then
        assertFalse(atmMock.logIn());
    }
    @Test
    void loginInvalidPassword(){
        //Given
        ATM atmMock = mock(ATM.class);
        Database database = new Database();
        database.addStuff();
        String expectedName = database.testUser.name;
        String expectedPassword = database.testUser.password;
        String nameInput = "TestUser";
        String passwordInput = "WrongPassword";
        String actualName = nameInput;
        String actualPassword = passwordInput;
        //When
        if (actualName.equals(expectedName) && actualPassword.equals(expectedPassword)){
            when(atmMock.logIn()).thenReturn(true);
        }else {
            when(atmMock.logIn()).thenReturn(false);
        }
        //Then
        assertFalse(atmMock.logIn());
    }

    @Test
    void checkAccountBalanceShouldSuccess() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.testUser;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        int amountOfMoney = 500;
        //When
        atm.depositMoney(accountNumber,amountOfMoney);
        //Then
        int expectedAmount = 500;
        int actualAmount = atm.checkAccountBalance(ATM.database.loggedUser.accounts.get(0).AccountNumber);
        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void checkAccountBalanceUnableToFindAccount() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.joe;
        String accountNumber = "Invalid Account Number";
        int expectedAmount = -1;
        //When
        int actualAmount = atm.checkAccountBalance(accountNumber);
        //Then
        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void checkAccountBalanceEqualsZero() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.testUser;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        int expectedAmount = 0;
        //When
        int actualAmount = atm.checkAccountBalance(accountNumber);
        //Then
        assertEquals(expectedAmount,actualAmount);
    }



    @Test
    void depositMoneyShouldSuccess() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        int amountToDeposit = 100;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        //When
        atm.depositMoney(accountNumber, amountToDeposit);
        //Then
        int expectedAmount = 100;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);
    }
    @Test
    void depositMoneyAccountNumberNotFound() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        int amountToDeposit = 100;
        String accountNumber = "Invalid Account Number";
        //When
        atm.depositMoney(accountNumber, amountToDeposit);
        //Then
        int expectedAmount = 0;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void depositMoneyAccountIsOnMinusBalance() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        ATM.database.loggedUser.accounts.get(0).balance = -100;
        int amountToDeposit = 100;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        //When
        atm.depositMoney(accountNumber, amountToDeposit);
        //Then
        int expectedAmount = 0;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void withDrawMoneyShouldSuccess() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        ATM.database.loggedUser.accounts.get(0).balance = 100;
        int amountToBeWithDrawn = 100;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        //When
        atm.withDrawMoney(accountNumber, amountToBeWithDrawn);
        //Then
        int expectedAmount = 0;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);
    }
    @Test
    void withDrawAccountNumberNotFound() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        ATM.database.loggedUser.accounts.get(0).balance = 100;
        int amountToBeWithDrawn = 100;
        String accountNumber = "Invalid Account Number";
        //When
        atm.withDrawMoney(accountNumber, amountToBeWithDrawn);
        //Then
        int expectedAmount = 100;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);
    }

    @Test
    void withDrawMoneyAccountBalanceIsZero() {
        //Given
        ATM atm = new ATM();
        ATM.database.addStuff();
        ATM.database.loggedUser = ATM.database.users.get(3);
        int amountToBeWithDrawn = 100;
        String accountNumber = ATM.database.loggedUser.accounts.get(0).AccountNumber;
        //When
        atm.withDrawMoney(accountNumber, amountToBeWithDrawn);
        //Then
        int expectedAmount = 0;
        int actualAmount = ATM.database.loggedUser.accounts.get(0).balance;
        assertEquals(expectedAmount,actualAmount);

    }
}