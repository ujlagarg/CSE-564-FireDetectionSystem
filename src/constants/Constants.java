package constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<Integer, Integer> ROOMS_MAX_OCCUPANCY = new HashMap<>();
    static {
        ROOMS_MAX_OCCUPANCY.put(0, 10);
        ROOMS_MAX_OCCUPANCY.put(1, 100);
        ROOMS_MAX_OCCUPANCY.put(2, 50);
        ROOMS_MAX_OCCUPANCY.put(3, 40);
        ROOMS_MAX_OCCUPANCY.put(4, 60);
    }
    public static final Map<String, Integer> ROOMS_EDGE_TO_INDEX = new HashMap<>();
    static {
        ROOMS_EDGE_TO_INDEX.put("0 1", 0);
        ROOMS_EDGE_TO_INDEX.put("1 0", 1);
        ROOMS_EDGE_TO_INDEX.put("1 2", 2);
        ROOMS_EDGE_TO_INDEX.put("1 3", 3);
        ROOMS_EDGE_TO_INDEX.put("1 4", 4);
        ROOMS_EDGE_TO_INDEX.put("1 5", 5);
        ROOMS_EDGE_TO_INDEX.put("2 5", 6);
        ROOMS_EDGE_TO_INDEX.put("2 0", 7);
        ROOMS_EDGE_TO_INDEX.put("3 1", 8);
        ROOMS_EDGE_TO_INDEX.put("4 5", 9);
        ROOMS_EDGE_TO_INDEX.put("4 1", 10);
        ROOMS_EDGE_TO_INDEX.put("5 1", 11);
        ROOMS_EDGE_TO_INDEX.put("5 2", 12);
        ROOMS_EDGE_TO_INDEX.put("5 4", 13);
    }
    public static final Double MIN_SMOKE_INTENSITY = 0.01;
    public static final Double MAX_SMOKE_INTENSITY = 1.00;

    private Constants() {

    }
}
