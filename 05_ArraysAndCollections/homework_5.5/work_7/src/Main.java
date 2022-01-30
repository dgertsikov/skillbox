import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] word = {"А", "В", "С", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У"};
        String text = "";
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            for (int n = 0; n < 10; n++) {
                for (int s = 0; s < word.length; s++) {
                    for (int t = 0; t < word.length; t++) {
                        for (int r = 1; r < 200; r++) {
                            text = word[i] + String.valueOf(n) + String.valueOf(n) + String.valueOf(n) + word[s] + word[t] + String.format("%03d", r);
                            list.add(text);
                        }
                    }
                }
            }
        }
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(list);
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.addAll(list);

        System.out.println("Введите номер: ");

        Scanner scanner = new Scanner(System.in);
        for (;;) {
            String input = scanner.nextLine();

            long start = System.nanoTime();
            if (list.contains(input)) {
                System.out.println("Номер найден");
            } else {
                System.out.println("Номер не найден");
            }
            long duration = System.nanoTime() - start;
            System.out.println("Время прямого перебора: " + duration + " наносек");
            //
            Collections.sort(list);
            start = System.nanoTime();
            if (Collections.binarySearch(list, input) > 0) {
                System.out.println("Номер найден");
            } else {
                System.out.println("Номер не найден");
            }
            duration = System.nanoTime() - start;
            System.out.println("Время бинарного поиска: " + duration + " наносек");
            //
            start = System.nanoTime();
            if (hashSet.contains(input)) {
                System.out.println("Номер найден");
            } else {
                System.out.println("Номер не найден");
            }
            duration = System.nanoTime() - start;
            System.out.println("Время HashSet: " + duration + " наносек");
            //
            start = System.nanoTime();
            if (treeSet.contains(input)) {
                System.out.println("Номер найден");
            } else {
                System.out.println("Номер не найден");
            }
            duration = System.nanoTime() - start;
            System.out.println("Время TreeSet: " + duration + " наносек");
        }

    }
}