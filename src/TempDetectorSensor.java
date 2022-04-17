public class TempDetectorSensor {
    int id;
    String name = "";
    double temperature = 32.0;

    public TempDetectorSensor(int Id){
        id = Id;
    }
    public TempDetectorSensor(String nameVal){
        id = (int) Math.random();
        name = nameVal;
    }

    public void setId(int Id){
        id = Id;
    }
    public void setTemperature(double temp){temperature = temp;}

    public double getTemperature() {
        return temperature;
    }
}
