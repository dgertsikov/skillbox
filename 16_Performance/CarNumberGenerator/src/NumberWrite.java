import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumberWrite implements Runnable{

    private StringBuilder stringBuilder;
    private String regionCode;

    public NumberWrite(StringBuilder stringBuilder, String regionCode) {
        this.stringBuilder = stringBuilder;
        this.regionCode = regionCode;
    }

    @Override
    public void run() {

        try {

            PrintWriter writer = new PrintWriter("res/numbers" + regionCode + ".txt");
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();

            System.out.println(System.currentTimeMillis() + " : end");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
