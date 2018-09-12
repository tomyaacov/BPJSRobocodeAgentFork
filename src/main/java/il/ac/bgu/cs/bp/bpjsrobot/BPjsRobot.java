package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjs.eventselection.SimpleEventSelectionStrategy;
import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.*;
import org.mozilla.javascript.Scriptable;

import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.StreamLoggerListener;
import robocode.*;

public class BPjsRobot extends AdvancedRobot {

	BPjsRobot robot = this;
	protected SingleResourceBProgram bprog;
	StatusEvent lastStatus;
	public long lastExecute = System.nanoTime();

    public BPjsRobot(){
        bprog = new SingleResourceBProgram("MyFirstRobot.js", "MyFirstRobot.js", new SimpleEventSelectionStrategy()) {
            protected void setupProgramScope(Scriptable scope) {
                putInGlobalScope("robot", robot);// enables getting robots status
                super.setupProgramScope(scope);
            }
        };
    }

	public BPjsRobot(String resourceName){
	    bprog = new SingleResourceBProgram(resourceName, resourceName, new SimpleEventSelectionStrategy()) {
            protected void setupProgramScope(Scriptable scope) {
                putInGlobalScope("robot", robot);// enables getting robots status
                super.setupProgramScope(scope);
            }
        };
    }

	public void run() {
		System.out.println("---- start -----");
		bprog.setDaemonMode(true);
		bprog.addListener(new StreamLoggerListener());
		bprog.addListener(new RobocodeEventListener(this));
		//adding custom events
		addCustomEvent(new MoveCompleteCondition(this));
		addCustomEvent(new GunTurnCompleteCondition(this));
		addCustomEvent(new RadarTurnCompleteCondition(this));
		addCustomEvent(new TurnCompleteCondition(this));
		// go!
		try {
			bprog.start();

		} catch (InterruptedException e) {
			e.printStackTrace(out);
		}

		System.out.println("---- done -----");
	}

	@Override
	public void onCustomEvent(CustomEvent event) {
		if(event.getCondition() instanceof MoveCompleteCondition){ bprog.enqueueExternalEvent(MotionEnded.event); }
		if(event.getCondition() instanceof GunTurnCompleteCondition){ bprog.enqueueExternalEvent(GunRevEnded.event); }
		if(event.getCondition() instanceof RadarTurnCompleteCondition){ bprog.enqueueExternalEvent(RadarRevEnded.event); }
		if(event.getCondition() instanceof TurnCompleteCondition){ bprog.enqueueExternalEvent(RevEnded.event); }
	}

	@Override
	public void onStatus(StatusEvent e) {
    	//if(lastStatus != null) {
    	//	if (e.getStatus().getDistanceRemaining() == 0 && lastStatus.getStatus().getDistanceRemaining() != 0) {
		//		bprog.enqueueExternalEvent(MotionEnded.event);
		//	}
//
		//	if (e.getStatus().getTurnRemaining() == 0 && lastStatus.getStatus().getTurnRemaining() != 0) {
		//		bprog.enqueueExternalEvent(RevEnded.event);
		//	}
//
		//	if (e.getStatus().getGunTurnRemaining() == 0 && lastStatus.getStatus().getGunTurnRemaining() != 0) {
		//		bprog.enqueueExternalEvent(GunRevEnded.event);
		//	}
		//}
		bprog.enqueueExternalEvent(new Status(e));
		//lastStatus = e;
	}

    @Override
    public void onWin(WinEvent e) { bprog.enqueueExternalEvent(new Win(e)); }

	@Override
	public void onDeath(DeathEvent event) {
    	bprog.enqueueExternalEvent(new Death(event)); }

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		bprog.enqueueExternalEvent(new ScannedRobot(e));
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e){
		bprog.enqueueExternalEvent(new HitByBullet(e));
	}

	@Override
	public void onHitRobot(HitRobotEvent e){
		bprog.enqueueExternalEvent(new HitRobot(e));
	}

	@Override
	public void onSkippedTurn(SkippedTurnEvent event) {
		robot.setDebugProperty("SkippedTurn","");
	}

	@Override
	public void onHitWall(HitWallEvent e) { bprog.enqueueExternalEvent(new HitWall(e));}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
    	System.out.println("----round ended----");
    	bprog.setDaemonMode(false); }
}
