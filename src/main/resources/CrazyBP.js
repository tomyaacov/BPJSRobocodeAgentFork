importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

movingForward = false;

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

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(new Color(0/255, 200/255, 0/255))});//TODO: figure out why new colors cant be initialized as int
    bsync({request : SetGunColor(new Color(0/255, 150/255, 50/255))});
    bsync({request : SetRadarColor(new Color(0/255, 100/255, 100/255))});
    bsync({request : SetScanColor(new Color(255/255, 200/255, 200/255))});
    bsync({request : SetBulletColor(new Color(255/255, 255/255, 100/255))});
    while (true) {
        bsync({request : SetAhead(4000)});
        movingForward = true;
        bsync({request : SetTurnRight(90)});
        bsync({waitFor : RevEndedEventSet});
        bsync({request : SetTurnLeft(180)});
        bsync({waitFor : RevEndedEventSet});
        bsync({request : SetTurnRight(180)});
        bsync({waitFor : RevEndedEventSet});
    }
});

bp.registerBThread("onScannedRobot", function() {
    while(true){
        bsync({waitFor : ScannedRobotEventSet});
        bsync({request : Fire(1)});
    }
});

bp.registerBThread("onHit", function() {
    while(true){
        var e = bsync({waitFor : HitRobotWallEventSet});
        if((e instanceof HitWall) || (e instanceof HitRobot && e.getData().isMyFault())){
            if (movingForward) {
                bsync({request : SetBack(40000)});
                movingForward = false;
            } else {
                bsync({request : SetAhead(40000)});
                movingForward = true;
            }
        }
    }
});

