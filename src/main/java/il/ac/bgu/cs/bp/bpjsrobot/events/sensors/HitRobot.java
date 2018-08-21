package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitRobotEvent;

@SuppressWarnings("serial")
public class HitRobot extends RobotSensorEvent {
    HitRobotEvent event;

    public HitRobot(HitRobotEvent event) {
        super("HitRobot");
        this.event = event;
    }

    @Override
    public String toString() {
        return "HitRobot [event=" + event + "]";
    }

    @Override
    public Object getData() {
        return event;
    }
}
