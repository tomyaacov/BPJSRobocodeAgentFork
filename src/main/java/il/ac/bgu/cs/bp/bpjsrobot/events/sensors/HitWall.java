package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitWallEvent;

@SuppressWarnings("serial")
public class HitWall extends RobotSensorEvent {
    HitWallEvent event;

    public HitWall(HitWallEvent event) {
        super("HitWall");
        this.event = event;
    }

    @Override
    public String toString() {
        return "HitWall [event=" + event + "]";
    }

    @Override
    public Object getData() {
        return event;
    }
}
