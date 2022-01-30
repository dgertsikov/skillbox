import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static long folderSize = 0;

    public static void main(String[] args)
    {

        for (;;)
        {
            try {
                System.out.println("Введите путь до папки:");
                scanner = new Scanner(System.in);
                String line = scanner.nextLine().trim();
                //String line = "C:\\Temp";
                folderSize = FileUtils.calculateFolderSize(line);
                System.out.println("Размер папки " + line + " составляет " + bytesToHuman(folderSize));
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static String bytesToHuman (long size)
    {
        long Kb = 1  * 1024;
        long Mb = Kb * 1024;
        long Gb = Mb * 1024;
        long Tb = Gb * 1024;
        long Pb = Tb * 1024;
        long Eb = Pb * 1024;

        if (size <  Kb)                 return floatForm(        size     ) + " byte";
        if (size >= Kb && size < Mb)    return floatForm((double)size / Kb) + " Kb";
        if (size >= Mb && size < Gb)    return floatForm((double)size / Mb) + " Mb";
        if (size >= Gb && size < Tb)    return floatForm((double)size / Gb) + " Gb";
        if (size >= Tb && size < Pb)    return floatForm((double)size / Tb) + " Tb";
        if (size >= Pb && size < Eb)    return floatForm((double)size / Pb) + " Pb";
        if (size >= Eb)                 return floatForm((double)size / Eb) + " Eb";

        return "";
    }

    public static String floatForm (double d)
    {
        return new DecimalFormat("#.##").format(d);
    }
}
