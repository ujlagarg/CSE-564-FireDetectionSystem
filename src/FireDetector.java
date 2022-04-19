public class FireDetector {
    int id;
    boolean fireDetected = true;

    public FireDetector(int Id){
        id = Id;
    }

    public void setId(int Id){
        id = Id;
    }

    public Boolean getFireDetectedBool(){
        return fireDetected;
    }
}
