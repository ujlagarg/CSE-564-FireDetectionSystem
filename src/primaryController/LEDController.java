package primaryController;

import java.util.HashMap;
import java.util.Map;

public class LEDController {
    Map<Integer, Boolean> paths = new HashMap<Integer, Boolean>();

    public LEDController() {

    }

    public void updateLedPaths(HashMap<Integer, Boolean> ledPaths) {
        for (Map.Entry<Integer, Boolean> set : ledPaths.entrySet()) {
            paths.put(set.getKey(), set.getValue());
        }

        /**
         * Execute LED Actuators by switching all LEDs associated to a single path
         */
    }
}
