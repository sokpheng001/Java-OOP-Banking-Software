
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

abstract class Account{
    double riel;
    double dollar;
    protected double amountOfCash;
    protected final Integer password = 168;
    abstract void deposit(double amount);
    abstract void withdrawal(double amount);
    abstract void showBalance();
}
interface ExchangeService{
    void dollarExchange();
    void rielExchange();
    void showInDollar();
    void showInRiel();
}

class SavingAccount extends Account implements ExchangeService{
    @Override
    void deposit(double amount) {
        super.amountOfCash+=amount;
        System.out.println(STR."Amount of \{amount} has been deposited.");
    }
    @Override
    void withdrawal(double amount) {
        super.amountOfCash-=amount;
        System.out.println(STR."Amount of \{amount} has been withdrawn.");
    }
    @Override
    void showBalance() {
        System.out.println(STR."Balance: \{super.amountOfCash}");
    }

    @Override
    public void dollarExchange() {

    }

    @Override
    public void rielExchange() {
        riel = amountOfCash*4000;
    }

    @Override
    public void showInDollar() {
        System.out.println(STR."Balance in Dollar: \{super.amountOfCash}");
    }

    @Override
    public void showInRiel() {
        System.out.println(STR."Balance in Riel: \{super.riel}");
    }
}
class CreditCardAccount extends Account{
    @Override
    void deposit(double amount) {
        System.out.print("Insert password: ");
        if(password==new Scanner(System.in).nextInt()){
            amountOfCash+=amount;
            System.out.println(STR."Amount of \{amount} has been deposited.");
        }
    }
    @Override
    void withdrawal(double amount) {
        System.out.print("Insert password: ");
        if(password==new Scanner(System.in).nextInt()){
            amountOfCash-=amount;
            System.out.println(STR."Amount of \{amount} has been withdrawn.");
        }
    }
    @Override
    void showBalance() {
        System.out.print("Insert password: ");
        if(password==new Scanner(System.in).nextInt()){
            System.out.println(STR."Balance: \{amountOfCash}");
        }
    }
}

public class Main    {
    private final static Account saving = new SavingAccount();
    private final static Account creditCard = new CreditCardAccount();
    private final static ExchangeService exchange = (ExchangeService) saving; // casting one object to be reusable.
    static void ui(){
        System.out.println("==================================");
        System.out.println("1. Saving Account");
        System.out.println("2. Credit Card Account");
        System.out.println("==================================");
        System.out.print("Insert option: ");
        int option = new Scanner(System.in).nextInt();
        switch (option){
            case 1-> {
                saving.deposit(12.0d);
                saving.withdrawal(5.0d);
                saving.showBalance();
                exchange.rielExchange();
                exchange.showInRiel();
            }
            case 2->{
                creditCard.deposit(30.0d);
                creditCard.withdrawal(1.0d);
                creditCard.showBalance();
            }
            default -> {
                System.out.println("None");
            }
        }
    }
    public static void main(String[] args) {
        ui(); // call our function
    }
}