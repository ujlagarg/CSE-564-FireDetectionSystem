package fireController;

public class FireDetector {
    int id;
    boolean state;
    public SmokeDetectorSensor smokeDetectorSensor;
    public TempDetectorSensor tempDetectorSensor;

    public FireDetector(int Id, SmokeDetectorSensor s, TempDetectorSensor t) {
        setId(Id);
        tempDetectorSensor = t;
        smokeDetectorSensor = s;
        setState();
    }

    public void setId(int Id) {
        id = Id;
    }

    public int getId() {
        return id;
    }

    public void setState() {
        if (smokeDetectorSensor.getSmoke() >= 0.14 || tempDetectorSensor.getTemperature() >= 57)
            this.state = true;
        else
            this.state = false;
    }

    public boolean getState() {
        return state;
    }
}
