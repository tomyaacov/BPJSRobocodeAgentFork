importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.actions);
importPackage(Packages.il.ac.bgu.cs.bp.bpjsrobot.events.sensors);
importPackage(java.awt);

trigger = 80;

var RevEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof RevEnded);
});
var MotionEndedEventSet = bp.EventSet('', function(e) {
    return (e instanceof MotionEnded);
});
var StatusEventSet = bp.EventSet('', function(e) {
    return (e instanceof Status);
});

bp.registerBThread("run", function() {
    bsync({request : SetBodyColor(Color.white)});
    bsync({request : SetGunColor(Color.white)});
    bsync({request : SetRadarColor(Color.white)});
});

bp.registerBThread("onCustomEvent", function(){
    while (true){
        var e1 = bsync({waitFor : StatusEventSet});
        if(e1.getStatus().getEnergy() <= trigger){
            trigger -= 20;
            bp.log.info("Ouch, down to " + (e1.getStatus().getEnergy() + .5) + " energy.)");
            bsync({request : SetTurnLeft(65)});
            bsync({waitFor : RevEndedEventSet});
            bsync({request : SetAhead(100)});
            bsync({waitFor : MotionEndedEventSet});
        }
    }
});