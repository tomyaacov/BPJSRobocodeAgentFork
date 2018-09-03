package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitByBulletEvent;

@SuppressWarnings("serial")
public class HitByBullet extends RobotSensorEvent {

    public HitByBullet(HitByBulletEvent event) {
        super("HitByBullet", event);
    }

}
