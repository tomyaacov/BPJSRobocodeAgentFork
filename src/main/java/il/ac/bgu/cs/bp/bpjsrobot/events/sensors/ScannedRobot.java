package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.ScannedRobotEvent;

@SuppressWarnings("serial")
public class ScannedRobot extends RobotSensorEvent {

	public ScannedRobot(ScannedRobotEvent event) {
		super("ScannedRobot", event);
	}

}
