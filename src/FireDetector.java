public class FireDetector {
    int id;
    boolean value;

    public FireDetector(int Id, SmokeDetectorSensor s, TempDetectorSensor t){
       setId(Id);
       if(s.getSmoke()>=0.14 || t.getTemperature()>=57)
           setValue(true);
       else
           setValue(false);
    }

    public void setId(int Id){
        id = Id;
    }
    public void setValue(boolean value){
        this.value=value;
    }
    public boolean getValue(){
       return value;
    }
}
