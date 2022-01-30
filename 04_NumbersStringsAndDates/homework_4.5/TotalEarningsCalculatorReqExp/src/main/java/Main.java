import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int earn = calculateSalarySum("Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей");
        System.out.println("Сумма заработка всех друзей " + earn + " руб.");

    }

    public static int calculateSalarySum(String text) {
        //TODO: реализуйте метод
        int ret = 0;
        Pattern pat = Pattern.compile("\\d+");
        Matcher matcher = pat.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
            ret = ret + Integer.parseInt(matcher.group());
        };
        return ret;
    }

}