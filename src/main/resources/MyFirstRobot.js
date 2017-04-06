importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);

veryFar = 9999.0;
quarterTurn = 90.0;
halfTurn = 180.0;
threeQuarterTurn = 270.0;
fullTurn = 360.0;

bp.registerBThread(function() {
	bsync({ request : TurnGunRight(7200) });

	height = robot.getBattleFieldHeight();
	width = robot.getBattleFieldWidth();

	y = robot.getY();
	x = robot.getX();

	bsync({ request : TurnRight(robot.getHeading()) });

	if (x > width / 2)
		bsync({ request : TurnRight(threeQuarterTurn - robot.getHeading()) });
	else
		bsync({ request : TurnRight(quarterTurn - robot.getHeading()) });

	bsync({ request : Ahead(Math.abs(width / 2 - x)) });
	if (y < height / 2)
		bsync({ request : TurnLeft(getHeading()) });
	else
		bsync({ request : TurnRight(halfTurn - getHeading()) });

	bsync({ request : Ahead(Math.abs(height / 2 - y)) });
});


var Scan = bp.EventSet('', function(e) {
	return (e instanceof ScannedRobot);
});

bp.registerBThread(function() {
	bp.log.info('Running fire-on-scan b-thread');

	while (true) {
		bsync({ waitFor : Scan });
		bsync({ request : Fire(1.0) });
	}
});
