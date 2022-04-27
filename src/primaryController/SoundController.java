package primaryController;

public class SoundController {
    Alarm[] alarms = new Alarm[250];

    public SoundController() {
        for(int i = 0; i< alarms.length; i++)
            alarms[i] = new Alarm();
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
