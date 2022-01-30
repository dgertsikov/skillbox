import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdaysLD(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar(year, month, day);
        int n = 0;
        String text = "";
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy - EEEE");

        while(calendar2.get(Calendar.YEAR) <= calendar1.get(Calendar.YEAR)){

            text = text + (n==0?"":"\n") + n + " - " + formater.format(calendar2.getTime());

            calendar2.add(Calendar.YEAR, 1);
            n++;
        }

        return text;
    }

    public static String collectBirthdaysLD(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        int n = 0;
        String text = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEEE");

        while(today.isAfter(birthday)){

            text = text + (n==0?"":"\n") + n + " - " + birthday.format(formatter);
            birthday = birthday.plusYears(1);

            n++;
        }

        return text;
    }
}
