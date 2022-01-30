import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static String movementFile = "C:/Temp/movementList.csv";

    public static void main(String[] args)
    {
        Movements movements = new Movements(movementFile);

        System.out.println("Сумма расходов: " + movements.getExpenseSum() + " руб.");
        System.out.println("Сумма доходов: " + movements.getIncomeSum() + " руб.");

        System.out.println("\nСумма расходов по организациям: ");

//        movements.account.stream().filter(a -> a.getCredit()>0).sorted(Comparator.comparing(Account::getShortDesc)).forEach(System.out::println);
//
//        System.out.println("\nСумма доходов по организациям: ");
//        movements.account.stream().filter(a -> a.getDebet()>0).sorted(Comparator.comparing(Account::getShortDesc)).forEach(System.out::println);

        Map<String, Double> result = movements.account.stream()
                .filter(a -> a.getCredit()>0)
                .collect(Collectors
                        .groupingBy(Account::getShortDesc, Collectors.summingDouble(Account::getCredit)));

        for(String key : result.keySet()){
            System.out.println(key + "\t" + result.get(key));
        }

    }
}
