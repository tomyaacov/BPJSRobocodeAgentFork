package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class TurnGunLeft extends RobotActionEvent {
	int angle;

	public TurnGunLeft(int angle) {
		super();
		this.angle = angle;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.turnGunLeft(angle);
	}

	@Override
	public String toString() {
		return "TurnGunLeft [angle=" + angle + "]";
	}

}