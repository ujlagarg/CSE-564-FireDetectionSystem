public class SmokeDetectorSensor {
    int id;
    double smokeDensity;

    public SmokeDetectorSensor(int Id) {
        setId(Id);
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public void setSmokeDensity(double smoke) {
        smokeDensity = smoke;
    }

    public int getId() {
        return id;
    }

    public double getSmoke() {
        return smokeDensity;
    }
}
