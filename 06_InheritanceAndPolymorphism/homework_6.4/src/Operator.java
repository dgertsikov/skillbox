public class Operator  implements Employee{

    private static final double fixSalary = 30000;
    private String name;
    Company company;

    public Operator(String name) {
        this.name = name;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getMonthIncome() {
        return 0;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
