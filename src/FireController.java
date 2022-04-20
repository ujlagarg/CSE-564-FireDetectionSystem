import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import constants.Constants;

public class FireController {
    Map<Integer, Boolean> fireDetectedMap = new HashMap<Integer, Boolean>();
    ArrayList<FireDetector> fireDetectors = new ArrayList<FireDetector>();
    Map<Integer, FireDetector> fireDetectorMap = new HashMap<Integer, FireDetector>();

    public FireController(ArrayList<FireDetector> newFireDetectors) {
        fireDetectors = newFireDetectors;

        for (FireDetector fireDetector : fireDetectors) {
            fireDetectorMap.put(fireDetector.getId(), fireDetector);
        }
    }

    public Map<Integer, Boolean> getFireDetectedMap() {
        return fireDetectedMap;
    }

    /**
     * Simulation function
     */
    public void spreadFire(Map<Integer, Integer[]> roomMap) {
        Random random = new Random();
        for (FireDetector fireDetector : fireDetectors) {
            Integer currentRoomId = fireDetector.getId();
            Boolean isCurrentRoomBurning = fireDetector.getState();
            if (isCurrentRoomBurning) {
                Integer[] neighboringRoomIds = roomMap.get(currentRoomId);
                for (Integer id : neighboringRoomIds) {
                    Boolean isItBurning = fireDetectorMap.get(id).getState();
                    if (!isItBurning) {
                        /** Random Smoke */
                        fireDetector.smokeDetectorSensor.setSmokeDensity(random.nextDouble());

                        /** Random Heat */
                        fireDetector.tempDetectorSensor.setTemperature((Math.random() * 81) + 20);
                        continue;
                    }
                    ;
                }
            }
        }
    }
}
