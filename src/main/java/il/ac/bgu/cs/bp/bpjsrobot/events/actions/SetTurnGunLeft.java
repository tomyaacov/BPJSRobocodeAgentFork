package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetTurnGunLeft extends RobotActionEvent {
    double degrees;

    public SetTurnGunLeft(double degrees) {
        super("SetTurnGunLeft");
        this.degrees = degrees;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setTurnGunLeft(degrees);
    }

    @Override
    public String toString() {
        return "SetTurnGunLeft [degrees=" + degrees + "]";
    }

}