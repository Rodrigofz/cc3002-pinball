package logic.bonus;

import controller.Game;

/**
 * One of the 3 types of bonuses: Gives 1000000 points and upgrades al the bumpers
 */
public class DropTargetBonus extends AbstractBonus {
    /**
     * Adds 1000000 to the game score and upgrades all the bumpers, then calls trigger in the super class
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        game.addScore(1000000);
        game.getCurrentTable().upgradeAllBumpers();
        super.trigger(game);
    }
}
