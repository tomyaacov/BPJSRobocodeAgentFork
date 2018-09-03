package il.ac.bgu.cs.bp.bpjsrobot.events.sensors;

import il.ac.bgu.cs.bp.bpjs.events.BEvent;
import robocode.Event;

@SuppressWarnings("serial")
public class RobotSensorEvent extends BEvent {

	protected Event event;

	public RobotSensorEvent(String aName, Event e) {
		super(aName);
		event = e;
	}

	@Override
	public String toString() { return name + " [event=" + event + "]"; }

	@Override
	public Object getData() {
		return event;
	}

}
