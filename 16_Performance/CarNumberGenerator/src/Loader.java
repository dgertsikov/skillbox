import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + " start");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for (int regionCode=1; regionCode<100; regionCode++){
            StringBuilder builder = new StringBuilder();
            String regionCodeString = padNumber(regionCode, 2);
            for (int number = 1; number < 100; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number,3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(regionCodeString);
                            builder.append("\n");
                        }
                    }
                }
            }
            NumberWrite numberWrite = new NumberWrite(builder, regionCodeString);
            new Thread(numberWrite).start();
//            PrintWriter writer = new PrintWriter("res/numbers.txt");
//            writer.write(builder.toString());
//            writer.flush();
//            writer.close();

        }


        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        StringBuilder builder = new StringBuilder();
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            builder.append('0');
        }
        builder.append(numberStr);
        return builder.toString();
    }
}
