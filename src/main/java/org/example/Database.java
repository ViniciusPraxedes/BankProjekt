package org.example;
import java.util.Random;
import java.util.ArrayList;

public class Database {
    ArrayList<User> users = new ArrayList<>();
    Random rand = new Random();
    ArrayList<Account> joeAccounts = new ArrayList<>();
    Account joeAccount1 = new Account("123",300);
    Account joeAccount2 = new Account("321",0);

    ArrayList<Account> userAccounts = new ArrayList<>();
    Account userAccount1 = new Account("123",100);
    Account userAccount2 = new Account("321",100);

    ArrayList<Account> defaultAccounts = new ArrayList<>();
    Account defaultAcc1 = new Account("123",100);
    Account defaultAcc2 = new Account("321",100);

    User joe = new User("Joe","123",joeAccounts);
    User loggedUser = new User("Logged user","123",defaultAccounts);
    User user = new User("User","123",userAccounts);
    public void addStuff(){
        do {
            if (users.contains(user)){
                break;
            }else {
                users.add(joe);
                joeAccounts.add(joeAccount1);
                joeAccounts.add(joeAccount2);

                users.add(loggedUser);
                defaultAccounts.add(defaultAcc1);
                defaultAccounts.add(defaultAcc2);

                users.add(user);
                userAccounts.add(userAccount1);
                userAccounts.add(userAccount2);
                break;
            }
        }while(true);
    }
    public void createAnAccount(String name, String password){
        for(int i = 0; i < users.size(); i++){
            if(name.equals(users.get(i).name) && password.equals(users.get(i).password)){
                System.out.println("User already exists!");
                break;
            }else {
                int accountNumber1 = rand.nextInt(1000, 9999);
                int accountNumber2 = rand.nextInt(1000,9999);

                String accountNumberString1 = Integer.toString(accountNumber1);
                String accountNumberString2 = Integer.toString(accountNumber2);

                ArrayList<Account> accountList = new ArrayList<>();
                accountList.add(new Account(accountNumberString1, 0));
                accountList.add(new Account(accountNumberString2, 0));

                User newUser = new User(name, password, accountList);
                users.add(newUser);
                System.out.println("Name: "+name);
                System.out.println("Password: "+password);
                System.out.println("Account 1: "+accountList.get(0).AccountNumber);
                System.out.println("Account 2: "+accountList.get(1).AccountNumber);

                break;
            }
        }

    }
}
