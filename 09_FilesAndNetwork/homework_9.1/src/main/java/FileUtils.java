import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {

        long folderSize = 0;

        File folder = new File(path);
        if (!folder.exists()){
            throw new IllegalArgumentException("Путь не существует");
        }
        File[] files = folder.listFiles();

        for (File file : files){
            if (file.isDirectory()){
                //System.out.println("Папка: "+file.getAbsolutePath()+ " - "+ file.length());
                folderSize = folderSize + calculateFolderSize(file.getAbsolutePath());
            }
            else{
                //System.out.println(file.getAbsolutePath()+ " - "+ file.length());
                folderSize = folderSize + file.length();
            }
        }

        return folderSize;
    }


}
