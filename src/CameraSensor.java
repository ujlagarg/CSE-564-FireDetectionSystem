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
}
