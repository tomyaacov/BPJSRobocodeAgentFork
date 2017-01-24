package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.ScannedRobotEvent;

@SuppressWarnings("serial")
public class ScannedRobot extends RobotSensorEvent {
	ScannedRobotEvent event;

	public ScannedRobot(ScannedRobotEvent event) {
		super("ScannedRobot");
		this.event = event;
	}

	@Override
	public String toString() {
		return "ScannedRobot [event=" + event + "]";
	}
	
	
}
