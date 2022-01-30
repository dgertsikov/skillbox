public class Main {

    public static void main(String[] args) {

        String rusString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int ch;
        for (int i = 0; i < rusString.length(); i++){
            ch = (int) rusString.charAt(i);
            System.out.println("Буква - " + rusString.charAt(i) + " код - " + ch);
        }
    }
}
