importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(Packages.robocode.util);
importPackage(java.awt);
/*
veryFar = 9999.0;
quarterTurn = 90.0;
halfTurn = 180.0;
threeQuarterTurn = 270.0;
fullTurn = 360.0;
*/
dist = 50;
/*
var targetflag = false;
var targetBear = null;
var firsttimescanned = 0;
*/
var ScannedRobotEventSet = bp.EventSet('', function(e) {
	return (e instanceof ScannedRobot);
});
var HitByBulletEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitByBullet);
});
var HitRobotEventSet = bp.EventSet('', function(e) {
    return (e instanceof HitRobot);
});
var GunRevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof GunRevEnded);
});
var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var MotionEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof MotionEnded);
});


bp.registerBThread("run", function() {
	bsync({request : SetBodyColor(Color.orange)});
    bsync({request : SetGunColor(Color.orange)});
    bsync({request : SetRadarColor(Color.red)});
    bsync({request : SetScanColor(Color.red)});
    bsync({request : SetBulletColor(Color.red)});
    while (true) {
        bsync({request : SetTurnGunRight(5)});
        bsync({waitFor : GunRevEndedEventSet});
	}
});

bp.registerBThread("onScannedRobot", function() {
	while (true){
		var e1 = bsync({ waitFor : ScannedRobotEventSet });
		if(e1.getData().getDistance() < 50 && robot.getEnergy() > 50){
			bsync({request : Fire(3), block: SetTurnGunRight(5)});


		} else {
			bsync({request : Fire(1), block: SetTurnGunRight(5)});
		}
		bsync({request : Scan(), block: SetTurnGunRight(5)});
	}
});

bp.registerBThread("onHitByBullet", function () {
    var angle = null;
	while(true){
		var e1 = bsync({waitFor : HitByBulletEventSet});
		angle = Utils.normalRelativeAngleDegrees(90 - (robot.getHeading() - e1.getData().getHeading()));
        bsync({request : SetTurnRight(angle), block: SetTurnGunRight(5)});
        bsync({waitFor : RevEndedEventSet, block: SetTurnGunRight(5)});
        bsync({request : SetAhead(dist), block: SetTurnGunRight(5)});
        bsync({waitFor : MotionEndedEventSet, block: SetTurnGunRight(5)});
        dist *= -1;
        bsync({request : Scan(), block: SetTurnGunRight(5)});

	}
});

bp.registerBThread("onHitRobot", function () {
	var turnGunAmt = null;
	while(true){
		var e1 = bsync({waitFor : HitRobotEventSet});
        turnGunAmt = Utils.normalRelativeAngleDegrees(e1.getData().getBearing() + robot.getHeading() - robot.getGunHeading());
        bsync({request :SetTurnGunRight(turnGunAmt)});
        bsync({waitFor : GunRevEndedEventSet, block: SetTurnGunRight(5)});
        bsync({request : Fire(3)});
	}
});

/*
bp.registerBThread("SmartRadarSpin", function() {
	while (true) {
		if (targetflag == false) {
			bp.log.info('Running radar scan false');
			bsync({ request : TurnRadarRight(10) });
			bsync({ waitFor : RadRevend });
		}
		if (targetflag == true) {
			bp.log.info('Running radar scan true');
			if (firsttimescanned == 0) {
				bsync({ request : TurnRadarLeft(targetBear) });
				bsync({ waitFor : RadRevend });
				firsttimescanned = 1;
			}
			bsync({ request : TurnRadarLeft(50) });
			bsync({ waitFor : RadRevend });
			bsync({ request : TurnRadarRight(50) });
			bsync({ waitFor : RadRevend });
		}
	}
});

bp.registerBThread("IsThereAny Target", function() {
	var bear = null;
	var oldbear = null;
	while (true) {
		var e = bsync({ waitFor : Scaned });
		var bear = e.getData().getBearing();
		if (bear != null && oldbear != bear) {
			targetflag = true;
			bp.log.info('flagtrue' + targetflag);
			oldbear = bear;
		}
	}
});

bp.registerBThread("Go To Target", function() {
	var e = bsync({ waitFor : Scaned });
	targetBear = e.getData().getBearing();
	var targetDist = e.getData().getDistance();
	bp.log.info("targetBear=" + targetBear);
	bp.log.info("targetDist=" + targetDist);

	bsync({ request : TurnRight(targetBear) });
	bsync({ waitFor : Revend });
	bsync({ request : Ahead(targetDist) });
	bsync({ waitFor : Motiend });
});

bp.registerBThread(function() {
	bp.log.info('Running fire-on-scan b-thread');
	while (true) {
		bsync({ waitFor : Scaned });
		bsync({ request : Fire(50.0) });
	}
});
*/


