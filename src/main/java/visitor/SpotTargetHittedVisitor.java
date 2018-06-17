package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class SpotTargetHittedVisitor implements Visitor {
    /**
     * Makes the table notify the game that a spotTarget has been hitted
     *
     * @param table         Table to be visited
     * @param hittable      Hittable that is triggering the visit
     */
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.notifySpotTarget();
    }

    /**
     * Triggers the JackPotBonus instance
     *
     * @param game          Game to be visited
     * @param table         Table that is triggering the visit
     */
    @Override
    public void visitGame(Game game, Table table) {
        game.getJackPotBonus().trigger(game);
    }
}
