package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class Back extends RobotActionEvent {
	int distance;

	public Back(int distance) {
		super();
		this.distance = distance;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.back(distance);
	}

	@Override
	public String toString() {
		return "" + "Back [distance=" + distance + "]";
	}

}