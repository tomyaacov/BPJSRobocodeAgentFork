importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var HitWallEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitWall);
});
var ScannedRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof ScannedRobot);
});
var HitRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitRobot);
});
var HitRobotWallEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitRobot) || (e instanceof HitWall);
});
var MotionEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof MotionEnded);
});

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.blue)});
    bsync({request : SetGunColor(Color.blue)});
    bsync({request : SetRadarColor(Color.black)});
    bsync({request : SetScanColor(Color.yellow)});
    while (true) {
        bsync({request : SetTurnRight(1000)});
        bsync({request : SetMaxVelocity(5)});
        bsync({request : SetAhead(10000)});
        bsync({waitFor : MotionEndedEventSet});
    }
});

bp.registerBThread("onScannedRobot", function() {
    while (true){
        bsync({ waitFor : ScannedRobotEventSet });
        bsync({request : Fire(3), block: bp.EventSet('', function(e) {return !(e instanceof Fire)})});

    }
});

bp.registerBThread("onHitRobot", function () {
    while(true){
        var e1 = bsync({waitFor : HitRobotEventSet});
        if(e1.getData().getBearing() > -10 && e1.getData().getBearing() < 10){
            bsync({request : Fire(3), block: bp.EventSet('', function(e) {return !(e instanceof Fire)})});
        }
        if(e1.getData().isMyFault()){
            bsync({request : SetTurnRight(10)});
            bsync({waitFor : RevEndedEventSet});
        }
    }
});