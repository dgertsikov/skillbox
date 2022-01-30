public class Organization extends Client {

    private double percent = 1;

    public Organization(double amount) {
        super(amount);
    }

    @Override
    public void minusAmount(double amount){
        minusAmount(amount, percent);
    }

    @Override
    public void minusAmount(double amount, double percent) {
        super.minusAmount(amount, percent);
    }

    @Override
    public void showAmount(){
        System.out.println("Юридическое лицо, условие снятия: " + percent + "%, баланс " + getAmount());
    }

}
