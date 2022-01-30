import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        Pattern emailpattern = Pattern.compile("[\\w-\\.]+@[\\w-]+\\.+[\\w-]{2,4}");

        String[] components = data.split("\\s+");
        if (components.length != 4){
            throw new IllegalArgumentException("Неправильный формат сообщения. Правильный формат:\n"
                    +"add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        Matcher emailMatcher = emailpattern.matcher(components[2]);
        if (!emailMatcher.find()) {
            throw new IllegalArgumentException("Неправильный формат eMail");
        }
        if (!validatePhone(components[3])){
            throw new IllegalArgumentException("Неправильный формат телефона");
        }


        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    public static boolean validatePhone(String input){

        Pattern pattern = Pattern.compile("\\+?(7|8?)\\D?\\(?(\\d{3})\\)?\\D?(\\d{5})\\D?([\\d]{2})");
        String phone = input.replaceAll("([^0-9])+", "");

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
            return true;
        }
        return false;
    }

}