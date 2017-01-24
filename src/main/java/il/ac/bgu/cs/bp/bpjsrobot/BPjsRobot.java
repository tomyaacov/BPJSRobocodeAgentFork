package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.ScannedRobot;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.Status;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

public class BPjsRobot extends Robot {
	
	final SingleResourceBProgram bprog = new SingleResourceBProgram("MyFirstRobot.js");
	
	
	public void run() {
		bprog.addListener(new RobocodeEventListener(this));

		bprog.setDaemonMode(true);
		
		// go!
		try {
			bprog.start();
		} catch (InterruptedException e) {
			e.printStackTrace(out);
		}

	}

	@Override
	public void onStatus(StatusEvent e) {
		bprog.enqueueExternalEvent(new Status(e));
	
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		bprog.enqueueExternalEvent(new ScannedRobot(e));
		
		//fire(1);
	}
}