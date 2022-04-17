public class LEDPanelSensor {
    int id;
    String name = "";

    public LEDPanelSensor(int Id){
        id = Id;
    }
    public LEDPanelSensor(String nameVal){
        id = (int) Math.random();
        name = nameVal;
    }

    public void setId(int Id){
        id = Id;
    }
}
