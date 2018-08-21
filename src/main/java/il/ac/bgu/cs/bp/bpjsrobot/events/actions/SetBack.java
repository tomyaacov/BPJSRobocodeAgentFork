package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetBack extends RobotActionEvent {
    double distance;

    public SetBack(double distance) {
        super("SetBack");
        this.distance = distance;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setBack(distance);
    }

    @Override
    public String toString() {
        return "setBack [distance=" + distance + "]";
    }

}