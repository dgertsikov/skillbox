import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[\\w-]+\\.\\w{3}\\b");
        String url;
        String fileName = "";

        try {
            Document doc = Jsoup.connect("https://lenta.ru").get();

//            String htmlFile = parseFile("data/code.html");
//            Document doc = Jsoup.parse(htmlFile);
            Elements elements = doc.select("img");
//            elements.forEach(element -> {
//                System.out.println(element.attr("src"));
//            });

            for (Element el : elements){
                url = el.attr("src");
                Matcher matcher = pattern.matcher(el.attr("src"));
                try{
                    if(matcher.find()) 
                    {
                        System.out.println(matcher.group());
                        fileName = "image/" + matcher.group();
                    }
                    downloadUsingStream(url, fileName);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            //System.out.println(doc.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public static String parseFile(String path){
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line +"\n"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }
}
