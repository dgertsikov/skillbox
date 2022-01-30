public class Main {
    public static void main(String[] args) {

        Person person = new Person(100);
        Individual individual = new Individual(100);
        Organization organization = new Organization(100);

        person.plusAmount(50);
        person.minusAmount(20);
        person.showAmount();

        individual.plusAmount(1150);
        individual.minusAmount(0);
        individual.showAmount();

        organization.plusAmount(50);
        organization.minusAmount(10);
        organization.showAmount();
    }
}
