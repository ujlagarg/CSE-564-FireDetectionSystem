package constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<Integer, Integer> ROOMS_MAX_OCCUPANCY = new HashMap<Integer, Integer>();
    static {
        ROOMS_MAX_OCCUPANCY.put(0, 10);
        ROOMS_MAX_OCCUPANCY.put(1, 100);
        ROOMS_MAX_OCCUPANCY.put(2, 50);
        ROOMS_MAX_OCCUPANCY.put(3, 40);
        ROOMS_MAX_OCCUPANCY.put(4, 60);
    }
    public static final Double MIN_SMOKE_INTENSITY = 0.01;
    public static final Double MAX_SMOKE_INTENSITY = 1.00;

    private Constants() {

    }
}
