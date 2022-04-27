package primaryController;

public class SoundController {
    Alarm[] alarms = new Alarm[250];

    public SoundController() {

    }

    public void setState(boolean state) {
        /**
         * Switch primaryController.Alarm Actuators
         */
        for (Alarm alarm : alarms) {
            alarm.setState(state);
        }
    }
}
