importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);

bp.registerBThread(function() {
	bsync({ request : TurnGunRight(7200) });

	while (true) {
		bsync({ request : Ahead(100) });
		bp.log.info('waiting for MotionEnded 1');
		bsync({ waitFor : MotionEnded.event });
		bp.log.info('after MotionEnded 1');
		bsync({ request : Back(100) });
		bp.log.info('waiting for MotionEnded 2');
		bsync({ waitFor : MotionEnded.event });
		bp.log.info('after MotionEnded 2');
	}
});

var Scan = bp.EventSet('', function(e) {
	return (e instanceof ScannedRobot);
});

bp.registerBThread(function() {
	while (true) {
		bsync({ waitFor : Scan });
		bsync({ request : Fire(1.0) });
	}
});

