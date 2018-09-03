package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitWallEvent;

@SuppressWarnings("serial")
public class HitWall extends RobotSensorEvent {

    public HitWall(HitWallEvent event) {
        super("HitWall", event);
    }

}
