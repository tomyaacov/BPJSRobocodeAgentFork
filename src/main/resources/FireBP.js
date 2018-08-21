importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
//importPackage(Packages.net.sf.robocode.util);//TODO: still need to debug this
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

bp.registerBThread("run", function() {
	bsync({request : SetBodyColor(Color.orange)});
    bsync({request : SetGunColor(Color.orange)});
    bsync({request : SetRadarColor(Color.red)});
    bsync({request : SetScanColor(Color.red)});
    bsync({request : SetBulletColor(Color.red)});
    while (true) {
        bsync({request : TurnGunRight(5)});
	}
});

bp.registerBThread("onScannedRobot", function() {
	while (true){
		var e1 = bsync({ waitFor : ScannedRobotEventSet });
		if(e1.getData().getDistance() < 50 && robot.getEnergy() > 50){
			bsync({request : Fire(3)});

		} else {
			bsync({request : Fire(1)});
		}
		bsync({request : Scan()});
	}
});

bp.registerBThread("onHitByBullet", function () {
    var angle = null;
	while(true){
		var e1 = bsync({waitFor : HitByBulletEventSet});
		angle = 90 - (robot.getHeading() - e1.getData().getHeading());// TODO: add "normalRelativeAngleDegrees" from utilities and remove patch
		//bp.log.info(Utils.normalRelativeAngleDegrees(angle));//TODO: still need to debug this
        angle = (angle %= 360.0) >= 0.0 ? (angle < 180.0 ? angle : angle - 360.0) : (angle >= -180.0 ? angle : angle + 360.0);
        bsync({request : TurnRight(angle)});
        bsync({request : Ahead(dist)});
        dist *= -1;
        bsync({request : Scan()});

	}
});

bp.registerBThread("onHitRobot", function () {
	var turnGunAmt = null;
	while(true){
		var e1 = bsync({waitFor : HitRobotEventSet});
        turnGunAmt = e1.getData().getBearing() + robot.getHeading() - robot.getGunHeading();// TODO: add "normalRelativeAngleDegrees" from utilities and remove patch
        turnGunAmt = (turnGunAmt %= 360.0) >= 0.0 ? (turnGunAmt < 180.0 ? turnGunAmt : turnGunAmt - 360.0) : (turnGunAmt >= -180.0 ? turnGunAmt : turnGunAmt + 360.0);
        bsync({request :TurnGunRight(turnGunAmt)});
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


