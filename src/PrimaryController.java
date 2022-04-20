import java.util.HashMap;
import java.util.Map;

public class PrimaryController {
    int noOfRooms = 0;
    CameraController cameraController;
    PersonDetector personDetector;
    Router router;
    LEDController ledController;
    Map<Integer, Boolean> fireMap;

    public PrimaryController(int numberOfRooms){
        noOfRooms = numberOfRooms;
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
//        personDetector.detectPeople(cameraController.getImageMap());
    }

    public void stopFireEvacuationSystem(){
        System.out.println("Stopping system");
//        personDetector.detectPeople(cameraController.getImageMap());
    }

    public CameraController getCameraController(){
        return cameraController;
    }
}
