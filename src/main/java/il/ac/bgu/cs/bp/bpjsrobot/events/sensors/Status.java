package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.RobotStatus;
import robocode.StatusEvent;

@SuppressWarnings("serial")
public class Status extends RobotSensorEvent {

	public Status(StatusEvent e) {
		super("Status", e);
	}

	/**
	 * @return the e
	 */
	public RobotStatus getStatus() { return ((StatusEvent)event).getStatus(); }

}
