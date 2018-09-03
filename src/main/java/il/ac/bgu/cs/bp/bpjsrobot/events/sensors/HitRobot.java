package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitRobotEvent;

@SuppressWarnings("serial")
public class HitRobot extends RobotSensorEvent {

    public HitRobot(HitRobotEvent event) {
        super("HitRobot", event);
    }

}
