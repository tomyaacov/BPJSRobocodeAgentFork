package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.StreamLoggerListener;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.MotionEnded;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.ScannedRobot;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.Status;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

public class BPjsRobot extends AdvancedRobot {

	final SingleResourceBProgram bprog = new SingleResourceBProgram("MyFirstRobot.js"); 

	public void run() {

		bprog.setDaemonMode(true);
		bprog.addListener(new StreamLoggerListener());
		bprog.addListener(new RobocodeEventListener(this));

		// go!
		try {
			bprog.start();

		} catch (InterruptedException e) {
			e.printStackTrace(out);
		}

		System.out.println("---- done -----");
	}

	@Override
	public void onStatus(StatusEvent e) {
		bprog.enqueueExternalEvent(new Status(e));

		System.out.println("e.getStatus().getDistanceRemaining()=" + e.getStatus().getDistanceRemaining());

		if (e.getStatus().getDistanceRemaining() == 0) {
			bprog.enqueueExternalEvent(MotionEnded.event);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		bprog.enqueueExternalEvent(new ScannedRobot(e));
	}

}
