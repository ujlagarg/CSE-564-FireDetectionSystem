import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello World");
        // this is the entry point of our project. All classes will be initialized over here.

        // initializing Sensors and detectors
        int numberOfRooms = 10;
        ArrayList<SmokeDetectorSensor> smokeSensors = new ArrayList<SmokeDetectorSensor>();
        ArrayList<TempDetectorSensor> tempSensors = new ArrayList<TempDetectorSensor>();
        ArrayList<CameraSensor> cameraSensors = new ArrayList<CameraSensor>();
        ArrayList<LEDPanelSensor> ledPanelSensors = new ArrayList<LEDPanelSensor>();
        ArrayList<FireDetector> fireDetectors = new ArrayList<FireDetector>();

        for(int i = 0; i< numberOfRooms; i++) {
            smokeSensors.add(new SmokeDetectorSensor(i));
            tempSensors.add(new TempDetectorSensor(i));
            fireDetectors.add(new FireDetector(i));
            cameraSensors.add(new CameraSensor(i));
            ledPanelSensors.add(new LEDPanelSensor(i));
        }

        // initializing Primary Controller
        PrimaryController controller = new PrimaryController(numberOfRooms);
        CameraController cameraController = controller.getCameraController();
        Map<Integer, Boolean> fireDetectedMap = new HashMap<>();
        while(true){
            for(int i = 0; i< numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
                fireDetectedMap.put(i, fireDetectors.get(i).getFireDetectedBool());
            }
            if(fireDetectedMap.containsValue(true)) {
                controller.setFireDetectorSignal(fireDetectedMap);
            }
        }

    }
}
