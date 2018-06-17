package logic.bonus;

import controller.Game;

/**
 * One of the 3 types of bonuses: It gives an extra ball
 */
public class ExtraBallBonus extends AbstractBonus{
    /**
     * Adds an extra ball to the game, and then calls the trigger method in the super class
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game){
        game.addBall();
        super.trigger(game);
    }
}
