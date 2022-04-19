public class Alarm {
    int id;
    boolean state = false;

    public Alarm() {

    }

    public void setId(Integer newId) {
        id = newId;
    }

    public Integer getId() {
        return id;
    }

    public void setState(boolean newState) {
        state = newState;
    }

    public boolean getState() {
        return state;
    }
}
