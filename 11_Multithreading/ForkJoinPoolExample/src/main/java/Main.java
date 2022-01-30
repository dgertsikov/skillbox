import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        String path = "http://www.lenta.ru/";
        List<String> list = new ForkJoinPool().invoke(new SiteMap(path, "", 0));

        Storage.listOut.add(path);
        getSiteMapRecurse(path, path, "");

        try {
            Files.write(Paths.get("data/outfile.txt"), Storage.listOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ok");
    }

    public static void getSiteMapRecurse(String path, String urlString, String tab){
        tab = tab + "\t";
        if (Storage.siteMap.containsKey(urlString)) {
            for (String item : Storage.siteMap.get(urlString)) {
                //System.out.println(item);
                Storage.listOut.add(tab + path + item);
                getSiteMapRecurse(path, item, tab);

            }
        }
    }
}