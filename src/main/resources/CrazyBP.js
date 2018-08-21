importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

movingForward = false;

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(new Color(0, 200, 0))});
    bsync({request : SetGunColor(new Color(0, 150, 50))});
    bsync({request : SetRadarColor(new Color(0, 100, 100))});
    bsync({request : SetScanColor(new Color(255, 200, 200))});
    bsync({request : SetBulletColor(new Color(255, 255, 100))});
    while (true) {
        bsync({request : TurnGunRight(5)});
    }
});


