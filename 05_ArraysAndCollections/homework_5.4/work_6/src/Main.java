import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, String> phoneBook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(.*)\\s(.*)", Pattern.CASE_INSENSITIVE);
        String name = "";

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //System.out.println(validatePhone(input));

            if (input.equals("LIST")) {
                printMap(phoneBook);
            } else {
                String phone = validatePhone(input);

                if(!phone.equals("error")) {

                    if(phoneBook.containsKey(phone)) {
                        System.out.println("Телефон " + phone + " Имя " + phoneBook.get(phone));
                    } else {
                        System.out.println("Введите имя:");
                        input = scanner.nextLine();
                        phoneBook.put(phone, input);
                    }
                } else {
                    if (phoneBook.containsValue(input)){
                        System.out.println("Телефон " + getPhoneForName(phoneBook, input) + " Имя " + input);
                    } else {
                        name = input;
                        System.out.println("Введите телефон:");
                        input = scanner.nextLine();

                        phone = validatePhone(input);
                        if(!phone.equals("error")) {
                            phoneBook.put(phone, name);
                        } else {
                            System.out.println("Неправильный номер");
                        }
                    }
                }
            }
        }
    }

    public static String validatePhone(String input){

        Pattern pattern = Pattern.compile("\\+?(7|8?)\\D?\\(?(\\d{3})\\)?\\D?(\\d{5})\\D?([\\d]{2})");
        String phone = input.replaceAll("([^0-9])+", "");
        String ret = "";

        if (phone.length() == 10) {
            phone = "7" + phone;
        } else if (phone.length() == 11 && (phone.substring(0, 1).equals("7") || phone.substring(0, 1).equals("8"))) {
            phone = "7" + phone.substring(1, 11);
        }
        if (phone.length() == 10) {
            phone = "7" + phone;
        } else if (phone.length() == 11 && (phone.substring(0, 1).equals("7") || phone.substring(0, 1).equals("8"))) {
            phone = "7" + phone.substring(1, 11);
        }
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            ret = phone;
        }
        else {
            ret = "error";
        }
        return ret;
    }

    private static void printMap(Map<String, String> map){
        for(String key: map.keySet()){
            System.out.println("Телефон " + key + " Имя " + map.get(key));
        }
    }

    private static String getPhoneForName(Map<String, String> map, String name){
        String ret = "";
        for(String key: map.keySet()){
            if (map.get(key).equals(name)){
                ret = key;
                break;
            }
        }
        return ret;
    }
}