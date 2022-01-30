import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        HashSet<Employee> set = new HashSet<>();

        Company company = new Company();
        Manager manager = new Manager("Middle-" + generateName());

        company.hire(manager);
        for (int i = 0; i<80; i++){
            set.add(new Manager("Middle-" + generateName()));
        }
        company.fire(manager);

        for (int i = 0; i<10; i++){
            set.add(new TopManager("Top-" + generateName()));
        }

        for (int i = 0; i<180; i++){
            set.add(new Operator("Operator-" + generateName()));
        }
        company.hireAll(set);
        System.out.println("До увольнения количество сотрудников: " + company.employeeList.size());
        System.out.println("Топ 10");
        for (Employee emp : company.getTopSalaryStaffVersion2(10)){
            System.out.println(formatter.format(emp.getMonthSalary()));
        }
        System.out.println("30 низких");
        for (Employee emp : company.getLowestSalaryStaffVersion2(30)){
            System.out.println(formatter.format(emp.getMonthSalary()));
        }

        ArrayList<Employee> listDelete = new ArrayList<>();
        listDelete.addAll(company.employeeList);
        for (int i=0; i < listDelete.size()*0.5; i++){
            company.fire(listDelete.get(i));
        }
        System.out.println("После увольнения количество сотрудников: " + company.employeeList.size());
        System.out.println("Топ 10");
        for (Employee emp : company.getTopSalaryStaffVersion2(10)){
            System.out.println(formatter.format(emp.getMonthSalary()));
        }
        System.out.println("30 низких");
        for (Employee emp : company.getLowestSalaryStaffVersion2(30)){
            System.out.println(formatter.format(emp.getMonthSalary()));
        }
    }

    public static String generateName()
    {
        Random rng = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 7;
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
