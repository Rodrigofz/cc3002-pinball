package logic.bonus;

import controller.Game;

/**
 * One of the 3 types of bonuses: it gives 100000 points
 */
public class JackPotBonus extends AbstractBonus{
    /**
     * Adds 100000 points to the game score and then calls the trigger method of the super class
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        game.addScore(100000);
        super.trigger(game);
    }
}
