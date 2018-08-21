package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetTurnRight extends RobotActionEvent {
    double degrees;

    public SetTurnRight(double degrees) {
        super("SetTurnRight");
        this.degrees = degrees;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setTurnRight(degrees);
    }

    @Override
    public String toString() {
        return "SetTurnRight [degrees=" + degrees + "]";
    }

}