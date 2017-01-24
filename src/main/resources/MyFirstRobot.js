importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);

var Status = bp.EventSet('Status', function(e) {
	return e.name == 'Status';
});

var ScannedRobot = bp.EventSet('ScannedRobot', function(e) {
	return e.name == 'ScannedRobot';
});

bp.registerBThread("", function() {
	while (true) {
        bsync({ request :Ahead(100)          });
        bsync({ request :TurnGunRight(360)   });
        bsync({ request :Back(100)           });
        bsync({ request :TurnGunRight(360)   });  

		bsync({ waitFor : Status });
	}
});


bp.registerBThread("", function() {
	while (true) {
		bsync({ waitFor : ScannedRobot });
		bsync({ request : Fire(1) });
	}
});
