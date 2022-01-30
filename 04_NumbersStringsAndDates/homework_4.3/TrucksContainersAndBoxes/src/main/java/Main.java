import java.util.Scanner;

public class Main {
    // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам

    private static final int maxBoxInConteiner = 27;
    private static final int maxConteinerInTruck = 12;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int counter = (int) Double.parseDouble(boxes);
        int numberBox = 0;
        int numberTruck = 0;
        int numberConteiner = 0;

        for (int i = 0; i < counter; i++) {
            if (i % (maxBoxInConteiner * maxConteinerInTruck) == 0) {
                numberTruck++;
                System.out.println("Грузовик: " + numberTruck);
            }
            if (i % maxBoxInConteiner == 0) {
                numberConteiner++;
                System.out.println("\t Контейнер: " + numberConteiner);
            }
            numberBox++;
            System.out.println("\t\t Ящик: " + numberBox);

        }
        System.out.println("Необходимо");
        System.out.println("грузовиков - " + numberTruck + " шт.");
        System.out.println("контейнеров - " + numberConteiner + " шт.");

    }
}
