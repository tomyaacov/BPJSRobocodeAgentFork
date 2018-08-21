package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.ScannedRobotEvent;

@SuppressWarnings("serial")
public class ScannedRobot extends RobotSensorEvent {
	ScannedRobotEvent event;

	public ScannedRobot(ScannedRobotEvent event) {
		super("ScannedRobot");// TODO: shouldnt we add event to the constructor or override get data
		this.event = event;
	}

	@Override
	public String toString() {
		return "ScannedRobot [event=" + event + "]";
	}

	@Override
	public Object getData() {
		return event;
	}
}
