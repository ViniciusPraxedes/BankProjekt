package org.example;
import java.util.ArrayList;
public class Bank {
    public ArrayList<User> getUsers(){
        return ATM.database.users;
    }
    public User getUserByUserName(String name){
        Database database = new Database();
        User tempUser = new User("temp","123",database.defaultAccounts);
        for(int i = 0; i < database.users.size(); i++){
            if (database.users.contains(name) && database.users.get(i).name.equals(name)){
                tempUser = database.users.get(i);
            }
        }
        return tempUser;
    }
    public void setAccountMoney(int howMuchMoney, String accountNumber, String typeOfOperation){
        for (int i = 0; i < ATM.database.loggedUser.accounts.size(); i++){
            if(typeOfOperation.equalsIgnoreCase("withdraw") && ATM.database.loggedUser.accounts.get(i).AccountNumber.equals(accountNumber)){
                ATM.database.loggedUser.accounts.get(i).balance -= howMuchMoney;
                System.out.println("Withdraw successfully made");
                System.out.println("Balance: "+ATM.database.loggedUser.accounts.get(i).balance);
                break;
            }
            if (typeOfOperation.equalsIgnoreCase("deposit") && ATM.database.loggedUser.accounts.get(i).AccountNumber.equals(accountNumber)){
                ATM.database.loggedUser.accounts.get(i).balance += howMuchMoney;
                System.out.println("Deposit successfully made");
                System.out.println("Balance: "+ATM.database.loggedUser.accounts.get(i).balance);
                break;
            }
        }
    }
}
