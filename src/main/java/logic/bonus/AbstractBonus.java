package logic.bonus;

import controller.Game;

/**
 * Abstract class that represents bonuses, there can be 3 types of it: DropTargetBonus, ExtraBallBonus or JackPotBonus
 */
public abstract class AbstractBonus implements Bonus {
    private int timesTriggered;

    /**
     * Constructor of the class. Sets the timesTriggered to 0
     */
    public AbstractBonus(){
        timesTriggered = 0;
    }

    /**
     * Gets the times triggered
     *
     * @return variable timesTriggered
     */
    public int timesTriggered(){
        return timesTriggered;
    }

    /**
     * Triggers the bonus, adds one to the timesTriggered variable
     *
     * @param game the game controller object
     */
    public void trigger(Game game){
        timesTriggered ++;
    }

}
