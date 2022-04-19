public class LEDPanel {
    int id;
    Boolean state = false;

    public LEDPanel(int Id){
        id = Id;
    }

    public void setId(int Id){
        id = Id;
    }

    public int getId(int Id){
        return id;
    }
}
