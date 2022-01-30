import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static void copyFolder(String sourceDirectory, String destinationDirectory)
    {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        try {

            File sourceFolder = new File(sourceDirectory);
            if (!sourceFolder.exists()){
                throw new IOException("Путь источника не существует");
            }
            File destinationFolder = new File(destinationDirectory);

            Path sourcePath = Path.of(sourceDirectory);
            Path destinationPath = Path.of(destinationDirectory);

            if (!destinationFolder.exists()){
                Files.createDirectory(destinationPath);
            }
            File[] files = sourceFolder.listFiles();

            for (File file : files){
                if (file.isDirectory()){
//                    System.out.println("Папка: "+file.getAbsolutePath()+ " - "+ file.length());
//                    System.out.println(file.getName());
                    copyFolder(file.getAbsolutePath(), destinationDirectory + "/" + file.getName());
                }
                else{
//                    System.out.println(file.getAbsolutePath()+ " - "+ file.length());
//                    System.out.println(destinationDirectory + "\\" + file.getName());
                    Files.copy(Path.of(file.getAbsolutePath()), Path.of(destinationDirectory + "/" + file.getName()));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
