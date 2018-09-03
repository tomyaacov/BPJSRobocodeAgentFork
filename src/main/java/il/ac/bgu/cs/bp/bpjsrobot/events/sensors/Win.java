package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.WinEvent;

@SuppressWarnings("serial")
public class Win extends RobotSensorEvent {
    WinEvent event;

    public Win(WinEvent event) {
        super("Win");// TODO: shouldnt we add event to the constructor or override get data
        this.event = event;
    }

    @Override
    public String toString() {
        return "Win [event=" + event + "]";
    }

    @Override
    public Object getData() {
        return event;
    }
}
