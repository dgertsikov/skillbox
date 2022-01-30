public class Manager implements Employee {

    private static final double fixSalary = 100000;
    private static final double percentBonus = 5;
    private static double sumIncome = 0;
    private double income = 0;
    private String name;
    Company company;

    public Manager(String name) {
        this.name = name;
        income = 115000 +(Math.random()*35000);
        sumIncome = getSumIncome() + income;
    }

    public static double getSumIncome() {
        return sumIncome;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary + income * (percentBonus/100);
    }

    @Override
    public double getMonthIncome() {
        return income;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
