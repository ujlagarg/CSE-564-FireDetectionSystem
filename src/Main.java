import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello World");
        // this is the entry point of our project. All classes will be initialized over here.
        int numberOfRooms = 10;
        // initializing Sensors and detectors

        ArrayList<SmokeDetectorSensor> smokeSensors = new ArrayList<SmokeDetectorSensor>();
        ArrayList<TempDetectorSensor> tempSensors = new ArrayList<TempDetectorSensor>();
        ArrayList<CameraSensor> cameraSensors = new ArrayList<CameraSensor>();
        ArrayList<LEDPanelSensor> ledPanelSensors = new ArrayList<LEDPanelSensor>();
        ArrayList<FireDetector> fireDetectors = new ArrayList<FireDetector>();
        FireController FC;
        Boolean fireControllerStatus=false;
        for(int i = 0; i< numberOfRooms; i++) {
            smokeSensors.add(new SmokeDetectorSensor(i));
            tempSensors.add(new TempDetectorSensor(i));
            fireDetectors.add(new FireDetector(i,smokeSensors.get(i),tempSensors.get(i)));
            //cameraSensors.add(new CameraSensor(i));
            //ledPanelSensors.add(new LEDPanelSensor(i));
        }

        // initializing Primary Controller
        PrimaryController controller = new PrimaryController(numberOfRooms);
        //CameraController cameraController = controller.getCameraController();
        for (int i = 0; i < fireDetectors.size(); i++){
            if(fireDetectors.get(i).getValue()==true){
               FC = new FireController();
               fireControllerStatus = FC.setFire();
            }
        }
        System.out.println(fireControllerStatus);
        controller.setFireDetectorSignal(fireControllerStatus); // for now has been hard coded. Will be taking this after computing values from Fire controller
       /* while(true){
            for(int i = 0; i< numberOfRooms; i++) {
                cameraController.updateImageMap(i, cameraSensors.get(i).getImage());
            }
        }*/

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
