package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class SetResume extends RobotActionEvent {

    public SetResume() {
        super("SetResume");
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setResume();
    }

    @Override
    public String toString() {
        return "SetResume";
    }

}