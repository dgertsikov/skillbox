import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase
{
    List<Station> route;
    private static StationIndex stationIndex;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception{
        route = new ArrayList<>();
        stationIndex = new StationIndex();
        Station station;

        Line line1 = new Line(1,"Первая");
        stationIndex.addLine(line1);
        Line line2 = new Line(2,"Вторая");
        stationIndex.addLine(line2);
        Line line3 = new Line(3,"Третья");
        stationIndex.addLine(line3);

        station = new Station("Петровская", line1);
        stationIndex.addStation(station);
        line1.addStation(station);
        station = new Station("Сидоровская", line1);
        stationIndex.addStation(station);
        line1.addStation(station);
        station = new Station("Василевская", line1);
        stationIndex.addStation(station);
        line1.addStation(station);
        station = new Station("Артемовская", line1);
        stationIndex.addStation(station);
        line1.addStation(station);
//
        station = new Station("Ивановская", line2);
        stationIndex.addStation(station);
        line2.addStation(station);
        station = new Station("Кузнецовская", line2);
        stationIndex.addStation(station);
        line2.addStation(station);
        station = new Station("Павловская", line2);
        stationIndex.addStation(station);
        line2.addStation(station);
        station = new Station("Антоновская", line2);
        stationIndex.addStation(station);
        line2.addStation(station);
//
        station = new Station("Григорьевская", line3);
        stationIndex.addStation(station);
        line3.addStation(station);
        station = new Station("Михайловская", line3);
        stationIndex.addStation(station);
        line3.addStation(station);
        station = new Station("Пантелемоновская", line3);
        stationIndex.addStation(station);
        line3.addStation(station);
        station = new Station("Андреевская", line3);
        stationIndex.addStation(station);
        line3.addStation(station);

        List<Station> connectionStations = new ArrayList<>();
        connectionStations.add(stationIndex.getStation("Василевская", 1));
        connectionStations.add(stationIndex.getStation("Михайловская", 3));
        stationIndex.addConnection(connectionStations);
        connectionStations = new ArrayList<>();
        connectionStations.add(stationIndex.getStation("Павловская", 2));
        connectionStations.add(stationIndex.getStation("Пантелемоновская", 3));
        stationIndex.addConnection(connectionStations);

        calculator = new RouteCalculator(stationIndex);
    }

    public void testRouteOnTheLine(){
        List<Station> route = calculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Василевская"));

        assertEquals(3, route.size());
    }

    public void testRouteWithOneConnection(){
        List<Station> route = calculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Григорьевская"));

        assertEquals(5, route.size());
    }

    public void testRouteWithTwoConnections(){

        List<Station> route = calculator.getShortestRoute(stationIndex.getStation("Сидоровская"), stationIndex.getStation("Кузнецовская"));

        assertEquals(6, route.size());
    }

    public void testCalculateDuration()
    {
        List<Station> route = calculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Григорьевская"));

        assertEquals((double) 11, RouteCalculator.calculateDuration(route));
    }

    @Override
    protected void tearDown() throws Exception
    {

    }

}
