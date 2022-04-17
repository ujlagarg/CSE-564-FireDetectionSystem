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
        controller.setFireDetectorSignal(true); // for now has been hard coded. Will be taking this after computing values from Fire controller
        while(true){
            for(int i = 0; i< numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
            }
        }

        // For Testing Person Detector
        // PersonDetector pd = new PersonDetector();
        // Map<Integer, Integer[]> imageMap = new HashMap<Integer, Integer[]>();
        // imageMap.put(1, new Integer[]{ 1,2,3,4,5,6,7,8,9,10 });
        // imageMap.put(2, new Integer[]{ 1,2,3,4,5,7,8,9,10 });
        // imageMap.put(3, new Integer[]{ 1,2,3,4,5,7,8,9,10 });
        // imageMap.put(4, new Integer[]{ 1,2,3,6,7,8,9,10 });
        // imageMap.put(5, new Integer[]{ 4,5,6,7,8,9,10 });
        // imageMap.put(6, new Integer[]{ 3,4,5,6,7,8,9,10 });
        // imageMap.put(7, new Integer[]{ 3,4,5,6,7,8,9,10 });
        // System.out.print(pd.detectPeople(imageMap));


    }
}
