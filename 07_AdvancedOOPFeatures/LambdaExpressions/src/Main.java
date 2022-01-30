import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "C:\\Temp\\staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws IOException, ParseException {
        Date date = new SimpleDateFormat(dateFormat).parse("01.01.2017");

        ArrayList<Employee> staff = loadStaffFromFile();

        staff.stream()
                .filter(s -> s.getWorkStart().after(date))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

//        staff.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
//        staff.forEach(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}