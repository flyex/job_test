package thread.buyTicket;

public class StationDo {
    public static void main(String[] args) {
        Station station = new Station("车站1");
        Station station2 = new Station("车站2");
        Station station3 = new Station("车站3");

        station.start();
        station2.start();
        station3.start();
    }
}
