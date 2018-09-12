package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class Stop extends RobotActionEvent {
	boolean overwrite;

	public Stop(boolean overwrite) {
		super("Stop");
		this.overwrite = overwrite;
	}

	public Stop() {
		super("Stop");
		this.overwrite = false;
	}

	@Override
	public void act(BPjsRobot robot) {
		robot.stop(overwrite);
	}

	@Override
	public String toString() {
		return "Stop [overwrite=" + overwrite + "]";
	}
}