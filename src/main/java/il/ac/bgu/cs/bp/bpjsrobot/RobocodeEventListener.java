package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BThreadSyncSnapshot;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.BProgramListener;
import il.ac.bgu.cs.bp.bpjs.events.BEvent;
import il.ac.bgu.cs.bp.bpjsrobot.events.actions.RobotActionEvent;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.RobotSensorEvent;

public class RobocodeEventListener implements BProgramListener {
	private BPjsRobot robot;

	public RobocodeEventListener(BPjsRobot bPjsRobot) {
		this.robot = bPjsRobot;
	}

	@Override
	public void started(BProgram bp) {
	}

	@Override
	public void bthreadAdded(BProgram bp, BThreadSyncSnapshot theBThread) {
	}

	@Override
	public void bthreadRemoved(BProgram bp, BThreadSyncSnapshot theBThread) {
	}

	@Override
	public void eventSelected(BProgram bp, BEvent theEvent) {

		if (theEvent instanceof RobotActionEvent) {
			((RobotActionEvent) theEvent).act(robot);
		} else if (theEvent instanceof RobotSensorEvent) {
		} else {
			robot.out.println("Uhandled BPjs event:" + theEvent);
		}

	}

	@Override
	public void superstepDone(BProgram bp) {
	}

	@Override
	public void ended(BProgram bp) {
	}

}