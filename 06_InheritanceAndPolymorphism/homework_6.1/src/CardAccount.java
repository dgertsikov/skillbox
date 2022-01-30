public class CardAccount extends BankAccount {

    public CardAccount(double amount) {
        super(amount);
    }

    @Override
    public boolean minusMoney(double amount) {
        super.minusMoney(amount + (amount * PERCENT));
        return true;
    }
}
