package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjsrobot.events.sensors.*;
import org.mozilla.javascript.Scriptable;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.listeners.StreamLoggerListener;
import robocode.*;

public class CrazyBPjsRobot extends BPjsRobot {

    BPjsRobot robot = this;

    protected SingleResourceBProgram bprog = new SingleResourceBProgram("CrazyBP.js", "CrazyBP.js", new RobocodeEventSelectionStrategy()) {
        protected void setupProgramScope(Scriptable scope) {
            putInGlobalScope("robot", robot);// enables getting robots status
            super.setupProgramScope(scope);
        }
    };

    public void run() {
        System.out.println("---- start -----");
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

        if (e.getStatus().getDistanceRemaining() == 0) {
            bprog.enqueueExternalEvent(MotionEnded.event);
        }

        if (e.getStatus().getTurnRemaining() == 0) {
            bprog.enqueueExternalEvent(RevEnded.event);
        }
    }

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
    public void onHitWall(HitWallEvent e) { bprog.enqueueExternalEvent(new HitWall(e));}

}
