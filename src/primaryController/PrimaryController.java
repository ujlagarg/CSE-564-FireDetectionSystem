package primaryController;

import java.util.Map;

import constants.Constants;

public class PrimaryController {
    CameraController cameraController;
    PersonDetector personDetector;
    public Router router;
    LEDController ledController;
    Map<Integer, Boolean> fireMap;

    public PrimaryController(int numberOfRooms) {
        cameraController = new CameraController();
        personDetector = new PersonDetector();
        router = new Router();
        ledController = new LEDController();
    }

    public void setFireDetectorSignal(Map<Integer, Boolean> fireDetectedMap) {
        fireMap = fireDetectedMap;

        for (Map.Entry<Integer, Boolean> set : fireDetectedMap.entrySet()) {
            if (set.getValue()) {
                System.out.println("🔥 Fire detected in Room " + set.getKey());
            }
        }

        startFireEvacuationSystem();
    }

    public void startFireEvacuationSystem() {
        System.out.println("🤖 Initiating Evacuation System");
        Map<Integer, Integer> peopleDetected = personDetector.detectPeople(cameraController.getImageMap());
        System.out.println("🙋 People detected in each room" + peopleDetected.toString());
        System.out.println("🚨 Routing people out of rooms to nearest Fire Exit");
        Map<Integer, Boolean> edges = router.update(peopleDetected, fireMap);

        /** Make LED Path adjacency matrix */
        Integer[][] edgesAdjacencyMatrix = new Integer[6][6];
        for (Map.Entry<String, Integer> room : Constants.ROOMS_EDGE_TO_INDEX.entrySet()) {
            if (edges.get(room.getValue())) {
                String[] path = room.getKey().split(" ");
                int from = Integer.parseInt(path[0]);
                int to = Integer.parseInt(path[1]);

                edgesAdjacencyMatrix[from][to] = 1;
                System.out.println("🚥 Lighting path from " + from + " to " + to + " 🚥");
            }
        }

        System.out.println("✅ Finished Routing");
        System.out.println(" ");
    }

    public void stopFireEvacuationSystem() {
        System.out.println("👌🏻 No fire detected. All good");
    }

    public CameraController getCameraController() {
        return cameraController;
    }
}
