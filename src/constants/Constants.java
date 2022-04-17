package constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<Integer, Integer> ROOMS_MAX_OCCUPANCY = new HashMap<Integer, Integer>();
    static {
        ROOMS_MAX_OCCUPANCY.put(1, 10);
        ROOMS_MAX_OCCUPANCY.put(2, 100);
        ROOMS_MAX_OCCUPANCY.put(3, 50);
        ROOMS_MAX_OCCUPANCY.put(4, 40);
        ROOMS_MAX_OCCUPANCY.put(5, 60);
        ROOMS_MAX_OCCUPANCY.put(6, 125);
        ROOMS_MAX_OCCUPANCY.put(7, 225);
    }

    private Constants() {

    }
}
