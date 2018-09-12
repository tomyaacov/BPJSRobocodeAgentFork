package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.DeathEvent;

@SuppressWarnings("serial")
public class Death extends RobotSensorEvent {

    public Death(DeathEvent event) {
        super("Death", event);
    }
}
