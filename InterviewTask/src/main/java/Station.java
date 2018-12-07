import java.util.Objects;

public class Station {
    private String stationName;
    private int power;

    public Station() {}

    public Station(String stationName, int power) {
        this.stationName = stationName;
        this.power = power;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (power != station.power) return false;
        return Objects.equals(stationName, station.stationName);
    }

    @Override
    public int hashCode() {
        int result = stationName != null ? stationName.hashCode() : 0;
        result = 31 * result + power;
        return result;
    }
}
