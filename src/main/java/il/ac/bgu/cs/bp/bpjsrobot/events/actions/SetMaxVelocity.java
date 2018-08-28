package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetMaxVelocity extends RobotActionEvent {
    double newMaxVelocity;

    public SetMaxVelocity(double newMaxVelocity) {
        super("SetMaxVelocity");
        this.newMaxVelocity = newMaxVelocity;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setMaxVelocity(newMaxVelocity);
    }

    @Override
    public String toString() {
        return "SetMaxVelocity [newMaxVelocity=" + newMaxVelocity + "]";
    }

}