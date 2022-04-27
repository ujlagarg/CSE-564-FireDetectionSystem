package fireController;

public class TempDetectorSensor {
    int id;
    double temperature = 32.0;

    public TempDetectorSensor(int Id) {
        setId(Id);
    }

    public void setId(int Id) {
        id = Id;
    }

    public void setTemperature(double temp) {
        temperature = temp;
    }

    public int getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }
}
