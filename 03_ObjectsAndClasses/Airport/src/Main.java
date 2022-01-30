import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();
        calendar2.add(Calendar.HOUR, 2);
        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();

        Airport aiport = Airport.getInstance();
        int size = aiport.getAllAircrafts().size();
        System.out.println("Количество самолетов в аэропорту - " + size);

//        for (Terminal t : aiport.getTerminals()){
//            t.getFlights()
//                    .stream()
//                    .filter(f -> f.getType().equals(Flight.Type.DEPARTURE)
//                            &&f.getDate().after(date1)
//                            &&f.getDate().before(date2))
//                    .forEach(System.out::println);
//        }
//
//        System.out.println("-----------");
//        System.out.println("Как то не так сделал");

        aiport.getTerminals()
                .stream()
                .flatMap(a -> a.getFlights()
                        .stream())
                        .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
                        .filter(f -> f.getDate().after(date1))
                        .filter(f -> f.getDate().before(date2))
                        .forEach(System.out::println);
    }
}
