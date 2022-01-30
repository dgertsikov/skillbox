import java.util.*;

public class Company {

    //public TreeSet<Employee> employeeList = new TreeSet<>(new EmployeeComparator());
    public HashSet<Employee> employeeList = new HashSet<>();

    public void hire(Employee employee){
        employeeList.add(employee);
        employee.setCompany(this);
    }

    public void hireAll(HashSet<Employee> employeeHashSet){
        employeeList.addAll(employeeHashSet);
        for (Employee emp : employeeHashSet){
            emp.setCompany(this);
        }
    }

    public void fire(Employee employee){
        employeeList.remove(employee);
        employee.setCompany(null);
    }

    public double getIncome(){
        double sumIncome = 0;
        for(Employee emp : employeeList){
            sumIncome = sumIncome + emp.getMonthIncome();
        }
        return sumIncome;
    }

    public ArrayList<Employee> getTopSalaryStaffVersion2(int count){
        ArrayList<Employee> arrayList = new ArrayList<>();
        ArrayList<Employee> ret = new ArrayList<>();

        arrayList.addAll(employeeList);

        arrayList.sort(Comparator.comparing(Employee::getMonthSalary));

        int maxSize = (count > arrayList.size() ? arrayList.size() : count);

        for (int i = arrayList.size()-1; i >= arrayList.size() - maxSize; i--){
            ret.add(arrayList.get(i));
        }
        return ret;
    }
    public ArrayList<Employee> getLowestSalaryStaffVersion2(int count){
        ArrayList<Employee> arrayList = new ArrayList<>();
        ArrayList<Employee> ret = new ArrayList<>();

        arrayList.addAll(employeeList);

        arrayList.sort(Comparator.comparing(Employee::getMonthSalary));
        int maxSize = count > arrayList.size()?arrayList.size():count;

        for (int i = 0; i < maxSize; i++){
            ret.add(arrayList.get(i));
        }
        return ret;
    }

    public ArrayList<Employee> getTopSalaryStaff(int count){

        ArrayList<Employee> arrayList = new ArrayList<>();
        ArrayList<Employee> ret = new ArrayList<>();

        TreeSet<Employee> set = new TreeSet<>((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary()){
                return -1;
            }
            if (o1.getMonthSalary() < o2.getMonthSalary()){
                return 1;
            }
            return 0;
        });
        set.addAll(employeeList);
        arrayList.addAll(set);
        for (int i = 0; i < count; i++){
            ret.add(arrayList.get(i));
        }
        return ret;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count){

        ArrayList<Employee> arrayList = new ArrayList<>();
        ArrayList<Employee> ret = new ArrayList<>();

        TreeSet<Employee> set = new TreeSet<>((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary()){
                return 1;
            }
            if (o1.getMonthSalary() < o2.getMonthSalary()){
                return -1;
            }
            return 0;
        });
        set.addAll(employeeList);
        arrayList.addAll(set);
        for (int i = 0; i < count; i++){
            ret.add(arrayList.get(i));
        }
        return ret;
    }
}
