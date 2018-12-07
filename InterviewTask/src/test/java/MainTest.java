import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testDeserializationList () throws IOException {
        StationService stationService = new StationService();

        List<Station> actual = stationService.createDeserializationListFromString();

        List<Station> expected = new ArrayList<>();
        expected.add(new Station("MySSID",-10));
        expected.add(new Station("Appolica",-15));
        expected.add(new Station("MySSID",-1));
        expected.add(new Station("Appolica", -5));
        expected.add(new Station("Appolica", -50));

        assertThat(actual, is(expected));
    }

    @Test
    public void testDeserializationListMap() throws IOException {
        StationService stationService = new StationService();

        Map<String, List <Integer>> actual = stationService.addDeserializationListInMap(stationService.createDeserializationListFromString());
        Map<String, List<Integer>> expected = new HashMap<>();

        String name1 = "MySSID";
        List<Integer> station1Values = new ArrayList<>(Arrays.asList(-10, -1));

        String name2 = "Appolica";
        List<Integer> station2Values = new ArrayList<>(Arrays.asList(-15, -5, -50));

        expected.put(name1,station1Values);
        expected.put(name2, station2Values);


        assertThat(actual, is(expected));

    }

    @Test
    public void testAverageList() throws IOException {
        StationService stationService = new StationService();
        List<AverageStation> actual =  stationService.createAverageStationList(stationService.addDeserializationListInMap(stationService.createDeserializationListFromString()));

        List<AverageStation> expected =  new ArrayList<>();
        expected.add(new AverageStation("MySSID", -5.5));
        expected.add(new AverageStation("Appolica", -23.333333333333332));

        assertThat(actual, is(expected));
    }

    @Test
    public void testSerializationList () throws IOException {
        StationService stationService = new StationService();
        ObjectMapper objectMapper = new ObjectMapper();

        String actual = stationService.SerializeAverageListToString(stationService.createAverageStationList(stationService.addDeserializationListInMap(stationService.createDeserializationListFromString())),objectMapper).replaceAll("\\s","");

        String expected = "[ { \"stationName\" : \"MySSID\", \"averagePower\" : -5.5 }, { \"stationName\" : \"Appolica\", \"averagePower\" : -23.333333333333332 } ]".replaceAll("\\s","");

            assertEquals(expected, actual);


    }
}
