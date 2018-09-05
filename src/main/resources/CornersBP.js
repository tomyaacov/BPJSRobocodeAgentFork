importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

others = 0;
corner = 0;
stopWhenSeeRobot = false;

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
var StatusEventSet = bp.EventSet('', function(e) {
    return (e instanceof Status);
});

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.red)});
    bsync({request : SetGunColor(Color.black)});
    bsync({request : SetRadarColor(Color.yellow)});
    bsync({request : SetBulletColor(Color.green)});
    bsync({request : SetScanColor(Color.green)});

    var e = bsync({waitFor : StatusEventSet});
    others = e.getStatus().getOthers();

    stopWhenSeeRobot = false;
    // turn to face the wall to the "right" of our desired corner.
    turnRight(normalRelativeAngleDegrees(corner - getHeading()));
    // Ok, now we don't want to crash into any robot in our way...
    stopWhenSeeRobot = true;
    // Move to that wall
    ahead(5000);
    // Turn to face the corner
    turnLeft(90);
    // Move to the corner
    ahead(5000);
    // Turn gun to starting point
    turnGunLeft(90);

    while (true) {
        bsync({request : SetTurnRight(1000)});
        bsync({request : SetMaxVelocity(5)});
        bsync({request : SetAhead(10000)});
        bsync({waitFor : MotionEndedEventSet});
    }
});