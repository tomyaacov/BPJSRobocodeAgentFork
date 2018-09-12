package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetStop extends RobotActionEvent {

    public SetStop() {
        super("SetStop");
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setStop();
    }

    @Override
    public String toString() {
        return "SetStop";
    }

}