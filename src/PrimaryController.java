import java.util.HashMap;
import java.util.Map;

public class PrimaryController {
    CameraController cameraController;
    PersonDetector personDetector;
    Router router;
    LEDController ledController;
    Map<Integer, Boolean> fireMap;

    public PrimaryController(int numberOfRooms){
        cameraController = new CameraController();
        personDetector = new PersonDetector();
        router = new Router();
        ledController = new LEDController();
    }

    public void setFireDetectorSignal(Map<Integer, Boolean> fireDetectedMap){
            fireMap = fireDetectedMap;
            startFireEvacuationSystem();
    }

    public void startFireEvacuationSystem(){
        System.out.println("Starting system");
        Map<Integer, Integer> peopleDetected = personDetector.detectPeople(cameraController.getImageMap());
        System.out.println("people in each room"+peopleDetected.toString());
        System.out.println("Routing people out of rooms to nearest Fire Exit:");
        Map<Integer, Boolean> edges = router.update(peopleDetected,fireMap);
        System.out.println(edges);
        System.out.println("Finished Routing");
    }

    public void stopFireEvacuationSystem(){
        System.out.println("Stopping system");
    }

    public CameraController getCameraController(){
        return cameraController;
    }
}
