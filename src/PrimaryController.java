public class PrimaryController {
    int noOfRooms = 0;
    CameraController cameraController;
    PersonDetector personDetector;
    Router router;
    LEDController ledController;
    public PrimaryController(int numberOfRooms){
        noOfRooms = numberOfRooms;
        cameraController = new CameraController();
        personDetector = new PersonDetector();
        router = new Router();
        ledController = new LEDController();
    }

    public void setFireDetectorSignal(boolean value){
        if(value == true)
            startFireEvacuationSystem();
    }

    public void startFireEvacuationSystem(){
        startCameraControllerSystem();
    }

    public void startCameraControllerSystem(){
        cameraController.startCameraController();
    }

    public CameraController getCameraController(){
        return cameraController;
    }
}
