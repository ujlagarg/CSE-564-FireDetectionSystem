import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        // this is the entry point of our project. All classes will be initialized over
        // here.
        int numberOfRooms = 5;
        // initializing Sensors and detectors

        ArrayList<SmokeDetectorSensor> smokeSensors = new ArrayList<SmokeDetectorSensor>();
        ArrayList<TempDetectorSensor> tempSensors = new ArrayList<TempDetectorSensor>();
        ArrayList<CameraSensor> cameraSensors = new ArrayList<CameraSensor>();
        ArrayList<LEDPanel> ledPanelSensors = new ArrayList<LEDPanel>();
        ArrayList<FireDetector> fireDetectors = new ArrayList<FireDetector>();
        for (int i = 0; i < numberOfRooms; i++) {
            smokeSensors.add(new SmokeDetectorSensor(i));
            tempSensors.add(new TempDetectorSensor(i));
            fireDetectors.add(new FireDetector(i, smokeSensors.get(i), tempSensors.get(i)));
            cameraSensors.add(new CameraSensor(i));
            ledPanelSensors.add(new LEDPanel(i));
        }

        // initializing Primary Controller
        PrimaryController controller = new PrimaryController(numberOfRooms);
        CameraController cameraController = controller.getCameraController();
        FireController fireController = new FireController(fireDetectors);
        PersonDetector personDetector = new PersonDetector();
        Router router = new Router();

        /**
         * Start a fire somewhere before calling while
         */



        while (true) {
            Map<Integer, Boolean> fireDetectedMap = fireController.getFireDetectedMap();

            if (fireDetectedMap.containsValue(true)) {
                controller.setFireDetectorSignal(fireDetectedMap);
            }

            /**
             * Capture Images from Cameras
             */
            for (int i = 0; i < numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
            }

            /**
             * Detect number of persons
             */
            Map<Integer, Integer> peopleDetected = personDetector.detectPeople(cameraController.imageMap);

            /**
             * Do Routing
             */
            System.out.println("Routing");
            fireDetectedMap.put(4, true);
            System.out.println(peopleDetected.toString());
            System.out.println(fireDetectedMap.toString());
            router.update(peopleDetected, fireDetectedMap);
            System.out.println("Finished routing");

             
            /** Light Up LED */

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
