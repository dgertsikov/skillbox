import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import static java.lang.Runtime.getRuntime;

public class Main
{
    private  static int newWidth = 300;
    public static void main(String[] args)
    {
        //String srcFolder = "/users/sortedmap/Desktop/src";
        //String dstFolder = "/users/sortedmap/Desktop/dst";
        String srcFolder = "C:/Temp/src";
        String dstFolder = "C:/Temp/dst";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int middle = files.length / 2;
        int nProcesser= Runtime.getRuntime().availableProcessors();
        int counter = (files.length <= nProcesser) ? files.length : nProcesser;
        System.out.println("Количество процессоров: " + nProcesser);
        middle = files.length / counter;
        int srcPos = middle;
        for (int i=0; i < counter; i++){
            if(i == counter-1){
                middle = files.length - (i * srcPos);
            }
            File[] files01 = new File[middle];
            System.arraycopy(files, i * srcPos, files01, 0, files01.length);
            new Thread(new ImageResizer(files01, newWidth, dstFolder, start)).start();
        }

//        File[] files1 = new File[middle];
//        System.arraycopy(files, 0, files1, 0, files1.length);
//        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
//        new Thread(resizer1).start();
//
//        File[] files2 = new File[files.length - middle];
//        System.arraycopy(files, middle, files2, 0, files2.length);
//        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
//        new Thread(resizer2).start();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
