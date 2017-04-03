package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class Ahead extends RobotActionEvent {
	int distance;

	public Ahead(int distance) {
		super("Ahead");
		this.distance = distance;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.setAhead(distance);
	}

	@Override
	public String toString() {
		return "Ahead [distance=" + distance + "]";
	}
}