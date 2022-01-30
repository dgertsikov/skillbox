import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String phone = "+7 (909) 1234567";
        String regex = "\\+?(7|8?)\\D?\\(?(\\d{3})\\)?\\D?(\\d{5})\\D?([\\d]{2})";

        Pattern pattern = Pattern.compile(regex);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            phone = input.replaceAll("([^0-9])+", "");

            if (phone.length() == 10) {
                phone = "7" + phone;
                System.out.println(phone);
            } else if (phone.length() == 11 && (phone.substring(0, 1).equals("7") || phone.substring(0, 1).equals("8"))) {
                phone = "7" + phone.substring(1, 11);
                System.out.println(phone);
            }

            Matcher matcher = pattern.matcher(phone);

            if (matcher.find()) {
//                String group = matcher.group();
//                System.out.println(group);
//                System.out.println("Code: " + matcher.group(1));
//                System.out.println("Value: " + matcher.group(2));
//                System.out.println("Value: " + matcher.group(3));
//                System.out.println("------------------------------");
                String s = matcher.replaceAll("+$1($2)$3-$4");
                System.out.println(s);
            } else {
                System.out.println("Неверный формат номера");
            }
        }
    }

}
