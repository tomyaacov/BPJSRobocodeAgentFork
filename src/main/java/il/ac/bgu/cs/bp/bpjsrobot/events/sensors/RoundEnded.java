package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import robocode.RoundEndedEvent;

@SuppressWarnings("serial")
public class RoundEnded extends RobotSensorEvent {

	public RoundEnded(RoundEndedEvent e) {
		super("RoundEnded", e);
	}


}
