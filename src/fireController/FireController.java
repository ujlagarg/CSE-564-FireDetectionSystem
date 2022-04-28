package fireController;

import java.util.*;

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
        for (FireDetector fireDetector : fireDetectors) {
            fireDetectedMap.put(fireDetector.getId(), fireDetector.getState());
        }
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
                    if (Objects.equals(id, Constants.ROOM_COUNT)) {
                        continue;
                    }

                    FireDetector neighboringFireDetector = fireDetectorMap.get(id);
                    Boolean isItBurning = neighboringFireDetector.getState();
                    if (!isItBurning) {
                        /** Random Smoke */
                        neighboringFireDetector.smokeDetectorSensor.setSmokeDensity(0.16 * random.nextDouble());

                        /** Random Heat */
                        neighboringFireDetector.tempDetectorSensor.setTemperature(random.nextInt(60));

                        neighboringFireDetector.setState();
                        continue;
                    }
                    ;
                }
            }
        }
    }
}
