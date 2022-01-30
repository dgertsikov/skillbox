import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        try {
//            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").get();
            String htmlFile = parseFile("data/code.html");
            Document doc = Jsoup.parse(htmlFile);

            Elements elementsLine = doc.select("span.js-metro-line");
            Elements elementsConnect = doc.select("div.js-metro-stations[data-line='1'] span");
            //elementsLine.forEach(System.out::println);
//            elementsConnect.forEach(element -> {
//                System.out.println(element.className()+" " +element.text() +" "+element.attr("title")+" "+element.hasClass("t-icon-metroln"));
//            });

            Elements elementsStation = doc.select("div.js-metro-stations[data-line='D1'] span.name");

            JSONArray linesArray = new JSONArray();
            JSONObject stationsObj = new JSONObject();
            JSONArray connectArray = new JSONArray();
            JSONArray connectMainArray = new JSONArray();
            int indStart = 0;
            int indEnd = 0;

            for (Element element: elementsLine) {
                JSONObject linesObj = new JSONObject();
                linesObj.put("number", element.attr("data-line"));
                linesObj.put("name", element.text());
                linesArray.add(linesObj);
                //-Connections-
                elementsConnect = doc.select("div.js-metro-stations[data-line='" + element.attr("data-line") + "'] span");
                for(Element connect: elementsConnect){
                    JSONObject connectObj = new JSONObject();
                    if (connect.className().equals("name")){
                        if (connectArray.size() > 1){
                            connectMainArray.add(connectArray);
                        }
                        connectArray = new JSONArray();
                        connectObj = new JSONObject();
                        connectObj.put("line", element.attr("data-line"));
                        connectObj.put("station", connect.text());
                        connectArray.add(connectObj);
                    }
                    if(connect.className().indexOf("t-icon-metroln") > -1){
                        connectObj = new JSONObject();
                        indStart = connect.attr("title").indexOf("«")+1;
                        indEnd = connect.attr("title").indexOf("»");

                        connectObj.put("line", connect.className().substring(18,connect.className().length()));
                        connectObj.put("station", connect.attr("title").substring(indStart,indEnd));
                        connectArray.add(connectObj);
                        //System.out.println(connect.className()+ " " +connect.className().substring(18,connect.className().length())+" "+connect.attr("title").substring(indStart,indEnd)+" "+indStart+" "+indEnd);
                    }
                }
                //

                elementsStation = doc.select("div.js-metro-stations[data-line='" + element.attr("data-line") + "'] span.name");

                JSONArray stationAray = new JSONArray();
                for (Element element1: elementsStation)
                {
                    stationAray.add(element1.text());
                }
                stationsObj.put(element.attr("data-line"), stationAray);
            }

            JSONObject obj = new JSONObject();
            obj.put("stations", stationsObj);
            obj.put("lines", linesArray);
            obj.put("connections", connectMainArray);

            writeJsonFile(obj.toJSONString());

            createStationIndex();

           //System.out.println(obj.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createStationIndex()
    {
        try
        {
            final int[] connectionCount = {0};
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            stationsObject.keySet().forEach(lineNumber ->
            {
                JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumber);
                System.out.println("Линия " + lineNumber + ", количество станций: " + stationsArray.stream().count());
            });

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            connectionsArray.forEach(connectionObject ->
            {
                JSONArray connection = (JSONArray) connectionObject;
                connectionCount[0] = connectionCount[0] + connection.size();
            });
            System.out.println("Количество переходов " + connectionCount[0]);

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getJsonFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/outjson.json"));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void writeJsonFile(String jsonFile) throws IOException {
        FileWriter fileWriter = new FileWriter("data/outjson.json");
        try {
            fileWriter.write(jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.flush();
            fileWriter.close();
        }
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
