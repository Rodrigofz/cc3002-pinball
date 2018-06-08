package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class SpotTargetHittedVisitor implements Visitor {
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.notifySpotTarget();
    }

    @Override
    public void visitGame(Game game, Table table) {
        game.getJackPotBonus().trigger(game);
    }
}
