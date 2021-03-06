import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class XMLHandler extends DefaultHandler
{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    Voter voter;
    private HashMap<Voter, Integer> voterCounts1;
    private HashMap<String, Integer> voterCounts;
    private ArrayList<String> voterArray = new ArrayList<>();

    public XMLHandler() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        try {
            if (qName.equals("voter") && voter == null) {

                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            }
            else if (qName.equals("visit") && voter != null){
                //int count = voterCounts.getOrDefault(voter.toString(), 0);
                //voterCounts.put(voter.toString(), count + 1);
                voterArray.add(voter.toString());

            }

        } catch(ParseException e){
            e.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

//    public void printDuplicate(){
//        for (Voter voter : voterCounts.keySet()){
//            int count = voterCounts.get(voter);
//            if (count > 1){
//                System.out.println(voter.toString() + " - " + count);
//            }
//        }
//    }
    public void printDuplicate(){

        voterArray.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .forEach(e-> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}
