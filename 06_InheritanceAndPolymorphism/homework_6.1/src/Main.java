import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(0);
        DepositAccount depositAccount = new DepositAccount(0);
        CardAccount cardAccount = new CardAccount(200);

        bankAccount.plusMoney(150);
        bankAccount.minusMoney(20);

        System.out.println("Банковский счет: " + bankAccount.toString());

        depositAccount.plusMoney(100);
        depositAccount.minusMoney(10);

        System.out.println("Депозитный счет: " + depositAccount.toString());

        cardAccount.plusMoney(100);
        cardAccount.minusMoney(10);

        System.out.println("Карточный счет: " + cardAccount.toString());

        if (bankAccount.send(depositAccount, 70)){
            System.out.println("Банковский счет: "+ bankAccount.toString());
            System.out.println("Депозитный счет: " + depositAccount.toString());
        } else {
            System.out.println("Перевод денег не возможен");
        }

    }
}
