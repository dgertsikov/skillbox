import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args)
    {
        for (;;)
        {
            try {
                scanner = new Scanner(System.in);

                System.out.println("Введите папку:");
                String sourceDirectory = scanner.nextLine().trim();
                System.out.println("Введите новую папку:");
                String destinationDirectory = scanner.nextLine().trim();

                FileUtils.copyFolder(sourceDirectory, destinationDirectory);

                System.out.println("Данные успешно скопированы!");
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }

    }
}
