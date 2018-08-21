package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.HitByBulletEvent;

@SuppressWarnings("serial")
public class HitByBullet extends RobotSensorEvent {
    HitByBulletEvent event;

    public HitByBullet(HitByBulletEvent event) {
        super("HitByBullet");
        this.event = event;
    }

    @Override
    public String toString() {
        return "HitByBullet [event=" + event + "]";
    }

    @Override
    public Object getData() {
        return event;
    }
}
