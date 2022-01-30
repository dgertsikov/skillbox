import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        DecimalFormat formatter = new DecimalFormat("#0.00°C");
        final int COUNT_ARRAY = 30;
        final double MAX_TEMP = 36.9;
        final double MIN_TEMP = 36.2;

        double[] array = new double[COUNT_ARRAY];
        double temp;
        double maxTemp=Double.MIN_VALUE, minTemp=Double.MAX_VALUE, averTemp=0, sumTemp=0;
        int count = 0;
        String text="";

        for (int i = 0; i < array.length; i++){
            temp = 32 +(Math.random()*8);
            temp = (Math.round(temp * 10))*0.1;
            array[i] = temp;
        }

        for (int i = 0; i < array.length; i++){
            maxTemp = Math.max(maxTemp, array[i]);
            minTemp = Math.min(minTemp, array[i]);
            sumTemp += array[i];
            if (array[i] >= MIN_TEMP && array[i] <= MAX_TEMP){
                count ++;
            }
            text = text  + (i==0?"":", ") +  formatter.format(array[i]);

        }
        System.out.println("Температура " + text);
        System.out.println("Максимальная температура " + formatter.format(maxTemp));
        System.out.println("Минимальная температура " + formatter.format(minTemp));
        System.out.println("Средняя температура " + formatter.format(sumTemp/array.length));
        System.out.println("Количество здоровых " + count);

    }
}
