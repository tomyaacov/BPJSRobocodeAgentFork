package il.ac.bgu.cs.bp.bpjsrobot.events.actions;

import il.ac.bgu.cs.bp.bpjsrobot.BPjsRobot;

@SuppressWarnings("serial")
public class Fire extends RobotActionEvent {
    double power;

    public Fire(double power) {
        super("Fire");
        this.power = power;
    }

    @Override
    public void act(BPjsRobot robot) {
        robot.setDebugProperty("Fire", Long.toString(System.nanoTime() - robot.lastExecute));
        robot.lastExecute = System.nanoTime();
        robot.fire(power);
    }

    @Override
    public String toString() {
        return "Fire [power=" + power + "]";
    }

}