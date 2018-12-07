import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(args[1]);

        if(!file.exists()){
            System.out.println("Invalid file path");
        }

        StationService stationService = new StationService();

        List<Station> stations = stationService.createDeserializationList(file,objectMapper);

        Map<String, List <Integer>> map = stationService.addDeserializationListInMap(stations);

        List<AverageStation> averageStations = stationService.createAverageStationList(map);

        stationService.SerializeAverageList(averageStations,objectMapper,args[3]);
    }
}


