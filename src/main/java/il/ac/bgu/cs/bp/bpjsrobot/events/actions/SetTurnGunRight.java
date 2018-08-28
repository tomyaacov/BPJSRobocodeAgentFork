package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetTurnGunRight extends RobotActionEvent {
    double degrees;

    public SetTurnGunRight(double degrees) {
        super("SetTurnGunRight");
        this.degrees = degrees;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setTurnGunRight(degrees);
    }

    @Override
    public String toString() {
        return "SetTurnGunRight [degrees=" + degrees + "]";
    }

}