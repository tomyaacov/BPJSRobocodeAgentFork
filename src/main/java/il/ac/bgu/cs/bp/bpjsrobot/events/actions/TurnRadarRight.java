package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class TurnRadarRight extends RobotActionEvent {
	int angle;

	public TurnRadarRight(int angle) {
		super();
		this.angle = angle;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.turnRadarRight(angle);
	}

	@Override
	public String toString() {
		return "TurnRadarRight [angle=" + angle + "]";
	}

}