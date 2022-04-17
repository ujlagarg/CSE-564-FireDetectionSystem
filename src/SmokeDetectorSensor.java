public class SmokeDetectorSensor {
    int id;
    String name = "";

    public SmokeDetectorSensor(int Id){
        id = Id;
    }
    public SmokeDetectorSensor(String nameVal){
        id = (int) Math.random();
        name = nameVal;
    }

    public void setId(int Id){
        id = Id;
    }
}
