import java.time.LocalDate;

public class BankAccount {
    public static final double PERCENT = 0.01;
    public static boolean successFlag = true;

    private double generalAmount;
    private LocalDate dateLastPlus;

    public BankAccount(double amount){
        setDepositDate();
    }

    public boolean minusMoney(double amount){
        if (generalAmount < amount){
            System.out.println("Нельзя выдать денег больше, чем есть на счёте!");
            return false;
        }
        else{
            generalAmount = generalAmount - amount;
            return true;
        }
    }

    public boolean plusMoney(double amount){
        generalAmount = generalAmount + amount;
        setDepositDate();
        return true;
    }

    public boolean send (BankAccount receiver, double amount){
        if (minusMoney(amount)){
            receiver.plusMoney(amount);
            return true;
        } else {
            return false;
        }

    }

    public double showAmount() {
        return generalAmount;
    }

    private void setDepositDate() {
        dateLastPlus = LocalDate.now();
        //dateLastPlus = LocalDate.of(2020,1,1);
    }

    public LocalDate getDepositDate(){
        return dateLastPlus;
    }

    @Override
    public String toString(){
        return "Сумма на счете: " + showAmount() + " Дата пополнения: " + dateLastPlus + " Комиссия: " + PERCENT;
    }

}
