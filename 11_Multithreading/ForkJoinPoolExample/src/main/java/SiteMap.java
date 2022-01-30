import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteMap extends RecursiveTask<List<String>>
{
    private final String path;
    private String url;
    private int level;
    List<String> list = new ArrayList<>();

    public SiteMap(String path, String url, int level) {
        this.path = path;
        this.url = url;
        this.level = level;
    }

    @Override
    protected List<String> compute() {
        List<SiteMap> tasks = new ArrayList<>();

        try {
            level++;
            Document doc = Jsoup.connect(path+url).get();
            Elements elements = doc.select("a[href~=^\\/.+\\/$]"); //выбираем в формате /rubrics/sport/
            String sUrl = "";
            for(Element element : elements)
            {
                //Если /rubrics/sport/winter/ содержит /rubrics/sport/
                if (level < 3 && element.attr("href").contains(url) && !element.attr("href").substring(1).equals(url)) {
                    Thread.sleep(150);

                    sUrl = (url.equals("") ? path : url);
                    if (!Storage.siteMap.containsKey(sUrl)) {
                        Storage.siteMap.put(sUrl, new ArrayList<>());
                    }
                    if (!Storage.siteMap.get(sUrl).contains(element.attr("href").substring(1))){
                        Storage.siteMap.get(sUrl).add(element.attr("href").substring(1));
                    }

                    list.add(path + element.attr("href").substring(1));
                    SiteMap task = new SiteMap(path, element.attr("href").substring(1), level);
                    task.fork();
                    tasks.add(task);
                }
            }

            for(SiteMap item : tasks)
            {
                list.addAll(item.join());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
