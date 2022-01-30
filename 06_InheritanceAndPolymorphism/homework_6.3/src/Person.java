public class Person extends Client {

    public Person(double amount) {
        super(amount);
    }

    @Override
    public void showAmount(){
        System.out.println("Физическое лицо, баланс " + getAmount());
    }
}
