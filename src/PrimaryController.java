public class PrimaryController {
    public PrimaryController(){
        CameraController cameraController = new CameraController();
        PersonDetector personDetector = new PersonDetector();
        Router router = new Router();
        LEDController ledController = new LEDController();
    }
}
