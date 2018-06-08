package logic.bonus;

import controller.Game;

public abstract class AbstractBonus implements Bonus {
    private int timesTriggered;

    public AbstractBonus(){
        timesTriggered = 0;
    }

    public int timesTriggered(){
        return timesTriggered;
    }

    public void trigger(Game game){
        timesTriggered ++;
    }

}
