import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StationService {

    List<Station> createDeserializationList (File file, ObjectMapper objectMapper) throws IOException {
        List<Station> stations;
        stations = Arrays.asList(objectMapper.readValue(file, Station[].class));
        return  stations;
    }

    List <Station> createDeserializationListFromString() throws IOException {

        String stations = "[ { \"stationName\":\"MySSID\", \"power\":-10 }, " +
                "{ \"stationName\":\"Appolica\", \"power\":-15 }, " +
                "{ \"stationName\":\"MySSID\", \"power\":-1 }, " +
                "{ \"stationName\":\"Appolica\", \"power\":-5 }, " +
                "{ \"stationName\":\"Appolica\", \"power\":-50 } ]";

        ObjectMapper objectMapper = new ObjectMapper();

        return Arrays.asList(objectMapper.readValue(stations, Station[].class));
    }

    Map<String, List <Integer>> addDeserializationListInMap( List<Station> stations) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (Station station : stations) {
            if (!map.containsKey(station.getStationName())) {

                List<Integer> list = new ArrayList<>();
                list.add(station.getPower());
                map.put(station.getStationName(), list);
            } else {
                map.get(station.getStationName()).add(station.getPower());
            }
        }
        return map;
    }

    List<AverageStation> createAverageStationList( Map<String, List<Integer>> map){
        List<AverageStation> averageStations = new ArrayList<>();
        map.forEach( (k,v) -> averageStations.add(new AverageStation(k , (double)v.stream().mapToInt(Integer::intValue).sum() / v.size())));

        return averageStations;
    }

    void SerializeAverageList (List<AverageStation> averageStations,ObjectMapper objectMapper, String filename) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), averageStations);
    }
    String SerializeAverageListToString (List<AverageStation> averageStations,ObjectMapper objectMapper) throws IOException {
        return objectMapper.writeValueAsString( averageStations);
    }
}
