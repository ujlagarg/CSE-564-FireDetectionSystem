import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CameraController {
    Map<Integer, ArrayList<Integer>> imageMap = new HashMap<>();
    public Map<Integer, ArrayList<Integer>> getImageMap(){
        return imageMap;
    }

    public void updateImageMap(Integer key, ArrayList<Integer> image){
        if(image.size() > 0)
            imageMap.put(key, image);
        else{
            if(imageMap.containsKey(key))
                imageMap.replace(key, image);
            else
                imageMap.put(key, null);
        }
    }
}
