public abstract class Client {

    private double amount;

    public Client(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void plusAmount(double amount){
        plusAmount(amount, 0);
    }

    public void minusAmount(double amount){
        minusAmount(amount, 0);
    }

    public void minusAmount(double amount, double percent) {
        if (this.amount < amount){
            System.out.println("Нельзя выдать денег больше, чем есть на счёте!");
        }
        else{
            this.amount = this.amount - amount - (amount * percent/100);
        }
    }

    public void plusAmount(double amount, double percent) {
        this.amount = this.amount + amount - (amount * percent/100);
    }

    public abstract void showAmount();
}
