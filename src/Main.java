import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        // this is the entry point of our project. All classes will be initialized over here.
        int numberOfRooms = 10;
        HashMap<Integer, ArrayList<Integer>> roomGraph = new HashMap<Integer, ArrayList<Integer>>(10);
        roomGraph.put(1, new ArrayList<Integer>(Arrays.asList(2,3,8)));
        roomGraph.put(2, new ArrayList<Integer>(Arrays.asList(1,4,5,7,9)));
        roomGraph.put(3, new ArrayList<Integer>(Arrays.asList(1,4,5,9)));
        roomGraph.put(4, new ArrayList<Integer>(Arrays.asList(2,3,6,8)));
        roomGraph.put(5, new ArrayList<Integer>(Arrays.asList(2,3,9)));
        roomGraph.put(6, new ArrayList<Integer>(Arrays.asList(4,7,8)));
        roomGraph.put(7, new ArrayList<Integer>(Arrays.asList(2,6)));
        roomGraph.put(8, new ArrayList<Integer>(Arrays.asList(1,4,6)));
        roomGraph.put(9, new ArrayList<Integer>(Arrays.asList(2,3,5,10)));
        roomGraph.put(0, new ArrayList<Integer>(Arrays.asList(9)));
        System.out.println(roomGraph);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter temperature and smoke reading for any 1 room seperated by comma(,):");
        String[] readings = reader.readLine().split(",");
        int tempReading = Integer.parseInt(readings[0]);
        int smokeReading = Integer.parseInt(readings[1]);
        System.out.println(tempReading+" "+smokeReading);

        // initializing Sensors and detectors
        ArrayList<SmokeDetectorSensor> smokeSensors = new ArrayList<SmokeDetectorSensor>();
        ArrayList<TempDetectorSensor> tempSensors = new ArrayList<TempDetectorSensor>();
        ArrayList<CameraSensor> cameraSensors = new ArrayList<CameraSensor>();
        ArrayList<LEDPanel> ledPanelSensors = new ArrayList<LEDPanel>();
        ArrayList<FireDetector> fireDetectors = new ArrayList<FireDetector>();
        for(int i = 0; i< numberOfRooms; i++) {
            smokeSensors.add(new SmokeDetectorSensor(i));
            tempSensors.add(new TempDetectorSensor(i));
            fireDetectors.add(new FireDetector(i,smokeSensors.get(i),tempSensors.get(i)));
            cameraSensors.add(new CameraSensor(i));
            ledPanelSensors.add(new LEDPanel(i));
        }

        // initializing Primary Controller
        PrimaryController controller = new PrimaryController(numberOfRooms);
        CameraController cameraController = controller.getCameraController();
        FireController fireController = new FireController();
        Map<Integer, Boolean> fireDetectedMap = new HashMap();

       while(fireDetectedMap.containsValue(true)){
            for(int i = 0; i< numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
                fireDetectedMap.put(i, fireDetectors.get(i).getValue());
            }
            if(fireDetectedMap.containsValue(true)){
                fireController.setFire(); // setting fire = true in fire controller
                controller.setFireDetectorSignal(fireDetectedMap);
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
