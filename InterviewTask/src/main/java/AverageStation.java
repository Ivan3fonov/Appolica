import java.util.Objects;

public class AverageStation {
    private String stationName;
    private double averagePower;

    public AverageStation(String stationName,double averagePower) {
        this.stationName = stationName;
        this.averagePower = averagePower;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getAveragePower() {
        return averagePower;
    }

    public void setAveragePower(int averagePower) {
        this.averagePower = averagePower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AverageStation that = (AverageStation) o;

        if (Double.compare(that.averagePower, averagePower) != 0) return false;
        return Objects.equals(stationName, that.stationName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = stationName != null ? stationName.hashCode() : 0;
        temp = Double.doubleToLongBits(averagePower);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
