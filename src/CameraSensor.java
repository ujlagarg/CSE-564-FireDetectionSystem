import java.util.ArrayList;

public class CameraSensor {
    int id;
    String name = "";

    public CameraSensor(int Id){
        id = Id;
    }
    public CameraSensor(String nameVal){
        id = (int) Math.random();
        name = nameVal;
    }

    public void setId(int Id){
        id = Id;
    }

    public ArrayList<Integer> getImage(){
        ArrayList<Integer> image = new ArrayList<Integer>(100);
        for(int i=0; i < image.size(); i++)
            image.add((int) (Math.random()*6*i) + 1);
        return image;
    }
}
