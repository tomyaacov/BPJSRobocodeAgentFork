package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class TurnRight extends RobotActionEvent {
	int angle;

	public TurnRight(int angle) {
		super();
		this.angle = angle;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.turnRight(angle);
	}

	@Override
	public String toString() {
		return "TurnRight [angle=" + angle + "]";
	}
}