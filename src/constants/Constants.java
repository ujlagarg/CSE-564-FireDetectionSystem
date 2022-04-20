package constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Integer ROOM_COUNT = 10;
    public static final Map<Integer, Integer> ROOMS_MAX_OCCUPANCY = new HashMap<>();
    static {
        ROOMS_MAX_OCCUPANCY.put(0, 50);
        ROOMS_MAX_OCCUPANCY.put(1, 5);
        ROOMS_MAX_OCCUPANCY.put(2, 20);
        ROOMS_MAX_OCCUPANCY.put(3, 5);
        ROOMS_MAX_OCCUPANCY.put(4, 15);
        ROOMS_MAX_OCCUPANCY.put(5, 10);
        ROOMS_MAX_OCCUPANCY.put(6, 10);
        ROOMS_MAX_OCCUPANCY.put(7, 15);
        ROOMS_MAX_OCCUPANCY.put(8, 5);
        ROOMS_MAX_OCCUPANCY.put(9, 10);
    }
    public static final Map<String, Integer> ROOMS_EDGE_TO_INDEX;
    static {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get("data/edges.json"));
            String fileContent = new String (bytes);
            ROOMS_EDGE_TO_INDEX = new ObjectMapper().readValue(fileContent, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Double MIN_SMOKE_INTENSITY = 0.01;
    public static final Double MAX_SMOKE_INTENSITY = 1.00;

    private Constants() {

    }
}
