package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.WinEvent;

@SuppressWarnings("serial")
public class Win extends RobotSensorEvent {

    public Win(WinEvent event) {
        super("Win", event);
    }
}
