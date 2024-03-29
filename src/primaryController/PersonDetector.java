package primaryController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import constants.Constants;

public class PersonDetector {
    public Map<Integer, Integer> detectPeople(Map<Integer, Integer[]> imageMap) {
        System.out.println("📸 Detecting people from images");
        Map<Integer, Integer> peopleDetected = new HashMap<Integer, Integer>();
        Random random = new Random();
        Integer min = 1;

        for (Map.Entry<Integer, Integer[]> set : imageMap.entrySet()) {
            Integer roomId = set.getKey();
            Integer max = Constants.ROOMS_MAX_OCCUPANCY.get(roomId);
            peopleDetected.put(roomId, max);

            if (random.nextBoolean()) {
                peopleDetected.put(roomId, random.nextInt(max - min + 1) + min);
            } else {
                peopleDetected.put(set.getKey(), 0);
            }
        }

        return peopleDetected;
    }
}
