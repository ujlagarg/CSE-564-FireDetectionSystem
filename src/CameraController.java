import java.util.Map;
import java.util.HashMap;

public class CameraController {
    Map<Integer, Integer[]> imageMap;
    public void CameraController(){
        imageMap = new HashMap<Integer, Integer[]>();
    }
    public void startCameraController(){

    }

    public void updateImageMap(Integer key, Integer[] image){
        if(image.length > 0)
            imageMap.put(key, image);
        else
            imageMap.putIfAbsent(key, null);
    }
}
