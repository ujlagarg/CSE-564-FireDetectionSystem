import java.util.*;

import constants.Constants;
import fireController.FireController;
import fireController.FireDetector;
import fireController.SmokeDetectorSensor;
import fireController.TempDetectorSensor;
import primaryController.CameraController;
import primaryController.CameraSensor;
import primaryController.LEDPanel;
import primaryController.PrimaryController;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // this is the entry point of our project. All classes will be initialized over
        // here.
        int numberOfRooms = Constants.ROOM_COUNT;
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

        // initializing Primary Controller and Fire Controller
        FireController fireController = new FireController(fireDetectors);
        PrimaryController controller = new PrimaryController(numberOfRooms);
        CameraController cameraController = controller.getCameraController();

        /**
         * Start a fire somewhere before calling while
         */
        fireDetectors.get(0).smokeDetectorSensor.setSmokeDensity(0.5);
        fireDetectors.get(0).tempDetectorSensor.setTemperature(67);
        fireDetectors.get(0).setState();

        int roundCount = 1;
        while (true) {
            System.out.println("*** Round " + roundCount + " ***");
            /**
             * Capture Images from Cameras
             */
            for (int i = 0; i < numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
            }

            Map<Integer, Boolean> fireDetectedMap = fireController.getFireDetectedMap();
            if (fireDetectedMap.containsValue(true)) {
                controller.setFireDetectorSignal(fireDetectedMap);
            } else {
                controller.stopFireEvacuationSystem();
            }

            Thread.sleep(2000);

            roundCount += 1;

            fireController.spreadFire(controller.router.roomMap);
        }

    }
}
