public class SmokeDetectorSensor {
    int id;
    double value;

    public SmokeDetectorSensor(int Id) {
        setId(Id);
        setSmokeDensity(Math.random());
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public void setSmokeDensity(double smoke) {
        value = smoke;
    }

    public int getId() {
        return id;
    }

    public double getSmoke() {
        return value;
    }
}
