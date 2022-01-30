import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            Pattern pattern = Pattern.compile("[^а-яА-Я\\-\\s]");
            Matcher matcher = pattern.matcher(input);
            boolean isTrue = matcher.find();

            String[] string = input.split("\\s");

            if (string.length !=3 || isTrue){
                System.out.println("Введенная строка не является ФИО");
            }
            else {
                System.out.printf("Фамилия: %s\nИмя: %s\nОтчество: %s", string[0], string[1], string[2]);
            }
        }
    }

}