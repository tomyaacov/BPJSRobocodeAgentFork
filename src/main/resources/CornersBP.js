importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);
importPackage(Packages.robocode.util);

others = 0;
corner = 0;
stopWhenSeeRobot = false;

var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var GunRevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof GunRevEnded);
});
var ScannedRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof ScannedRobot);
});
var MotionEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof MotionEnded);
});
var StatusEventSet = bp.EventSet('', function(e) {
    return (e instanceof Status);
});
var notStopEventSet = bp.EventSet('', function(e) {
    return !(e instanceof Stop);
});
var DeathEventSet = bp.EventSet('', function(e) {
    return (e instanceof Death);
});

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.red)});
    bsync({request : SetGunColor(Color.black)});
    bsync({request : SetRadarColor(Color.yellow)});
    bsync({request : SetBulletColor(Color.green)});
    bsync({request : SetScanColor(Color.green)});

    var e = bsync({waitFor : StatusEventSet});
    others = e.getStatus().getOthers();
    bsync({request : SetTurnRight(Utils.normalRelativeAngleDegrees(corner - e.getStatus().getHeading()))});
    bsync({waitFor : RevEndedEventSet});
    stopWhenSeeRobot = true;
    bsync({request : SetAhead(5000)});
    bsync({waitFor : MotionEndedEventSet});
    bsync({request : SetTurnLeft(90)});
    bsync({waitFor : RevEndedEventSet});
    bsync({request : SetAhead(5000)});
    bsync({waitFor : MotionEndedEventSet});
    bsync({request : SetTurnGunLeft(90)});
    bsync({waitFor : GunRevEndedEventSet});

    var gunIncrement = 3;

    var i;
    while (true) {
        for (i = 0; i < 30; i++) {
            bsync({request : SetTurnGunLeft(gunIncrement)});
            bsync({waitFor : GunRevEndedEventSet});
        }
        gunIncrement *= -1;
    }
});

bp.registerBThread("onScannedRobot", function() {
    while (true) {
        var e2 = bsync({waitFor: StatusEventSet});
        var e1 = bsync({waitFor: ScannedRobotEventSet});
        if (stopWhenSeeRobot) {
            bsync({request: Stop(), block: notStopEventSet});
        }
        if (e1.getData().getDistance() > 200 || e2.getStatus().getEnergy() < 15) {
            bsync({request: Fire(1)});
        } else if (e1.getData().getDistance() > 50) {
            bsync({request: Fire(2)});
        } else {
            bsync({request: Fire(3)});
        }
        if (stopWhenSeeRobot) {
            bsync({request: Scan()});
            bsync({request: Resume()});
        }
    }
});

bp.registerBThread("onDeath", function() {
    while (true){
        var e2 = bsync({waitFor: StatusEventSet});
        var e1 = bsync({waitFor: DeathEventSet});
        if (others != 0){
            if ((others - e2.getStatus().getOthers()) / others < .75) {
                corner += 90;
                if (corner == 270) {
                    corner = -90;
                }
                bp.log.info("I died and did poorly... switching corner to " + corner);
            } else {
                bp.log.info("I died but did well.  I will still use corner " + corner);
            }
        }
    }
});