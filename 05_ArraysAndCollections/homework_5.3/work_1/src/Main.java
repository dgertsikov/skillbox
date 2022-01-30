import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("");
        Pattern pattern = Pattern.compile("(ADD|LIST)\\s?(.*)", Pattern.CASE_INSENSITIVE);
        Pattern emailpattern = Pattern.compile("[\\w-\\.]+@[\\w-]+\\.+[\\w-]{2,4}");

        Scanner scanner = new Scanner(System.in);
        HashSet<String> emailList = new HashSet<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {

                switch (matcher.group(1).toUpperCase()) {
                    case "LIST":
                        for (String email: emailList) {
                            System.out.println(email);
                        }
                        break;
                    case "ADD":
                        Matcher emailMatcher = emailpattern.matcher(matcher.group(2));
                        if (emailMatcher.find()) {
                            emailList.add(matcher.group(2));
                        } else {
                            System.out.println("Неправильный формат Email");
                        }
                        break;
                }
            } else {
                System.out.println("Неизвестная команда");
            }

        }
    }
}
