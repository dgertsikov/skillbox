public class Individual extends Client {

    private double percent = 0;

    public Individual(double amount) {
        super(amount);
    }

    @Override
    public void plusAmount(double amount){
        percent = (amount < 1000 ? 1 : 0.5);
        plusAmount(amount, percent);
    }

    @Override
    public void plusAmount(double amount, double percent) {
        super.plusAmount(amount, percent);
    }

    @Override
    public void showAmount(){
        System.out.println("ИП, условие пополнения: " + percent + "%, баланс " + getAmount());
    }

}
