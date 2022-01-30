public class TopManager implements Employee {

    private static final double fixSalary = 200000;
    private static final double percentBonus = 150;
    private double income = 0;
    private String name;
    private final int maxIncome = 10000000;
    Company company;

    public TopManager(String name)
    {
        this.name = name;
    }

    public static double getSumIncome() {
        return 0;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary + (company.getIncome() > maxIncome ? fixSalary : 0) * (percentBonus/100);
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
    public void setCompany(Company company){
        this.company = company;
    }
}
