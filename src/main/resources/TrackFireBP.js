importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);
importPackage(Packages.robocode.util);

var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var ScannedRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof ScannedRobot);
});
var WinEventSet = bp.EventSet('', function(e) {
    return (e instanceof Win);
});
var StatusEventSet = bp.EventSet('', function(e) {
    return (e instanceof Status);
});


bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.pink)});
    bsync({request : SetGunColor(Color.pink)});
    bsync({request : SetRadarColor(Color.pink)});
    bsync({request : SetScanColor(Color.pink)});
    bsync({request : SetBulletColor(Color.pink)});
    while (true) {
        bsync({request : SetTurnGunRight(10)});
        bsync({waitFor : RevEndedEventSet});
    }
});

bp.registerBThread("onScannedRobot", function() {
    while(true){
        var e1 = bsync({waitFor : ScannedRobotEventSet});
        var e2 = bsync({waitFor : StatusEventSet});
        var absoluteBearing = e2.getData().getHeading() + e1.getData().getBearing();
        var bearingFromGun = Utils.normalRelativeAngleDegrees(absoluteBearing - e2.getData().getGunHeading());
        bsync({request : SetTurnRight(bearingFromGun)});
        bsync({waitFor : RevEndedEventSet});
        if(Math.abs(bearingFromGun <= 3)){
            if (e2.getData().getGunHeat() == 0) {
                bsync({request : Fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1))});
            }
        }
        if (bearingFromGun == 0) {
            bsync({request : Scan()});
        }
    }
});

bp.registerBThread("onWin", function() {
    while(true){
        bsync({waitFor : WinEventSet});
        bsync({request : SetTurnRight(36000)});
        bsync({waitFor : RevEndedEventSet});
    }
});