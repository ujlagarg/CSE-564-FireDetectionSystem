package primaryController;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CameraController {
    Map<Integer, Integer[]> imageMap = new HashMap<>();
    public Map<Integer, Integer[]> getImageMap(){
        return imageMap;
    }

    public void updateImageMap(Integer key, Integer[] image){
        if(image.length > 0)
            imageMap.put(key, image);
        else{
            if(imageMap.containsKey(key))
                imageMap.replace(key, image);
            else
                imageMap.put(key, null);
        }
    }
}
