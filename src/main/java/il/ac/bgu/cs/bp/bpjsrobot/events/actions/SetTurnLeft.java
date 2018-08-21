package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetTurnLeft extends RobotActionEvent {
    double degrees;

    public SetTurnLeft(double degrees) {
        super("SetTurnLeft");
        this.degrees = degrees;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setTurnLeft(degrees);
    }

    @Override
    public String toString() {
        return "SetTurnLeft [degrees=" + degrees + "]";
    }

}