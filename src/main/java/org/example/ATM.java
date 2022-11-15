package org.example;
import java.util.Scanner;

public class ATM {
    Bank bank = new Bank();
    Scanner sc = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();
    public static Database database = new Database();
    String name = null;
    String password;
    boolean running = true;
    int counter = 0;
    public void run(){
        database.addStuff();
        System.out.println("BANK APPLICATION");
        int choice = 0;
        do {
            choice = inputValidator.inputValidator(choice,"Press 1. to LOG IN \nPress 2. to CREATE AN ACCOUNT");
            if (choice == 1){
                if (logIn()){
                    break;
                }
            }
            if(choice == 2){
                System.out.println("Type in name");
                name = inputValidator.stringValidator(name);
                System.out.println("Type in Password");
                password = sc.nextLine();
                database.createAnAccount(name,password);
            }
        }while (true);


        int howMuchMoney = 0;
        String accountNumber;
        do {
            System.out.println("ACCOUNTS AVAILABLE:");
            for (int i = 0; i < database.loggedUser.accounts.size(); i++){
                System.out.println("Account number: "+database.loggedUser.accounts.get(i).AccountNumber+" Balance: "+database.loggedUser.accounts.get(i).balance);
            }

            choice = inputValidator.inputValidator(choice, "Press 1. to CHECK BALANCE\n" + "Press 2. to DEPOSIT MONEY\n" + "Press 3. to WITHDRAW MONEY");

            if (choice == 1) {
                do {
                    System.out.println("Type in account number");
                    accountNumber = sc.nextLine();
                    int balance = checkAccountBalance(accountNumber);
                    System.out.println("Account: " + accountNumber + " balance is: " + balance);
                    break;
                } while (true);
            }
            if (choice == 2){
                boolean running = true;
                    System.out.println("In what account do you want to deposit?");
                    for (int i = 0; i < database.loggedUser.accounts.size(); i++) {
                        System.out.println("Account: " + database.loggedUser.accounts.get(i).AccountNumber);
                    }

                    do {
                        System.out.println("Type in the account number");
                        accountNumber = sc.nextLine();
                        for (int i = 0; i < database.loggedUser.accounts.size(); i++){
                            if (accountNumber.equals(database.loggedUser.accounts.get(i).AccountNumber)){
                                running = false;
                            }
                        }
                        if (running != false){
                            System.out.println("Unable to find account");
                        }
                    }while(running);
                    howMuchMoney = inputValidator.inputValidator(howMuchMoney, "How much money do you want to deposit?");
                    depositMoney(accountNumber, howMuchMoney);
            }
            if (choice == 3){
                boolean running = true;
                System.out.println("In what account do you want to withdraw?");
                for (int i = 0; i < database.loggedUser.accounts.size(); i++) {
                    System.out.println("Account: " + database.loggedUser.accounts.get(i).AccountNumber);
                }

                do {
                    System.out.println("Type in the account number");
                    accountNumber = sc.nextLine();
                    for (int i = 0; i < database.loggedUser.accounts.size(); i++){
                        if (accountNumber.equals(database.loggedUser.accounts.get(i).AccountNumber)){
                            running = false;
                        }
                    }
                    if (running != false){
                        System.out.println("Unable to find account");
                    }
                }while(running);
                howMuchMoney = inputValidator.inputValidator(howMuchMoney, "How much money do you want to withdraw?");
                withDrawMoney(accountNumber, howMuchMoney);
            }
        }while (true);
    }
    public boolean logIn(){
        System.out.println("Users available:");
        for (int i = 0; i < bank.getUsers().size(); i++){
            System.out.println(bank.getUsers().get(i).name);
        }
        do {
            System.out.println("Type in name:");
            name = inputValidator.stringValidator(name);
            System.out.println("Type in password:");
            password = sc.nextLine();
            for (int i = 0; i < database.users.size(); i++){
                counter++;
                if (database.users.get(i).name.equals(name) && database.users.get(i).password.equals(password)){
                    System.out.println("Welcome "+database.users.get(i).name+"!");
                    database.loggedUser = database.users.get(i);
                    running = false;
                    break;
                }
            }
            if (running != false){
                System.out.println("User not found");
            }
        }while(running);
        return true;
    }
    public int checkAccountBalance(String accountNumber){
        database.addStuff();
        for (int i = 0; i < database.loggedUser.accounts.size(); i++) {
            if (accountNumber.equals(database.loggedUser.accounts.get(i).AccountNumber)) {
                return database.loggedUser.accounts.get(i).balance;
            }
        }
        System.out.println(database.loggedUser.accounts.size());
        System.out.println("Error: Unable to find account");
        return -1;
    }
    public boolean depositMoney(String accountNumber, int howMuchMoney){
        database.addStuff();
        for (int i = 0; i < database.loggedUser.accounts.size(); i++){
            if (database.loggedUser.accounts.get(i).AccountNumber.equals(accountNumber)){
                bank.setAccountMoney(howMuchMoney,accountNumber,"deposit");
                return true;
            }
        }
        System.out.println("Error");
        return false;
    }
    public boolean withDrawMoney(String accountNumber, int howMuchMoney){
        database.addStuff();
        for (int i = 0; i < database.loggedUser.accounts.size(); i++){
            if (database.loggedUser.accounts.get(i).AccountNumber.equals(accountNumber) && howMuchMoney <= database.loggedUser.accounts.get(i).balance ){
                bank.setAccountMoney(howMuchMoney,accountNumber,"withdraw");
                return true;
            }
        }
        System.out.println("Insufficient funds");
        return false;
    }
}
