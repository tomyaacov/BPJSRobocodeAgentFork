package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.StatusEvent;

@SuppressWarnings("serial")
public class GunRevEnded extends RobotSensorEvent {

    public static GunRevEnded event  = new GunRevEnded(null);

    public GunRevEnded(StatusEvent e) {
        super("GunRevEnded", e);
    }

}
