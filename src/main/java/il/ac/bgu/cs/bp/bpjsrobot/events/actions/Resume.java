package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class Resume extends RobotActionEvent {
	public Resume() {
		super();
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.resume();
	}

	@Override
	public String toString() {
		return "Resume []";
	}
}