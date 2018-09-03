importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

turnDirection = 1;

var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var MotionEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof MotionEnded);
});
var ScannedRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof ScannedRobot);
});
var HitRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitRobot);
});
var HitScannedRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitRobot) || (e instanceof ScannedRobot);
});

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.lightGray)});
    bsync({request : SetGunColor(Color.gray)});
    bsync({request : SetRadarColor(Color.darkGray)});
    while (true) {
        bsync({request : SetTurnRight(5*turnDirection)});
        bsync({waitFor : RevEndedEventSet});
    }
});

bp.registerBThread("onScannedRobot", function() {
    while(true){
        var e = bsync({waitFor : ScannedRobotEventSet});
        bsync({request : SetTurnRight(e.getData().getBearing())});
        bsync({waitFor : RevEndedEventSet, block : [SetTurnRight(5), SetTurnRight(-5)]});
        bsync({request : SetAhead(e.getData().getDistance() + 5), block : [SetTurnRight(5), SetTurnRight(-5)]});
        bsync({waitFor : MotionEndedEventSet, block : [SetTurnRight(5), SetTurnRight(-5)]});
        bsync({request : Scan(), block : [SetTurnRight(5), SetTurnRight(-5)]});

    }
});

bp.registerBThread("onHitRobot", function () {
    while(true){
        var e = bsync({waitFor : HitRobotEventSet});
        bsync({request : SetTurnRight(e.getData().getBearing())});
        bsync({waitFor : RevEndedEventSet, block : [SetTurnRight(5), SetTurnRight(-5)]});
        var energy = e.getData().getEnergy();
        if (energy > 16) {
            bsync({request : Fire(3), block : [SetTurnRight(5), SetTurnRight(-5)]});
        } else if (energy > 10) {
            bsync({request : Fire(2), block : [SetTurnRight(5), SetTurnRight(-5)]});
        } else if (energy > 4) {
            bsync({request : Fire(1), block : [SetTurnRight(5), SetTurnRight(-5)]});
        } else if (energy > 2) {
            bsync({request : Fire(0.5), block : [SetTurnRight(5), SetTurnRight(-5)]});
        } else if (energy > .4) {
            bsync({request : Fire(0.1), block : [SetTurnRight(5), SetTurnRight(-5)]});
        }
        bsync({request : SetAhead(40), block : [SetTurnRight(5), SetTurnRight(-5)]});
        bsync({waitFor : MotionEndedEventSet, block : [SetTurnRight(5), SetTurnRight(-5)]});
    }
});

bp.registerBThread("turnDirection", function () {
    while(true){
        var e = bsync({waitFor : HitScannedRobotEventSet});
        if (e.getData().getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
    }
});
