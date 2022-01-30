import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("(ADD|LIST|EDIT|DELETE)\\s?(\\d{0,})\\s?(.*)",Pattern.CASE_INSENSITIVE);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {

                String group = matcher.group();
//                System.out.println(matcher.group(1));
//                System.out.println(matcher.group(2));
//                System.out.println(matcher.group(3));
                String group2 = matcher.group(2).isEmpty() ? "0" : matcher.group(2);
                int maxSize = Integer.parseInt(group2);

                switch (matcher.group(1).toUpperCase()) {
                    case "LIST":
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                        break;
                    case "ADD":
                        if (matcher.group(2).equals("") || maxSize > todoList.size()){
                            todoList.add(matcher.group(3));
                        } else {
                            todoList.add(maxSize, matcher.group(3));
                        }
                        break;
                    case "DELETE":
                        if (!matcher.group(2).equals("") && maxSize <= todoList.size()){
                            todoList.remove(maxSize);
                        }
                        break;
                    case "EDIT":
                        if (!matcher.group(2).equals("") && maxSize <= todoList.size()) {
                            todoList.set(maxSize, matcher.group(3));
                        }
                        break;
                }
            } else {
                System.out.println("Неизвестная команда");
            }
//            System.out.println(input);
        }
    }
}
