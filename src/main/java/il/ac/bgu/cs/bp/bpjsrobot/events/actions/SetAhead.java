package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetAhead extends RobotActionEvent {
    double distance;

    public SetAhead(double distance) {
        super("SetAhead");
        this.distance = distance;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setAhead(distance);
    }

    @Override
    public String toString() {
        return "SetAhead [distance=" + distance + "]";
    }

}