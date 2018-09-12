package dw;

import robocode.AdvancedRobot;
import robocode.SkippedTurnEvent;
import robocode.StatusEvent;

public class TurnTimeTestBot extends AdvancedRobot {
    long start;
    @Override
    public void onSkippedTurn(SkippedTurnEvent event) {
        System.out.format("[%d] %d \n",getTime(),System.nanoTime() - start);
    }

    @Override
    public void onStatus(StatusEvent e) {
        start = System.nanoTime();
    }

    @Override
    public void run() {
        while(true){
            if (start%2 == 0){
                try {
                    System.out.println("sleeping");
                    Thread.sleep(100);
                } catch (InterruptedException e){}
            } else {
                System.out.println("Normal");
                doNothing();
            }
        }
    }
}
