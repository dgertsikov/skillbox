import com.opencsv.CSVReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Movements {
    private static String dateFormat = "dd.MM.yy";
    ArrayList<Account> account = new ArrayList<>();

    public Movements(String pathMovementsCsv) {

        boolean firstRecord = true;

        try
        {
            CSVReader reader = new CSVReader(new FileReader(pathMovementsCsv));
            List<String[]> lines = reader.readAll();

            for(String[] fragments : lines)
            {
                if(fragments.length != 8) {
                    System.out.println("Wrong line: " + fragments);
                    continue;
                }
                if(firstRecord){
                    firstRecord = false;
                    continue;
                }
                account.add(new Account.Builder()
                        .withType(fragments[0])
                        .withNumber(fragments[1])
                        .withCurrency(fragments[2])
                        .withDate((new SimpleDateFormat(dateFormat)).parse(fragments[3]))
                        .withReference(fragments[4])
                        .withDescription(fragments[5])
                        .withDebet(Double.parseDouble(fragments[6].replaceAll(",", ".")))
                        .withCredit(Double.parseDouble(fragments[7].replaceAll(",", ".")))
                        .build()
                );
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public double getExpenseSum()
    {
        double ret = 0.0;
        for(Account accounts : account){
            ret = ret + accounts.getCredit();
        }
        return ret;
    }

    public double getIncomeSum()
    {
        double ret = 0.0;
        for(Account accounts : account){
            ret = ret + accounts.getDebet();
        }
        return ret;
    }
}
