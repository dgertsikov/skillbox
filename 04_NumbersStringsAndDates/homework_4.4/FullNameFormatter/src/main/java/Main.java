import java.util.Scanner;

public class Main {

    public static int getValidSpaces(String string){
        int count = 0;
        for (int i=0; i < string.length(); i++){
            if (string.charAt(i) == ' '){
                count++;
            }
        }
        return count;


    }
    public static boolean getValidAlphabet(String string){
        String stringAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ-";
        //boolean ret;
        for (int i=0; i < string.length(); i++){
            if (stringAlphabet.indexOf(string.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int indFrom = 0;
        int indTo = 0;
        int indOne = 0;
        int indTwo = 0;
        String firstName, middleName, lastName;
        String str;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО

            if (!getValidAlphabet(input) || getValidSpaces(input) != 2) {
                System.out.println("Введенная строка не является ФИО");
                //break;
            }
            else {

                indOne = input.indexOf(" ");
                indTwo = input.lastIndexOf(" ", input.length());
                if (indOne < 0 || indTwo < 0) {
                    System.out.println("Введенная строка не является ФИО");
                }
                firstName = input.substring(0, indOne);
                middleName = input.substring(indOne + 1, indTwo);
                lastName = input.substring(indTwo + 1, input.length());

//                System.out.println("Фамилия: " + firstName);
//                System.out.println("Имя: " + middleName);
//                System.out.println("Отчество: " + lastName);
                System.out.println(String.format("Фамилия: %s\nИмя: %s\nОтчество: %s", firstName, middleName, lastName));
            }

        }
    }

}