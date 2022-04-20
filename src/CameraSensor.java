import java.util.ArrayList;

public class CameraSensor {
    int id;

    public CameraSensor(int Id){
        id = Id;
    }

    public void setId(int Id){
        id = Id;
    }

    public Integer[] getImage(){
        Integer[] image = new Integer[100];
        for(int i=0; i < image.length; i++)
            image[i] = (int) (Math.random()*6*i) + 1;
        return image;
    }
}
